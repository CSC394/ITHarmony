import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { CandidateProfileItharmonyService } from './candidate-profile-itharmony.service';

@Injectable()
export class CandidateProfileItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private candidateProfileService: CandidateProfileItharmonyService

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
                this.candidateProfileService.find(id)
                    .subscribe((candidateProfileResponse: HttpResponse<CandidateProfileItharmony>) => {
                        const candidateProfile: CandidateProfileItharmony = candidateProfileResponse.body;
                        this.ngbModalRef = this.candidateProfileModalRef(component, candidateProfile);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.candidateProfileModalRef(component, new CandidateProfileItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    candidateProfileModalRef(component: Component, candidateProfile: CandidateProfileItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.candidateProfile = candidateProfile;
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
