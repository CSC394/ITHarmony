import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JobMatchItharmonyComponent } from './job-match-itharmony.component';
import { JobMatchItharmonyDetailComponent } from './job-match-itharmony-detail.component';
import { JobMatchItharmonyPopupComponent } from './job-match-itharmony-dialog.component';
import { JobMatchItharmonyDeletePopupComponent } from './job-match-itharmony-delete-dialog.component';

export const jobMatchRoute: Routes = [
    {
        path: 'job-match-itharmony',
        component: JobMatchItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobMatches'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'job-match-itharmony/:id',
        component: JobMatchItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobMatches'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobMatchPopupRoute: Routes = [
    {
        path: 'job-match-itharmony-new',
        component: JobMatchItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobMatches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'job-match-itharmony/:id/edit',
        component: JobMatchItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobMatches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'job-match-itharmony/:id/delete',
        component: JobMatchItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobMatches'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
