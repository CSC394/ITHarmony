import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CandidateWorkExperienceItharmonyComponent } from './candidate-work-experience-itharmony.component';
import { CandidateWorkExperienceItharmonyDetailComponent } from './candidate-work-experience-itharmony-detail.component';
import { CandidateWorkExperienceItharmonyPopupComponent } from './candidate-work-experience-itharmony-dialog.component';
import {
    CandidateWorkExperienceItharmonyDeletePopupComponent
} from './candidate-work-experience-itharmony-delete-dialog.component';

export const candidateWorkExperienceRoute: Routes = [
    {
        path: 'candidate-work-experience-itharmony',
        component: CandidateWorkExperienceItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Candidate Work Experience'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'candidate-work-experience-itharmony/:id',
        component: CandidateWorkExperienceItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Candidate Work Experience'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const candidateWorkExperiencePopupRoute: Routes = [
    {
        path: 'candidate-work-experience-itharmony-new',
        component: CandidateWorkExperienceItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Candidate Work Experience'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-work-experience-itharmony/:id/edit',
        component: CandidateWorkExperienceItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Candidate Work Experience'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-work-experience-itharmony/:id/delete',
        component: CandidateWorkExperienceItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Candidate Work Experience'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
