import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyService } from './user-profile-extra-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-user-profile-extra-itharmony',
    templateUrl: './user-profile-extra-itharmony.component.html'
})
export class UserProfileExtraItharmonyComponent implements OnInit, OnDestroy {
userProfileExtras: UserProfileExtraItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.userProfileExtraService.query().subscribe(
            (res: HttpResponse<UserProfileExtraItharmony[]>) => {
                this.userProfileExtras = [];
                for (const r of res.body) {
                    if (r.userId === this.currentAccount.id) {
                    this.userProfileExtras.push(r);
                }
                }
                // this.userProfileExtras = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUserProfileExtras();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserProfileExtraItharmony) {
        return item.id;
    }
    registerChangeInUserProfileExtras() {
        this.eventSubscriber = this.eventManager.subscribe('userProfileExtraListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
