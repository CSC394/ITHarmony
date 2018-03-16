import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { SkillsProfileItharmonyService } from './skills-profile-itharmony.service';

@Component({
    selector: 'jhi-skills-profile-itharmony-detail',
    templateUrl: './skills-profile-itharmony-detail.component.html'
})
export class SkillsProfileItharmonyDetailComponent implements OnInit, OnDestroy {

    skillsProfile: SkillsProfileItharmony;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private skillsProfileService: SkillsProfileItharmonyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSkillsProfiles();
    }

    load(id) {
        this.skillsProfileService.find(id)
            .subscribe((skillsProfileResponse: HttpResponse<SkillsProfileItharmony>) => {
                this.skillsProfile = skillsProfileResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSkillsProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'skillsProfileListModification',
            (response) => this.load(this.skillsProfile.id)
        );
    }
}
