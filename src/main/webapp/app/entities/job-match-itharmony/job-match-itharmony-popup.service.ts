import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { JobMatchItharmony } from './job-match-itharmony.model';
import { JobMatchItharmonyService } from './job-match-itharmony.service';

@Injectable()
export class JobMatchItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private jobMatchService: JobMatchItharmonyService

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
                this.jobMatchService.find(id)
                    .subscribe((jobMatchResponse: HttpResponse<JobMatchItharmony>) => {
                        const jobMatch: JobMatchItharmony = jobMatchResponse.body;
                        this.ngbModalRef = this.jobMatchModalRef(component, jobMatch);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.jobMatchModalRef(component, new JobMatchItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    jobMatchModalRef(component: Component, jobMatch: JobMatchItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.jobMatch = jobMatch;
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
