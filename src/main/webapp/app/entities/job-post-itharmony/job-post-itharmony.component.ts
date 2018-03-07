import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JobPostItharmony } from './job-post-itharmony.model';
import { JobPostItharmonyService } from './job-post-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-job-post-itharmony',
    templateUrl: './job-post-itharmony.component.html'
})
export class JobPostItharmonyComponent implements OnInit, OnDestroy {
jobPosts: JobPostItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobPostService: JobPostItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.jobPostService.query().subscribe(
            (res: HttpResponse<JobPostItharmony[]>) => {
                this.jobPosts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInJobPosts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: JobPostItharmony) {
        return item.id;
    }
    registerChangeInJobPosts() {
        this.eventSubscriber = this.eventManager.subscribe('jobPostListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
