import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CultureProfileItharmony } from './culture-profile-itharmony.model';
import { CultureProfileItharmonyService } from './culture-profile-itharmony.service';

@Component({
    selector: 'jhi-culture-profile-itharmony-detail',
    templateUrl: './culture-profile-itharmony-detail.component.html'
})
export class CultureProfileItharmonyDetailComponent implements OnInit, OnDestroy {

    cultureProfile: CultureProfileItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cultureProfileService: CultureProfileItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCultureProfiles();
    }

    load(id) {
        this.cultureProfileService.find(id)
            .subscribe((cultureProfileResponse: HttpResponse<CultureProfileItharmony>) => {
                this.cultureProfile = cultureProfileResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCultureProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cultureProfileListModification',
            (response) => this.load(this.cultureProfile.id)
        );
    }
}
