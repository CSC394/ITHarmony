import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CandidateEducationItharmony } from './candidate-education-itharmony.model';
import { CandidateEducationItharmonyService } from './candidate-education-itharmony.service';

@Component({
    selector: 'jhi-candidate-education-itharmony-detail',
    templateUrl: './candidate-education-itharmony-detail.component.html'
})
export class CandidateEducationItharmonyDetailComponent implements OnInit, OnDestroy {

    candidateEducation: CandidateEducationItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private candidateEducationService: CandidateEducationItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCandidateEducations();
    }

    load(id) {
        this.candidateEducationService.find(id)
            .subscribe((candidateEducationResponse: HttpResponse<CandidateEducationItharmony>) => {
                this.candidateEducation = candidateEducationResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCandidateEducations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'candidateEducationListModification',
            (response) => this.load(this.candidateEducation.id)
        );
    }
}
