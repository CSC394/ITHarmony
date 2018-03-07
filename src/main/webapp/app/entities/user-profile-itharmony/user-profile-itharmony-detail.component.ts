import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { UserProfileItharmony } from './user-profile-itharmony.model';
import { UserProfileItharmonyService } from './user-profile-itharmony.service';

@Component({
    selector: 'jhi-user-profile-itharmony-detail',
    templateUrl: './user-profile-itharmony-detail.component.html'
})
export class UserProfileItharmonyDetailComponent implements OnInit, OnDestroy {

    userProfile: UserProfileItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private userProfileService: UserProfileItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserProfiles();
    }

    load(id) {
        this.userProfileService.find(id)
            .subscribe((userProfileResponse: HttpResponse<UserProfileItharmony>) => {
                this.userProfile = userProfileResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userProfileListModification',
            (response) => this.load(this.userProfile.id)
        );
    }
}
