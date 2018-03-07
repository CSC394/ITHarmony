import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { JobPostItharmony } from './job-post-itharmony.model';
import { JobPostItharmonyService } from './job-post-itharmony.service';

@Injectable()
export class JobPostItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private jobPostService: JobPostItharmonyService

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
                this.jobPostService.find(id)
                    .subscribe((jobPostResponse: HttpResponse<JobPostItharmony>) => {
                        const jobPost: JobPostItharmony = jobPostResponse.body;
                        if (jobPost.startDate) {
                            jobPost.startDate = {
                                year: jobPost.startDate.getFullYear(),
                                month: jobPost.startDate.getMonth() + 1,
                                day: jobPost.startDate.getDate()
                            };
                        }
                        this.ngbModalRef = this.jobPostModalRef(component, jobPost);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.jobPostModalRef(component, new JobPostItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    jobPostModalRef(component: Component, jobPost: JobPostItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.jobPost = jobPost;
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
