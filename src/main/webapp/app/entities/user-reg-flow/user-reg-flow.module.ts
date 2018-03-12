import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    UserRegFlowComponent,
    userRegFlowRoute,
} from './';
import { UserRegFlow2Component } from './user-reg-flow2.component';
import { UserRegFlow3CompanyComponent } from './user-reg-flow3-company.component';
import { UserRegFlow3CandidateComponent } from './user-reg-flow3-candidate.component';
import { UserRegFlow4BothComponent } from './user-reg-flow4-both.component';

const ENTITY_STATES = [
    ...userRegFlowRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserRegFlowComponent,
        UserRegFlow2Component,
        UserRegFlow3CompanyComponent,
        UserRegFlow3CandidateComponent,
        UserRegFlow4BothComponent,
    ],
    entryComponents: [
        UserRegFlowComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyUserRegFlowModule {}
