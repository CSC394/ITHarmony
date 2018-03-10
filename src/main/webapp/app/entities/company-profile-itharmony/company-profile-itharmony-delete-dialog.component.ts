import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { CompanyProfileItharmonyPopupService } from './company-profile-itharmony-popup.service';
import { CompanyProfileItharmonyService } from './company-profile-itharmony.service';

@Component({
    selector: 'jhi-company-profile-itharmony-delete-dialog',
    templateUrl: './company-profile-itharmony-delete-dialog.component.html'
})
export class CompanyProfileItharmonyDeleteDialogComponent {

    companyProfile: CompanyProfileItharmony;

    constructor(
        private companyProfileService: CompanyProfileItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.companyProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'companyProfileListModification',
                content: 'Deleted an companyProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-company-profile-itharmony-delete-popup',
    template: ''
})
export class CompanyProfileItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private companyProfilePopupService: CompanyProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.companyProfilePopupService
                .open(CompanyProfileItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
