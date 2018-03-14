import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Principal } from '../../shared';

@Component({
    selector: 'jhi-job-create-flow-itharmony',
    templateUrl: './job-create-flow-itharmony.component.html'
})
export class JobCreateFlowItharmonyComponent implements OnInit, OnDestroy {
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }

    ngOnDestroy() {
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
