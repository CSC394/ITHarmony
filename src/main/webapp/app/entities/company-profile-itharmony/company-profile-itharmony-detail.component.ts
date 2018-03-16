import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { CompanyProfileItharmonyService } from './company-profile-itharmony.service';

@Component({
    selector: 'jhi-company-profile-itharmony-detail',
    templateUrl: './company-profile-itharmony-detail.component.html'
})
export class CompanyProfileItharmonyDetailComponent implements OnInit, OnDestroy {

    companyProfile: CompanyProfileItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private companyProfileService: CompanyProfileItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCompanyProfiles();
    }

    load(id) {
        this.companyProfileService.find(id)
            .subscribe((companyProfileResponse: HttpResponse<CompanyProfileItharmony>) => {
                this.companyProfile = companyProfileResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompanyProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'companyProfileListModification',
            (response) => this.load(this.companyProfile.id)
        );
    }
}
