import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    UserRegFlowComponent,
    userRegFlowRoute,
} from './';

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
    ],
    entryComponents: [
        UserRegFlowComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyUserRegFlowModule {}
