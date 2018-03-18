import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JobCreateFlowItharmonyComponent } from './job-create-flow-itharmony.component';

export const jobCreateFlowItharmonyRoute: Routes = [
    {
        path: 'job-create-flow-itharmony',
        component: JobCreateFlowItharmonyComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Job Creation'
        },
        canActivate: [UserRouteAccessService]
    }
    ]
;
