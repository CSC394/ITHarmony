import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { SkillsProfileItharmonyPopupService } from './skills-profile-itharmony-popup.service';
import { SkillsProfileItharmonyService } from './skills-profile-itharmony.service';

@Component({
    selector: 'jhi-skills-profile-itharmony-delete-dialog',
    templateUrl: './skills-profile-itharmony-delete-dialog.component.html'
})
export class SkillsProfileItharmonyDeleteDialogComponent {

    skillsProfile: SkillsProfileItharmony;

    constructor(
        private skillsProfileService: SkillsProfileItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.skillsProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'skillsProfileListModification',
                content: 'Deleted an skillsProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-skills-profile-itharmony-delete-popup',
    template: ''
})
export class SkillsProfileItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private skillsProfilePopupService: SkillsProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.skillsProfilePopupService
                .open(SkillsProfileItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
