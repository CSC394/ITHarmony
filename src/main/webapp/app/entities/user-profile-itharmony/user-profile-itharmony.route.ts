import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserProfileItharmonyComponent } from './user-profile-itharmony.component';
import { UserProfileItharmonyDetailComponent } from './user-profile-itharmony-detail.component';
import { UserProfileItharmonyPopupComponent } from './user-profile-itharmony-dialog.component';
import { UserProfileItharmonyDeletePopupComponent } from './user-profile-itharmony-delete-dialog.component';

export const userProfileRoute: Routes = [
    {
        path: 'user-profile-itharmony',
        component: UserProfileItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-profile-itharmony/:id',
        component: UserProfileItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userProfilePopupRoute: Routes = [
    {
        path: 'user-profile-itharmony-new',
        component: UserProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-itharmony/:id/edit',
        component: UserProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-itharmony/:id/delete',
        component: UserProfileItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
