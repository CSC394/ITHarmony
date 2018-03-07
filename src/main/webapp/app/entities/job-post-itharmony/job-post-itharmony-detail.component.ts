import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { JobPostItharmony } from './job-post-itharmony.model';
import { JobPostItharmonyService } from './job-post-itharmony.service';

@Component({
    selector: 'jhi-job-post-itharmony-detail',
    templateUrl: './job-post-itharmony-detail.component.html'
})
export class JobPostItharmonyDetailComponent implements OnInit, OnDestroy {

    jobPost: JobPostItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private jobPostService: JobPostItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInJobPosts();
    }

    load(id) {
        this.jobPostService.find(id)
            .subscribe((jobPostResponse: HttpResponse<JobPostItharmony>) => {
                this.jobPost = jobPostResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInJobPosts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'jobPostListModification',
            (response) => this.load(this.jobPost.id)
        );
    }
}
