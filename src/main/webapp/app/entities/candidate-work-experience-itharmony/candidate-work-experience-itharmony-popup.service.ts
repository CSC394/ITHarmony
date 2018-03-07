import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { CandidateWorkExperienceItharmonyService } from './candidate-work-experience-itharmony.service';

@Injectable()
export class CandidateWorkExperienceItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private candidateWorkExperienceService: CandidateWorkExperienceItharmonyService

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
                this.candidateWorkExperienceService.find(id)
                    .subscribe((candidateWorkExperienceResponse: HttpResponse<CandidateWorkExperienceItharmony>) => {
                        const candidateWorkExperience: CandidateWorkExperienceItharmony = candidateWorkExperienceResponse.body;
                        if (candidateWorkExperience.startDate) {
                            candidateWorkExperience.startDate = {
                                year: candidateWorkExperience.startDate.getFullYear(),
                                month: candidateWorkExperience.startDate.getMonth() + 1,
                                day: candidateWorkExperience.startDate.getDate()
                            };
                        }
                        if (candidateWorkExperience.endDate) {
                            candidateWorkExperience.endDate = {
                                year: candidateWorkExperience.endDate.getFullYear(),
                                month: candidateWorkExperience.endDate.getMonth() + 1,
                                day: candidateWorkExperience.endDate.getDate()
                            };
                        }
                        this.ngbModalRef = this.candidateWorkExperienceModalRef(component, candidateWorkExperience);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.candidateWorkExperienceModalRef(component, new CandidateWorkExperienceItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    candidateWorkExperienceModalRef(component: Component, candidateWorkExperience: CandidateWorkExperienceItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.candidateWorkExperience = candidateWorkExperience;
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
