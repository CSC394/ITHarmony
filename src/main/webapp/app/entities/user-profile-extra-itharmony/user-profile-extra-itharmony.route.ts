import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserProfileExtraItharmonyComponent } from './user-profile-extra-itharmony.component';
import { UserProfileExtraItharmonyDetailComponent } from './user-profile-extra-itharmony-detail.component';
import { UserProfileExtraItharmonyPopupComponent } from './user-profile-extra-itharmony-dialog.component';
import { UserProfileExtraItharmonyDeletePopupComponent } from './user-profile-extra-itharmony-delete-dialog.component';

export const userProfileExtraRoute: Routes = [
    {
        path: 'user-profile-extra-itharmony',
        component: UserProfileExtraItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Profile Extras'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-profile-extra-itharmony/:id',
        component: UserProfileExtraItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Profile Extras'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userProfileExtraPopupRoute: Routes = [
    {
        path: 'user-profile-extra-itharmony-new',
        component: UserProfileExtraItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Profile Extras'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-extra-itharmony/:id/edit',
        component: UserProfileExtraItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Profile Extras'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-profile-extra-itharmony/:id/delete',
        component: UserProfileExtraItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Profile Extras'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
