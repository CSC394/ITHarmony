import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyService } from './user-profile-extra-itharmony.service';

@Component({
    selector: 'jhi-user-profile-extra-itharmony-detail',
    templateUrl: './user-profile-extra-itharmony-detail.component.html'
})
export class UserProfileExtraItharmonyDetailComponent implements OnInit, OnDestroy {

    userProfileExtra: UserProfileExtraItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserProfileExtras();
    }

    load(id) {
        this.userProfileExtraService.find(id)
            .subscribe((userProfileExtraResponse: HttpResponse<UserProfileExtraItharmony>) => {
                this.userProfileExtra = userProfileExtraResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserProfileExtras() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userProfileExtraListModification',
            (response) => this.load(this.userProfileExtra.id)
        );
    }
}
