import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserProfileItharmony } from './user-profile-itharmony.model';
import { UserProfileItharmonyPopupService } from './user-profile-itharmony-popup.service';
import { UserProfileItharmonyService } from './user-profile-itharmony.service';

@Component({
    selector: 'jhi-user-profile-itharmony-delete-dialog',
    templateUrl: './user-profile-itharmony-delete-dialog.component.html'
})
export class UserProfileItharmonyDeleteDialogComponent {

    userProfile: UserProfileItharmony;

    constructor(
        private userProfileService: UserProfileItharmonyService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userProfileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userProfileListModification',
                content: 'Deleted an userProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-profile-itharmony-delete-popup',
    template: ''
})
export class UserProfileItharmonyDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfilePopupService: UserProfileItharmonyPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userProfilePopupService
                .open(UserProfileItharmonyDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
