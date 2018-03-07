import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { CompanyProfileItharmonyService } from './company-profile-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-company-profile-itharmony',
    templateUrl: './company-profile-itharmony.component.html'
})
export class CompanyProfileItharmonyComponent implements OnInit, OnDestroy {
companyProfiles: CompanyProfileItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private companyProfileService: CompanyProfileItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.companyProfileService.query().subscribe(
            (res: HttpResponse<CompanyProfileItharmony[]>) => {
                this.companyProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCompanyProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CompanyProfileItharmony) {
        return item.id;
    }
    registerChangeInCompanyProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('companyProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
