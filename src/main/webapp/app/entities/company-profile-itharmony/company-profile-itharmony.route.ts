import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CompanyProfileItharmonyComponent } from './company-profile-itharmony.component';
import { CompanyProfileItharmonyDetailComponent } from './company-profile-itharmony-detail.component';
import { CompanyProfileItharmonyPopupComponent } from './company-profile-itharmony-dialog.component';
import { CompanyProfileItharmonyDeletePopupComponent } from './company-profile-itharmony-delete-dialog.component';

export const companyProfileRoute: Routes = [
    {
        path: 'company-profile-itharmony',
        component: CompanyProfileItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CompanyProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'company-profile-itharmony/:id',
        component: CompanyProfileItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CompanyProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const companyProfilePopupRoute: Routes = [
    {
        path: 'company-profile-itharmony-new',
        component: CompanyProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CompanyProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'company-profile-itharmony/:id/edit',
        component: CompanyProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CompanyProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'company-profile-itharmony/:id/delete',
        component: CompanyProfileItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CompanyProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
