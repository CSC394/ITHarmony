import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { CandidateProfileItharmonyPopupService } from './candidate-profile-itharmony-popup.service';
import { CandidateProfileItharmonyService } from './candidate-profile-itharmony.service';

@Component({
    selector: 'jhi-candidate-profile-itharmony-delete-dialog',
    templateUrl: './candidate-profile-itharmony-delete-dialog.component.html'
})
export class CandidateProfileItharmonyDeleteDialogComponent {

    candidateProfile: CandidateProfileItharmony;

    constructor(
        private candidateProfileService: CandidateProfileItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.candidateProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'candidateProfileListModification',
                content: 'Deleted an candidateProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-candidate-profile-itharmony-delete-popup',
    template: ''
})
export class CandidateProfileItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private candidateProfilePopupService: CandidateProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.candidateProfilePopupService
                .open(CandidateProfileItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
