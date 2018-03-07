import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyPopupService } from './user-profile-extra-itharmony-popup.service';
import { UserProfileExtraItharmonyService } from './user-profile-extra-itharmony.service';
import { CultureProfileItharmony, CultureProfileItharmonyService } from '../culture-profile-itharmony';
import { CandidateProfileItharmony, CandidateProfileItharmonyService } from '../candidate-profile-itharmony';
import { CompanyProfileItharmony, CompanyProfileItharmonyService } from '../company-profile-itharmony';

@Component({
    selector: 'jhi-user-profile-extra-itharmony-dialog',
    templateUrl: './user-profile-extra-itharmony-dialog.component.html'
})
export class UserProfileExtraItharmonyDialogComponent implements OnInit {

    userProfileExtra: UserProfileExtraItharmony;
    isSaving: boolean;

    cultureprofiles: CultureProfileItharmony[];

    candidateprofiles: CandidateProfileItharmony[];

    companyprofiles: CompanyProfileItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private cultureProfileService: CultureProfileItharmonyService,
        private candidateProfileService: CandidateProfileItharmonyService,
        private companyProfileService: CompanyProfileItharmonyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.cultureProfileService
            .query({filter: 'userprofileextra-is-null'})
            .subscribe((res: HttpResponse<CultureProfileItharmony[]>) => {
                if (!this.userProfileExtra.cultureProfileId) {
                    this.cultureprofiles = res.body;
                } else {
                    this.cultureProfileService
                        .find(this.userProfileExtra.cultureProfileId)
                        .subscribe((subRes: HttpResponse<CultureProfileItharmony>) => {
                            this.cultureprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.candidateProfileService
            .query({filter: 'userprofileextra-is-null'})
            .subscribe((res: HttpResponse<CandidateProfileItharmony[]>) => {
                if (!this.userProfileExtra.candidateProfileId) {
                    this.candidateprofiles = res.body;
                } else {
                    this.candidateProfileService
                        .find(this.userProfileExtra.candidateProfileId)
                        .subscribe((subRes: HttpResponse<CandidateProfileItharmony>) => {
                            this.candidateprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.companyProfileService
            .query({filter: 'userprofileextra-is-null'})
            .subscribe((res: HttpResponse<CompanyProfileItharmony[]>) => {
                if (!this.userProfileExtra.companyProfileId) {
                    this.companyprofiles = res.body;
                } else {
                    this.companyProfileService
                        .find(this.userProfileExtra.companyProfileId)
                        .subscribe((subRes: HttpResponse<CompanyProfileItharmony>) => {
                            this.companyprofiles = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userProfileExtra.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userProfileExtraService.update(this.userProfileExtra));
        } else {
            this.subscribeToSaveResponse(
                this.userProfileExtraService.create(this.userProfileExtra));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserProfileExtraItharmony>>) {
        result.subscribe((res: HttpResponse<UserProfileExtraItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserProfileExtraItharmony) {
        this.eventManager.broadcast({ name: 'userProfileExtraListModification', content: 'OK'});
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
    selector: 'jhi-user-profile-extra-itharmony-popup',
    template: ''
})
export class UserProfileExtraItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfileExtraPopupService: UserProfileExtraItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userProfileExtraPopupService
                    .open(UserProfileExtraItharmonyDialogComponent as Component, params['id']);
            } else {
                this.userProfileExtraPopupService
                    .open(UserProfileExtraItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
