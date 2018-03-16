import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserRegFlowComponent } from './user-reg-flow.component';
import { UserRegFlow2Component } from './user-reg-flow2.component';
import { UserRegFlow3CandidateComponent } from './user-reg-flow3-candidate.component';
import { UserRegFlow3CompanyComponent } from './user-reg-flow3-company.component';
import { UserRegFlow4BothComponent } from './user-reg-flow4-both.component';
import { UserRegFlow5CandidateComponent } from './user-reg-flow5-candidate.component';
import { UserRegFlow6CandidateComponent } from './user-reg-flow6-candidate.component';
import { UserRegFlow7CandidateComponent } from './user-reg-flow7-candidate.component';

export const userRegFlowRoute: Routes = [
    {
        path: 'user-reg-flow',
        component: UserRegFlowComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User Registration'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow2',
        component: UserRegFlow2Component,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow2'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow3-company',
        component: UserRegFlow3CompanyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow3-Company'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow3-candidate',
        component: UserRegFlow3CandidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow3-Candidate'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow4-both',
        component: UserRegFlow4BothComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow4-both'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow5-candidate',
        component: UserRegFlow5CandidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow5-candidateEducation'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow6-candidate',
        component: UserRegFlow6CandidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow6-candidateWorkExp'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-reg-flow7-candidate',
        component: UserRegFlow7CandidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlow7-candidateWorkExp'
        },
        canActivate: [UserRouteAccessService]
    }
];
