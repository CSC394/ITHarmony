import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CandidateEducationItharmonyComponent } from './candidate-education-itharmony.component';
import { CandidateEducationItharmonyDetailComponent } from './candidate-education-itharmony-detail.component';
import { CandidateEducationItharmonyPopupComponent } from './candidate-education-itharmony-dialog.component';
import { CandidateEducationItharmonyDeletePopupComponent } from './candidate-education-itharmony-delete-dialog.component';

export const candidateEducationRoute: Routes = [
    {
        path: 'candidate-education-itharmony',
        component: CandidateEducationItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateEducations'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'candidate-education-itharmony/:id',
        component: CandidateEducationItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateEducations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const candidateEducationPopupRoute: Routes = [
    {
        path: 'candidate-education-itharmony-new',
        component: CandidateEducationItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateEducations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-education-itharmony/:id/edit',
        component: CandidateEducationItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateEducations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-education-itharmony/:id/delete',
        component: CandidateEducationItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateEducations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
