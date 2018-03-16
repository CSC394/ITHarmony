import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyService } from './job-match-itharmony.service';

@Component({
    selector: 'jhi-job-match-itharmony-detail',
    templateUrl: './job-match-itharmony-detail.component.html'
})
export class JobMatchItharmonyDetailComponent implements OnInit, OnDestroy {

    jobMatch: JobMatchItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private jobMatchService: JobMatchItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJobMatches();
    }

    load(id) {
        this.jobMatchService.find(id)
            .subscribe((jobMatchResponse: HttpResponse<JobMatchItharmony>) => {
                this.jobMatch = jobMatchResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJobMatches() {
        this.eventSubscriber = this.eventManager.subscribe(
            'jobMatchListModification',
            (response) => this.load(this.jobMatch.id)
        );
    }
}
