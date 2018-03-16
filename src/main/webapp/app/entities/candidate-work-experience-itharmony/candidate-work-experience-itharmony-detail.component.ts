import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { CandidateWorkExperienceItharmonyService } from './candidate-work-experience-itharmony.service';

@Component({
    selector: 'jhi-candidate-work-experience-itharmony-detail',
    templateUrl: './candidate-work-experience-itharmony-detail.component.html'
})
export class CandidateWorkExperienceItharmonyDetailComponent implements OnInit, OnDestroy {

    candidateWorkExperience: CandidateWorkExperienceItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private candidateWorkExperienceService: CandidateWorkExperienceItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCandidateWorkExperiences();
    }

    load(id) {
        this.candidateWorkExperienceService.find(id)
            .subscribe((candidateWorkExperienceResponse: HttpResponse<CandidateWorkExperienceItharmony>) => {
                this.candidateWorkExperience = candidateWorkExperienceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCandidateWorkExperiences() {
        this.eventSubscriber = this.eventManager.subscribe(
            'candidateWorkExperienceListModification',
            (response) => this.load(this.candidateWorkExperience.id)
        );
    }
}
