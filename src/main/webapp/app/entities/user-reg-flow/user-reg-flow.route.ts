import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { UserRegFlowComponent } from './user-reg-flow.component';

export const userRegFlowRoute: Routes = [
    {
        path: 'user-reg-flow',
        component: UserRegFlowComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserRegFlows'
        },
        canActivate: [UserRouteAccessService]
    }
];
