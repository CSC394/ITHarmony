import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyPopupService } from './user-profile-extra-itharmony-popup.service';
import { UserProfileExtraItharmonyService } from './user-profile-extra-itharmony.service';

@Component({
    selector: 'jhi-user-profile-extra-itharmony-delete-dialog',
    templateUrl: './user-profile-extra-itharmony-delete-dialog.component.html'
})
export class UserProfileExtraItharmonyDeleteDialogComponent {

    userProfileExtra: UserProfileExtraItharmony;

    constructor(
        private userProfileExtraService: UserProfileExtraItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userProfileExtraService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userProfileExtraListModification',
                content: 'Deleted an userProfileExtra'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-profile-extra-itharmony-delete-popup',
    template: ''
})
export class UserProfileExtraItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfileExtraPopupService: UserProfileExtraItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userProfileExtraPopupService
                .open(UserProfileExtraItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
