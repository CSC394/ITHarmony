import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { CompanyProfileItharmonyService } from './company-profile-itharmony.service';

@Injectable()
export class CompanyProfileItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private companyProfileService: CompanyProfileItharmonyService

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
                this.companyProfileService.find(id)
                    .subscribe((companyProfileResponse: HttpResponse<CompanyProfileItharmony>) => {
                        const companyProfile: CompanyProfileItharmony = companyProfileResponse.body;
                        this.ngbModalRef = this.companyProfileModalRef(component, companyProfile);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.companyProfileModalRef(component, new CompanyProfileItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    companyProfileModalRef(component: Component, companyProfile: CompanyProfileItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.companyProfile = companyProfile;
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
