import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CultureProfileItharmony } from './culture-profile-itharmony.model';
import { CultureProfileItharmonyPopupService } from './culture-profile-itharmony-popup.service';
import { CultureProfileItharmonyService } from './culture-profile-itharmony.service';

@Component({
    selector: 'jhi-culture-profile-itharmony-delete-dialog',
    templateUrl: './culture-profile-itharmony-delete-dialog.component.html'
})
export class CultureProfileItharmonyDeleteDialogComponent {

    cultureProfile: CultureProfileItharmony;

    constructor(
        private cultureProfileService: CultureProfileItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cultureProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cultureProfileListModification',
                content: 'Deleted an cultureProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-culture-profile-itharmony-delete-popup',
    template: ''
})
export class CultureProfileItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cultureProfilePopupService: CultureProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cultureProfilePopupService
                .open(CultureProfileItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
