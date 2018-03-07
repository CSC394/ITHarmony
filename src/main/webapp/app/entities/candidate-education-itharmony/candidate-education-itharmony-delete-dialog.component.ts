import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CandidateEducationItharmony } from './candidate-education-itharmony.model';
import { CandidateEducationItharmonyPopupService } from './candidate-education-itharmony-popup.service';
import { CandidateEducationItharmonyService } from './candidate-education-itharmony.service';

@Component({
    selector: 'jhi-candidate-education-itharmony-delete-dialog',
    templateUrl: './candidate-education-itharmony-delete-dialog.component.html'
})
export class CandidateEducationItharmonyDeleteDialogComponent {

    candidateEducation: CandidateEducationItharmony;

    constructor(
        private candidateEducationService: CandidateEducationItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.candidateEducationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'candidateEducationListModification',
                content: 'Deleted an candidateEducation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-candidate-education-itharmony-delete-popup',
    template: ''
})
export class CandidateEducationItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateEducationPopupService: CandidateEducationItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.candidateEducationPopupService
                .open(CandidateEducationItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
