import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JobPostItharmony } from './job-post-itharmony.model';
import { JobPostItharmonyPopupService } from './job-post-itharmony-popup.service';
import { JobPostItharmonyService } from './job-post-itharmony.service';

@Component({
    selector: 'jhi-job-post-itharmony-delete-dialog',
    templateUrl: './job-post-itharmony-delete-dialog.component.html'
})
export class JobPostItharmonyDeleteDialogComponent {

    jobPost: JobPostItharmony;

    constructor(
        private jobPostService: JobPostItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobPostService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'jobPostListModification',
                content: 'Deleted an jobPost'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-post-itharmony-delete-popup',
    template: ''
})
export class JobPostItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobPostPopupService: JobPostItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.jobPostPopupService
                .open(JobPostItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
