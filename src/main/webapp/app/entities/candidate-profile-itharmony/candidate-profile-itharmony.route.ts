import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CandidateProfileItharmonyComponent } from './candidate-profile-itharmony.component';
import { CandidateProfileItharmonyDetailComponent } from './candidate-profile-itharmony-detail.component';
import { CandidateProfileItharmonyPopupComponent } from './candidate-profile-itharmony-dialog.component';
import { CandidateProfileItharmonyDeletePopupComponent } from './candidate-profile-itharmony-delete-dialog.component';

export const candidateProfileRoute: Routes = [
    {
        path: 'candidate-profile-itharmony',
        component: CandidateProfileItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'candidate-profile-itharmony/:id',
        component: CandidateProfileItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const candidateProfilePopupRoute: Routes = [
    {
        path: 'candidate-profile-itharmony-new',
        component: CandidateProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-profile-itharmony/:id/edit',
        component: CandidateProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'candidate-profile-itharmony/:id/delete',
        component: CandidateProfileItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CandidateProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
