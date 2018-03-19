import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { SkillsProfileItharmonyPopupService } from './skills-profile-itharmony-popup.service';
import { SkillsProfileItharmonyService } from './skills-profile-itharmony.service';
import { JobPostItharmony, JobPostItharmonyService } from '../job-post-itharmony';
import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';
import {Principal} from '../../shared';
import {User} from '../../shared/user/user.model';

@Component({
    selector: 'jhi-skills-profile-itharmony-dialog',
    templateUrl: './skills-profile-itharmony-dialog.component.html'
})
export class SkillsProfileItharmonyDialogComponent implements OnInit {

    skillsProfile: SkillsProfileItharmony;
    isSaving: boolean;

    jobposts: JobPostItharmony[];
    currentAccount: User;
    userProfileExtra: UserProfileExtraItharmony;
    userprofileextras: UserProfileExtraItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private skillsProfileService: SkillsProfileItharmonyService,
        private jobPostService: JobPostItharmonyService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then((u) => {
            this.currentAccount = u;
            this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body) {
                    if (upe.userId === this.currentAccount.id) {
                        console.log('UserProfileExtra Identified with the current account.');
                        console.log('UserProfileExtra.userID: ' + upe.userId + ' currentAccount.id: ' + this.currentAccount.id + ' AccountType: ' + upe.userTypeT);
                        this.userProfileExtra = upe;
                        this.skillsProfile.userProfileExtraId = this.userProfileExtra.id;
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

        this.jobPostService.query()
            .subscribe((res: HttpResponse<JobPostItharmony[]>) => { this.jobposts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userProfileExtraService.query()
            .subscribe((res: HttpResponse<UserProfileExtraItharmony[]>) => { this.userprofileextras = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.skillsProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.skillsProfileService.update(this.skillsProfile));
        } else {
            this.subscribeToSaveResponse(
                this.skillsProfileService.create(this.skillsProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SkillsProfileItharmony>>) {
        result.subscribe((res: HttpResponse<SkillsProfileItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SkillsProfileItharmony) {
        this.eventManager.broadcast({ name: 'skillsProfileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackJobPostById(index: number, item: JobPostItharmony) {
        return item.id;
    }

    trackUserProfileExtraById(index: number, item: UserProfileExtraItharmony) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-skills-profile-itharmony-popup',
    template: ''
})
export class SkillsProfileItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private skillsProfilePopupService: SkillsProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.skillsProfilePopupService
                    .open(SkillsProfileItharmonyDialogComponent as Component, params['id']);
            } else {
                this.skillsProfilePopupService
                    .open(SkillsProfileItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
