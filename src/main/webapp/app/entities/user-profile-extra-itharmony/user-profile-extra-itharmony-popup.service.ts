import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyService } from './user-profile-extra-itharmony.service';

@Injectable()
export class UserProfileExtraItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private userProfileExtraService: UserProfileExtraItharmonyService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.userProfileExtraService.find(id)
                    .subscribe((userProfileExtraResponse: HttpResponse<UserProfileExtraItharmony>) => {
                        const userProfileExtra: UserProfileExtraItharmony = userProfileExtraResponse.body;
                        this.ngbModalRef = this.userProfileExtraModalRef(component, userProfileExtra);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.userProfileExtraModalRef(component, new UserProfileExtraItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    userProfileExtraModalRef(component: Component, userProfileExtra: UserProfileExtraItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.userProfileExtra = userProfileExtra;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
