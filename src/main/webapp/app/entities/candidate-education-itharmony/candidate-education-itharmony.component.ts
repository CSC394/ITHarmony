import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CandidateEducationItharmony } from './candidate-education-itharmony.model';
import { CandidateEducationItharmonyService } from './candidate-education-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-candidate-education-itharmony',
    templateUrl: './candidate-education-itharmony.component.html'
})
export class CandidateEducationItharmonyComponent implements OnInit, OnDestroy {
candidateEducations: CandidateEducationItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private candidateEducationService: CandidateEducationItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.candidateEducationService.query().subscribe(
            (res: HttpResponse<CandidateEducationItharmony[]>) => {
                this.candidateEducations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCandidateEducations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CandidateEducationItharmony) {
        return item.id;
    }
    registerChangeInCandidateEducations() {
        this.eventSubscriber = this.eventManager.subscribe('candidateEducationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
