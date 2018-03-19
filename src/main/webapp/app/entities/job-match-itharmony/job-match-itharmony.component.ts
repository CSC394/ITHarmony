import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyService } from './job-match-itharmony.service';
import { Principal } from '../../shared';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.model';

@Component({
    selector: 'jhi-job-match-itharmony',
    templateUrl: './job-match-itharmony.component.html'
})
export class JobMatchItharmonyComponent implements OnInit, OnDestroy {
    userProfileExtra: UserProfileExtraItharmony;
    jobMatches: JobMatchItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobMatchService: JobMatchItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private userProfileExtraService: UserProfileExtraItharmonyService
    ) {
    }

    loadAll() {
        this.jobMatchService.query().subscribe(
            (res: HttpResponse<JobMatchItharmony[]>) => {
                this.jobMatches = [];
                if (this.userProfileExtra.userTypeT.toString() === 'CANDIDATE') {
                    console.warn('Candidate');
                    for (const j of res.body) {
                        if (j.userProfileExtraId === this.userProfileExtra.id) {
                            this.jobMatches.push(j);
                        }
                    }
                } else {
                    this.jobMatches = res.body;
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {

        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body) { // client-side filtering, why?
                    if (upe.userId === this.currentAccount.id) {
                        console.warn('Found it!');
                        console.warn(upe.userId + ' ' + this.currentAccount.id + ' ' + upe.userTypeT);
                        this.userProfileExtra = upe;
                        alreadyfound = true;
                        this.loadAll();
                        break;
                    }
                }
                if (!alreadyfound) {
                    console.warn('DOES NOT EXIST (bad news)' + this.currentAccount.id);
                }

            }, (rese) => {
                console.warn('ERRRRRRR');
            });
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
