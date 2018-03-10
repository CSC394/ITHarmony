import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { CandidateWorkExperienceItharmonyPopupService } from './candidate-work-experience-itharmony-popup.service';
import { CandidateWorkExperienceItharmonyService } from './candidate-work-experience-itharmony.service';
import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';

@Component({
    selector: 'jhi-candidate-work-experience-itharmony-dialog',
    templateUrl: './candidate-work-experience-itharmony-dialog.component.html'
})
export class CandidateWorkExperienceItharmonyDialogComponent implements OnInit {

    candidateWorkExperience: CandidateWorkExperienceItharmony;
    isSaving: boolean;

    userprofileextras: UserProfileExtraItharmony[];
    startDateDp: any;
    endDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private candidateWorkExperienceService: CandidateWorkExperienceItharmonyService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userProfileExtraService.query()
            .subscribe((res: HttpResponse<UserProfileExtraItharmony[]>) => { this.userprofileextras = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.candidateWorkExperience.id !== undefined) {
            this.subscribeToSaveResponse(
                this.candidateWorkExperienceService.update(this.candidateWorkExperience));
        } else {
            this.subscribeToSaveResponse(
                this.candidateWorkExperienceService.create(this.candidateWorkExperience));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CandidateWorkExperienceItharmony>>) {
        result.subscribe((res: HttpResponse<CandidateWorkExperienceItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CandidateWorkExperienceItharmony) {
        this.eventManager.broadcast({ name: 'candidateWorkExperienceListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-candidate-work-experience-itharmony-popup',
    template: ''
})
export class CandidateWorkExperienceItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateWorkExperiencePopupService: CandidateWorkExperienceItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.candidateWorkExperiencePopupService
                    .open(CandidateWorkExperienceItharmonyDialogComponent as Component, params['id']);
            } else {
                this.candidateWorkExperiencePopupService
                    .open(CandidateWorkExperienceItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
