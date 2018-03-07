import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CultureProfileItharmony } from './culture-profile-itharmony.model';
import { CultureProfileItharmonyPopupService } from './culture-profile-itharmony-popup.service';
import { CultureProfileItharmonyService } from './culture-profile-itharmony.service';
import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';

@Component({
    selector: 'jhi-culture-profile-itharmony-dialog',
    templateUrl: './culture-profile-itharmony-dialog.component.html'
})
export class CultureProfileItharmonyDialogComponent implements OnInit {

    cultureProfile: CultureProfileItharmony;
    isSaving: boolean;

    userprofileextras: UserProfileExtraItharmony[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private cultureProfileService: CultureProfileItharmonyService,
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
        if (this.cultureProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cultureProfileService.update(this.cultureProfile));
        } else {
            this.subscribeToSaveResponse(
                this.cultureProfileService.create(this.cultureProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CultureProfileItharmony>>) {
        result.subscribe((res: HttpResponse<CultureProfileItharmony>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CultureProfileItharmony) {
        this.eventManager.broadcast({ name: 'cultureProfileListModification', content: 'OK'});
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
    selector: 'jhi-culture-profile-itharmony-popup',
    template: ''
})
export class CultureProfileItharmonyPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cultureProfilePopupService: CultureProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.cultureProfilePopupService
                    .open(CultureProfileItharmonyDialogComponent as Component, params['id']);
            } else {
                this.cultureProfilePopupService
                    .open(CultureProfileItharmonyDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
