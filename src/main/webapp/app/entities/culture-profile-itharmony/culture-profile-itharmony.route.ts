import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CultureProfileItharmonyComponent } from './culture-profile-itharmony.component';
import { CultureProfileItharmonyDetailComponent } from './culture-profile-itharmony-detail.component';
import { CultureProfileItharmonyPopupComponent } from './culture-profile-itharmony-dialog.component';
import { CultureProfileItharmonyDeletePopupComponent } from './culture-profile-itharmony-delete-dialog.component';

export const cultureProfileRoute: Routes = [
    {
        path: 'culture-profile-itharmony',
        component: CultureProfileItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CultureProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'culture-profile-itharmony/:id',
        component: CultureProfileItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CultureProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cultureProfilePopupRoute: Routes = [
    {
        path: 'culture-profile-itharmony-new',
        component: CultureProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CultureProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'culture-profile-itharmony/:id/edit',
        component: CultureProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CultureProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'culture-profile-itharmony/:id/delete',
        component: CultureProfileItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CultureProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
