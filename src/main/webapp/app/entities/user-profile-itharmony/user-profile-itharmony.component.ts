import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserProfileItharmony } from './user-profile-itharmony.model';
import { UserProfileItharmonyService } from './user-profile-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-user-profile-itharmony',
    templateUrl: './user-profile-itharmony.component.html'
})
export class UserProfileItharmonyComponent implements OnInit, OnDestroy {
userProfiles: UserProfileItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userProfileService: UserProfileItharmonyService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.userProfileService.query().subscribe(
            (res: HttpResponse<UserProfileItharmony[]>) => {
                this.userProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUserProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserProfileItharmony) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInUserProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('userProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
