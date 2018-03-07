import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JobPostItharmonyComponent } from './job-post-itharmony.component';
import { JobPostItharmonyDetailComponent } from './job-post-itharmony-detail.component';
import { JobPostItharmonyPopupComponent } from './job-post-itharmony-dialog.component';
import { JobPostItharmonyDeletePopupComponent } from './job-post-itharmony-delete-dialog.component';

export const jobPostRoute: Routes = [
    {
        path: 'job-post-itharmony',
        component: JobPostItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobPosts'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'job-post-itharmony/:id',
        component: JobPostItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobPosts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobPostPopupRoute: Routes = [
    {
        path: 'job-post-itharmony-new',
        component: JobPostItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'job-post-itharmony/:id/edit',
        component: JobPostItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'job-post-itharmony/:id/delete',
        component: JobPostItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'JobPosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
