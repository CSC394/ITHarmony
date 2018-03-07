import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CultureProfileItharmony } from './culture-profile-itharmony.model';
import { CultureProfileItharmonyService } from './culture-profile-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-culture-profile-itharmony',
    templateUrl: './culture-profile-itharmony.component.html'
})
export class CultureProfileItharmonyComponent implements OnInit, OnDestroy {
cultureProfiles: CultureProfileItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private cultureProfileService: CultureProfileItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.cultureProfileService.query().subscribe(
            (res: HttpResponse<CultureProfileItharmony[]>) => {
                this.cultureProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCultureProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CultureProfileItharmony) {
        return item.id;
    }
    registerChangeInCultureProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('cultureProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
