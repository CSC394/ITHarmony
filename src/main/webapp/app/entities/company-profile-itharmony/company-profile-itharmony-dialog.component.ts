import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { CompanyProfileItharmonyPopupService } from './company-profile-itharmony-popup.service';
import { CompanyProfileItharmonyService } from './company-profile-itharmony.service';
import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';

@Component({
    selector: 'jhi-company-profile-itharmony-dialog',
    templateUrl: './company-profile-itharmony-dialog.component.html'
})
export class CompanyProfileItharmonyDialogComponent implements OnInit {

    companyProfile: CompanyProfileItharmony;
    isSaving: boolean;

    userprofileextras: UserProfileExtraItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private companyProfileService: CompanyProfileItharmonyService,
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
        if (this.companyProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.companyProfileService.update(this.companyProfile));
        } else {
            this.subscribeToSaveResponse(
                this.companyProfileService.create(this.companyProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CompanyProfileItharmony>>) {
        result.subscribe((res: HttpResponse<CompanyProfileItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CompanyProfileItharmony) {
        this.eventManager.broadcast({ name: 'companyProfileListModification', content: 'OK'});
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
    selector: 'jhi-company-profile-itharmony-popup',
    template: ''
})
export class CompanyProfileItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private companyProfilePopupService: CompanyProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.companyProfilePopupService
                    .open(CompanyProfileItharmonyDialogComponent as Component, params['id']);
            } else {
                this.companyProfilePopupService
                    .open(CompanyProfileItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
