import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { CandidateWorkExperienceItharmonyService } from './candidate-work-experience-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-candidate-work-experience-itharmony',
    templateUrl: './candidate-work-experience-itharmony.component.html'
})
export class CandidateWorkExperienceItharmonyComponent implements OnInit, OnDestroy {
candidateWorkExperiences: CandidateWorkExperienceItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private candidateWorkExperienceService: CandidateWorkExperienceItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.candidateWorkExperienceService.query().subscribe(
            (res: HttpResponse<CandidateWorkExperienceItharmony[]>) => {
                this.candidateWorkExperiences = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCandidateWorkExperiences();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CandidateWorkExperienceItharmony) {
        return item.id;
    }
    registerChangeInCandidateWorkExperiences() {
        this.eventSubscriber = this.eventManager.subscribe('candidateWorkExperienceListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
