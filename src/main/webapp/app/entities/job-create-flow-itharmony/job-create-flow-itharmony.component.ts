import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {Subscription} from 'rxjs/Subscription';
import {JhiEventManager} from 'ng-jhipster';

import {User} from '../../shared/user/user.model';
import {Principal} from '../../shared';
import {JobPostItharmony} from '../job-post-itharmony';
import {JobPostItharmonyService} from '../job-post-itharmony/job-post-itharmony.service';
import {UserProfileExtraItharmonyService} from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import {UserProfileExtraItharmony} from '../user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {SkillsProfileItharmony} from '../skills-profile-itharmony/skills-profile-itharmony.model';
import {SkillsProfileItharmonyService} from '../skills-profile-itharmony/skills-profile-itharmony.service';

@Component({
    selector: 'jhi-job-create-flow-itharmony',
    templateUrl: './job-create-flow-itharmony.component.html'
})
export class JobCreateFlowItharmonyComponent implements OnInit, OnDestroy {
    router: Router;
    isSaving: boolean;
    currentAccount: User;
    eventSubscriber: Subscription;
    userProfileExtra: UserProfileExtraItharmony;
    jobPost: JobPostItharmony;
    skillsProfile: SkillsProfileItharmony;
    id_array : number[] = [];

    constructor(private jobPostItharmonyService: JobPostItharmonyService,
                private userProfileExtraService: UserProfileExtraItharmonyService,
                private skillProfileService: SkillsProfileItharmonyService,
                private eventManager: JhiEventManager,
                private principal: Principal,
                private r: Router) {
        this.router = r;
        this.jobPost = new JobPostItharmony();
        this.id_array = this.id_array;
    }

    ngOnInit() {
        this.principal.identity().then((u) => {
            this.currentAccount = u;
            this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body) {
                    if (upe.userId === this.currentAccount.id) {
                        console.log('UserProfileExtra Identified with the current account.');
                        console.log('UserProfileExtra.userID: ' + upe.userId + ' currentAccount.id: ' + this.currentAccount.id + ' AccountType: ' + upe.userTypeT);
                        this.userProfileExtra = upe;
                        this.jobPost.userProfileExtraId = this.userProfileExtra.id;
                        alreadyfound = true;
                        break;
                    }
                }
                if (!alreadyfound) {
                    console.warn('UserProfileExtra does not exist with this ID!)' + this.currentAccount.id);
                }
            }, (res) => {
                console.warn('Error Querying the UserProfileExtraService');
            });
        });
    }

    ngOnDestroy() {
        this.id_array = [];
    }

    fetchSkillProfileID() {
        this.skillProfileService.query().subscribe((res) => {
            for (const sp of res.body) {
                try {
                    this.id_array.push(sp.id);
                    console.log(this.id_array);
                }
                catch{}
                console.log(sp.id);
            }
        }, (res) => {
            console.warn('Error Querying the SkillProfileService');
        });
    }

    save() {
        console.log('Save called on Job Creation Form.');
        this.isSaving = true;
        console.log('Fetching Skill Profile ID');
        this.fetchSkillProfileID();
        console.log(this.id_array);
        const skillID = this.id_array[this.id_array.length-1];
        console.log(skillID);
        this.jobPost.skillsProfileId = skillID;
        if (this.jobPost.id !== undefined) {
            console.log('Saving successfully with: ' + this.jobPost);
            this.subscribeToSaveResponse(this.jobPostItharmonyService.update(this.jobPost));
        } else {
            this.subscribeToSaveResponse(this.jobPostItharmonyService.create(this.jobPost));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JobPostItharmony>>) {
        result.subscribe((res: HttpResponse<JobPostItharmony>) =>
            this.onSaveSuccess(res.body));
    }

    private onSaveSuccess(result: JobPostItharmony) {
        this.eventManager.broadcast({name: 'JobPostItHarmony', content: 'OK'});
        this.isSaving = false;
        console.log('JobPost Save was successful!');
    }
}
