import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyPopupService } from './job-match-itharmony-popup.service';
import { JobMatchItharmonyService } from './job-match-itharmony.service';
import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';
import { JobPostItharmony, JobPostItharmonyService } from '../job-post-itharmony';

@Component({
    selector: 'jhi-job-match-itharmony-dialog',
    templateUrl: './job-match-itharmony-dialog.component.html'
})
export class JobMatchItharmonyDialogComponent implements OnInit {

    jobMatch: JobMatchItharmony;
    isSaving: boolean;

    userprofileextras: UserProfileExtraItharmony[];

    jobposts: JobPostItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private jobMatchService: JobMatchItharmonyService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private jobPostService: JobPostItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userProfileExtraService.query()
            .subscribe((res: HttpResponse<UserProfileExtraItharmony[]>) => { this.userprofileextras = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.jobPostService.query()
            .subscribe((res: HttpResponse<JobPostItharmony[]>) => { this.jobposts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.jobMatch.id !== undefined) {
            this.subscribeToSaveResponse(
                this.jobMatchService.update(this.jobMatch));
        } else {
            this.subscribeToSaveResponse(
                this.jobMatchService.create(this.jobMatch));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JobMatchItharmony>>) {
        result.subscribe((res: HttpResponse<JobMatchItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JobMatchItharmony) {
        this.eventManager.broadcast({ name: 'jobMatchListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserProfileExtraById(index: number, item: UserProfileExtraItharmony) {
        return item.id;
    }

    trackJobPostById(index: number, item: JobPostItharmony) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-job-match-itharmony-popup',
    template: ''
})
export class JobMatchItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobMatchPopupService: JobMatchItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.jobMatchPopupService
                    .open(JobMatchItharmonyDialogComponent as Component, params['id']);
            } else {
                this.jobMatchPopupService
                    .open(JobMatchItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
