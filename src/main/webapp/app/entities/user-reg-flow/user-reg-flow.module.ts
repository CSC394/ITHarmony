import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    UserRegFlowComponent,
    userRegFlowRoute,
} from './';
import { UserRegFlow2Component } from './user-reg-flow2.component';

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
    ],
    entryComponents: [
        UserRegFlowComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyUserRegFlowModule {}
