import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { SkillsProfileItharmonyService } from './skills-profile-itharmony.service';

@Injectable()
export class SkillsProfileItharmonyPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private skillsProfileService: SkillsProfileItharmonyService

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
                this.skillsProfileService.find(id)
                    .subscribe((skillsProfileResponse: HttpResponse<SkillsProfileItharmony>) => {
                        const skillsProfile: SkillsProfileItharmony = skillsProfileResponse.body;
                        this.ngbModalRef = this.skillsProfileModalRef(component, skillsProfile);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.skillsProfileModalRef(component, new SkillsProfileItharmony());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    skillsProfileModalRef(component: Component, skillsProfile: SkillsProfileItharmony): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.skillsProfile = skillsProfile;
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
