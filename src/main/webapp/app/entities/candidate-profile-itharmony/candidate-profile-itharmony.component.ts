import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { CandidateProfileItharmonyService } from './candidate-profile-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-candidate-profile-itharmony',
    templateUrl: './candidate-profile-itharmony.component.html'
})
export class CandidateProfileItharmonyComponent implements OnInit, OnDestroy {
candidateProfiles: CandidateProfileItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private candidateProfileService: CandidateProfileItharmonyService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.candidateProfileService.query().subscribe(
            (res: HttpResponse<CandidateProfileItharmony[]>) => {
                this.candidateProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCandidateProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CandidateProfileItharmony) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInCandidateProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('candidateProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
