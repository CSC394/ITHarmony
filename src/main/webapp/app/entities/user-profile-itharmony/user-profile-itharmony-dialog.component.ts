import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserProfileItharmony } from './user-profile-itharmony.model';
import { UserProfileItharmonyPopupService } from './user-profile-itharmony-popup.service';
import { UserProfileItharmonyService } from './user-profile-itharmony.service';
import { CultureProfileItharmony, CultureProfileItharmonyService } from '../culture-profile-itharmony';
import { CandidateProfileItharmony, CandidateProfileItharmonyService } from '../candidate-profile-itharmony';
import { CompanyProfileItharmony, CompanyProfileItharmonyService } from '../company-profile-itharmony';

@Component({
    selector: 'jhi-user-profile-itharmony-dialog',
    templateUrl: './user-profile-itharmony-dialog.component.html'
})
export class UserProfileItharmonyDialogComponent implements OnInit {

    userProfile: UserProfileItharmony;
    isSaving: boolean;

    cultureprofiles: CultureProfileItharmony[];

    candidateprofiles: CandidateProfileItharmony[];

    companyprofiles: CompanyProfileItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private userProfileService: UserProfileItharmonyService,
        private cultureProfileService: CultureProfileItharmonyService,
        private candidateProfileService: CandidateProfileItharmonyService,
        private companyProfileService: CompanyProfileItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.cultureProfileService
            .query({filter: 'userprofile-is-null'})
            .subscribe((res: HttpResponse<CultureProfileItharmony[]>) => {
                if (!this.userProfile.cultureProfileId) {
                    this.cultureprofiles = res.body;
                } else {
                    this.cultureProfileService
                        .find(this.userProfile.cultureProfileId)
                        .subscribe((subRes: HttpResponse<CultureProfileItharmony>) => {
                            this.cultureprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.candidateProfileService
            .query({filter: 'userprofile-is-null'})
            .subscribe((res: HttpResponse<CandidateProfileItharmony[]>) => {
                if (!this.userProfile.candidateProfileId) {
                    this.candidateprofiles = res.body;
                } else {
                    this.candidateProfileService
                        .find(this.userProfile.candidateProfileId)
                        .subscribe((subRes: HttpResponse<CandidateProfileItharmony>) => {
                            this.candidateprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.companyProfileService
            .query({filter: 'userprofile-is-null'})
            .subscribe((res: HttpResponse<CompanyProfileItharmony[]>) => {
                if (!this.userProfile.companyProfileId) {
                    this.companyprofiles = res.body;
                } else {
                    this.companyProfileService
                        .find(this.userProfile.companyProfileId)
                        .subscribe((subRes: HttpResponse<CompanyProfileItharmony>) => {
                            this.companyprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
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
        if (this.userProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userProfileService.update(this.userProfile));
        } else {
            this.subscribeToSaveResponse(
                this.userProfileService.create(this.userProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserProfileItharmony>>) {
        result.subscribe((res: HttpResponse<UserProfileItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserProfileItharmony) {
        this.eventManager.broadcast({ name: 'userProfileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCultureProfileById(index: number, item: CultureProfileItharmony) {
        return item.id;
    }

    trackCandidateProfileById(index: number, item: CandidateProfileItharmony) {
        return item.id;
    }

    trackCompanyProfileById(index: number, item: CompanyProfileItharmony) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-profile-itharmony-popup',
    template: ''
})
export class UserProfileItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfilePopupService: UserProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userProfilePopupService
                    .open(UserProfileItharmonyDialogComponent as Component, params['id']);
            } else {
                this.userProfilePopupService
                    .open(UserProfileItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
