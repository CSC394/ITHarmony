import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { CandidateProfileItharmonyPopupService } from './candidate-profile-itharmony-popup.service';
import { CandidateProfileItharmonyService } from './candidate-profile-itharmony.service';
import { UserProfileItharmony, UserProfileItharmonyService } from '../user-profile-itharmony';

@Component({
    selector: 'jhi-candidate-profile-itharmony-dialog',
    templateUrl: './candidate-profile-itharmony-dialog.component.html'
})
export class CandidateProfileItharmonyDialogComponent implements OnInit {

    candidateProfile: CandidateProfileItharmony;
    isSaving: boolean;

    userprofiles: UserProfileItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private candidateProfileService: CandidateProfileItharmonyService,
        private userProfileService: UserProfileItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userProfileService.query()
            .subscribe((res: HttpResponse<UserProfileItharmony[]>) => { this.userprofiles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.candidateProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.candidateProfileService.update(this.candidateProfile));
        } else {
            this.subscribeToSaveResponse(
                this.candidateProfileService.create(this.candidateProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CandidateProfileItharmony>>) {
        result.subscribe((res: HttpResponse<CandidateProfileItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CandidateProfileItharmony) {
        this.eventManager.broadcast({ name: 'candidateProfileListModification', content: 'OK'});
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
    selector: 'jhi-candidate-profile-itharmony-popup',
    template: ''
})
export class CandidateProfileItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateProfilePopupService: CandidateProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.candidateProfilePopupService
                    .open(CandidateProfileItharmonyDialogComponent as Component, params['id']);
            } else {
                this.candidateProfilePopupService
                    .open(CandidateProfileItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
