import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CandidateEducationItharmony } from './candidate-education-itharmony.model';
import { CandidateEducationItharmonyPopupService } from './candidate-education-itharmony-popup.service';
import { CandidateEducationItharmonyService } from './candidate-education-itharmony.service';
import { UserProfileItharmony, UserProfileItharmonyService } from '../user-profile-itharmony';

@Component({
    selector: 'jhi-candidate-education-itharmony-dialog',
    templateUrl: './candidate-education-itharmony-dialog.component.html'
})
export class CandidateEducationItharmonyDialogComponent implements OnInit {

    candidateEducation: CandidateEducationItharmony;
    isSaving: boolean;

    userprofiles: UserProfileItharmony[];
    graduationDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private candidateEducationService: CandidateEducationItharmonyService,
        private userProfileService: UserProfileItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userProfileService.query()
            .subscribe((res: HttpResponse<UserProfileItharmony[]>) => { this.userprofiles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.candidateEducation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.candidateEducationService.update(this.candidateEducation));
        } else {
            this.subscribeToSaveResponse(
                this.candidateEducationService.create(this.candidateEducation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CandidateEducationItharmony>>) {
        result.subscribe((res: HttpResponse<CandidateEducationItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CandidateEducationItharmony) {
        this.eventManager.broadcast({ name: 'candidateEducationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserProfileById(index: number, item: UserProfileItharmony) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-candidate-education-itharmony-popup',
    template: ''
})
export class CandidateEducationItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateEducationPopupService: CandidateEducationItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.candidateEducationPopupService
                    .open(CandidateEducationItharmonyDialogComponent as Component, params['id']);
            } else {
                this.candidateEducationPopupService
                    .open(CandidateEducationItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
