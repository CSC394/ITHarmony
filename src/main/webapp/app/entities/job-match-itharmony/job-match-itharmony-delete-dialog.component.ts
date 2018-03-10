import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyPopupService } from './job-match-itharmony-popup.service';
import { JobMatchItharmonyService } from './job-match-itharmony.service';

@Component({
    selector: 'jhi-job-match-itharmony-delete-dialog',
    templateUrl: './job-match-itharmony-delete-dialog.component.html'
})
export class JobMatchItharmonyDeleteDialogComponent {

    jobMatch: JobMatchItharmony;

    constructor(
        private jobMatchService: JobMatchItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobMatchService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'jobMatchListModification',
                content: 'Deleted an jobMatch'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-match-itharmony-delete-popup',
    template: ''
})
export class JobMatchItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobMatchPopupService: JobMatchItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.jobMatchPopupService
                .open(JobMatchItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
