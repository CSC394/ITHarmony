import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { SkillsProfileItharmonyComponent } from './skills-profile-itharmony.component';
import { SkillsProfileItharmonyDetailComponent } from './skills-profile-itharmony-detail.component';
import { SkillsProfileItharmonyPopupComponent } from './skills-profile-itharmony-dialog.component';
import { SkillsProfileItharmonyDeletePopupComponent } from './skills-profile-itharmony-delete-dialog.component';

export const skillsProfileRoute: Routes = [
    {
        path: 'skills-profile-itharmony',
        component: SkillsProfileItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SkillsProfiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'skills-profile-itharmony/:id',
        component: SkillsProfileItharmonyDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SkillsProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const skillsProfilePopupRoute: Routes = [
    {
        path: 'skills-profile-itharmony-new',
        component: SkillsProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SkillsProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'skills-profile-itharmony/:id/edit',
        component: SkillsProfileItharmonyPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SkillsProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'skills-profile-itharmony/:id/delete',
        component: SkillsProfileItharmonyDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SkillsProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
