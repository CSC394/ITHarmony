import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserRegFlowComponent } from './user-reg-flow.component';
import { UserRegFlow2Component } from './user-reg-flow2.component';

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
    }
];
