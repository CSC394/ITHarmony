import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { User } from '../../shared/user/user.model';
import { Principal } from '../../shared';
import { JobPostItharmony } from '../job-post-itharmony';
import { JobPostItharmonyService } from '../job-post-itharmony/job-post-itharmony.service';
import { SkillsProfileItharmony } from '../skills-profile-itharmony';
import { SkillsProfileItharmonyService } from '../skills-profile-itharmony';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.model'
import { Router } from '@angular/router';

@Component({
    selector: 'jhi-job-create-flow-itharmony',
    templateUrl: './job-create-flow-itharmony.component.html'
})
export class JobCreateFlowItharmonyComponent implements OnInit, OnDestroy {
    router: Router;
    isSaving: boolean;
    currentAccount: User;
    eventSubscriber: Subscription;
    skillsProfile: SkillsProfileItharmony;
    userProfileExtra: UserProfileExtraItharmony;
    jobPost: JobPostItharmony;

    constructor(
        private jobPostItharmonyService: JobPostItharmonyService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private skillProfileService: SkillsProfileItharmonyService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private r: Router
    ) {
        this.router = r;
        this.jobPost = new JobPostItharmony();
    }

    ngOnInit() {
        this.principal.identity().then((u) => {
            this.currentAccount = u;
            this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body){
                    if (upe.userId === this.currentAccount.id) {
                        console.log('UserProfileExtra Identified with the current account');
                        console.log(upe.userId + ' ' + this.currentAccount.id + ' ' + upe.userTypeT);
                        this.userProfileExtra = upe;
                        alreadyfound = true;
                        break;
                    }
                }
                if (!alreadyfound) {
                    console.warn('UserProfileExtra does not exist with this ID!)' + this.currentAccount.id);
                }
            }, (res) => { console.warn('Error Querying the UserProfileExtraService');
            });
        });
    }

    ngOnDestroy() {
    }
}
