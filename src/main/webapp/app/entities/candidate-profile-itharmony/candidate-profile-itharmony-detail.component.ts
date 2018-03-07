import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { CandidateProfileItharmonyService } from './candidate-profile-itharmony.service';

@Component({
    selector: 'jhi-candidate-profile-itharmony-detail',
    templateUrl: './candidate-profile-itharmony-detail.component.html'
})
export class CandidateProfileItharmonyDetailComponent implements OnInit, OnDestroy {

    candidateProfile: CandidateProfileItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private candidateProfileService: CandidateProfileItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCandidateProfiles();
    }

    load(id) {
        this.candidateProfileService.find(id)
            .subscribe((candidateProfileResponse: HttpResponse<CandidateProfileItharmony>) => {
                this.candidateProfile = candidateProfileResponse.body;
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

    registerChangeInCandidateProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'candidateProfileListModification',
            (response) => this.load(this.candidateProfile.id)
        );
    }
}
