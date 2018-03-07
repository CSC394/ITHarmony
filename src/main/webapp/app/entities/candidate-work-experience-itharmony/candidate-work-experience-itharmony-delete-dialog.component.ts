import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { CandidateWorkExperienceItharmonyPopupService } from './candidate-work-experience-itharmony-popup.service';
import { CandidateWorkExperienceItharmonyService } from './candidate-work-experience-itharmony.service';

@Component({
    selector: 'jhi-candidate-work-experience-itharmony-delete-dialog',
    templateUrl: './candidate-work-experience-itharmony-delete-dialog.component.html'
})
export class CandidateWorkExperienceItharmonyDeleteDialogComponent {

    candidateWorkExperience: CandidateWorkExperienceItharmony;

    constructor(
        private candidateWorkExperienceService: CandidateWorkExperienceItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.candidateWorkExperienceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'candidateWorkExperienceListModification',
                content: 'Deleted an candidateWorkExperience'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-candidate-work-experience-itharmony-delete-popup',
    template: ''
})
export class CandidateWorkExperienceItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateWorkExperiencePopupService: CandidateWorkExperienceItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.candidateWorkExperiencePopupService
                .open(CandidateWorkExperienceItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
