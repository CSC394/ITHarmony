import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyService } from './job-match-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-job-match-itharmony',
    templateUrl: './job-match-itharmony.component.html'
})
export class JobMatchItharmonyComponent implements OnInit, OnDestroy {
jobMatches: JobMatchItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobMatchService: JobMatchItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.jobMatchService.query().subscribe(
            (res: HttpResponse<JobMatchItharmony[]>) => {
                this.jobMatches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInJobMatches();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: JobMatchItharmony) {
        return item.id;
    }
    registerChangeInJobMatches() {
        this.eventSubscriber = this.eventManager.subscribe('jobMatchListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
