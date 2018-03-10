import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { SkillsProfileItharmonyService } from './skills-profile-itharmony.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-skills-profile-itharmony',
    templateUrl: './skills-profile-itharmony.component.html'
})
export class SkillsProfileItharmonyComponent implements OnInit, OnDestroy {
skillsProfiles: SkillsProfileItharmony[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private skillsProfileService: SkillsProfileItharmonyService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.skillsProfileService.query().subscribe(
            (res: HttpResponse<SkillsProfileItharmony[]>) => {
                this.skillsProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSkillsProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SkillsProfileItharmony) {
        return item.id;
    }
    registerChangeInSkillsProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('skillsProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
