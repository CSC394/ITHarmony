import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JobPostItharmony } from './job-post-itharmony.model';
import { JobPostItharmonyPopupService } from './job-post-itharmony-popup.service';
import { JobPostItharmonyService } from './job-post-itharmony.service';
import { SkillsProfileItharmony, SkillsProfileItharmonyService } from '../skills-profile-itharmony';
import { UserProfileItharmony, UserProfileItharmonyService } from '../user-profile-itharmony';

@Component({
    selector: 'jhi-job-post-itharmony-dialog',
    templateUrl: './job-post-itharmony-dialog.component.html'
})
export class JobPostItharmonyDialogComponent implements OnInit {

    jobPost: JobPostItharmony;
    isSaving: boolean;

    skillsprofiles: SkillsProfileItharmony[];

    userprofiles: UserProfileItharmony[];
    startDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private jobPostService: JobPostItharmonyService,
        private skillsProfileService: SkillsProfileItharmonyService,
        private userProfileService: UserProfileItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.skillsProfileService
            .query({filter: 'jobpost-is-null'})
            .subscribe((res: HttpResponse<SkillsProfileItharmony[]>) => {
                if (!this.jobPost.skillsProfileId) {
                    this.skillsprofiles = res.body;
                } else {
                    this.skillsProfileService
                        .find(this.jobPost.skillsProfileId)
                        .subscribe((subRes: HttpResponse<SkillsProfileItharmony>) => {
                            this.skillsprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userProfileService.query()
            .subscribe((res: HttpResponse<UserProfileItharmony[]>) => { this.userprofiles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.jobPost.id !== undefined) {
            this.subscribeToSaveResponse(
                this.jobPostService.update(this.jobPost));
        } else {
            this.subscribeToSaveResponse(
                this.jobPostService.create(this.jobPost));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JobPostItharmony>>) {
        result.subscribe((res: HttpResponse<JobPostItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JobPostItharmony) {
        this.eventManager.broadcast({ name: 'jobPostListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSkillsProfileById(index: number, item: SkillsProfileItharmony) {
        return item.id;
    }

    trackUserProfileById(index: number, item: UserProfileItharmony) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-job-post-itharmony-popup',
    template: ''
})
export class JobPostItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobPostPopupService: JobPostItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.jobPostPopupService
                    .open(JobPostItharmonyDialogComponent as Component, params['id']);
            } else {
                this.jobPostPopupService
                    .open(JobPostItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
