import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    JobCreateFlowItharmonyComponent,
    jobCreateFlowItharmonyRoute,
} from './';

const ENTITY_STATES = [
    ...jobCreateFlowItharmonyRoute
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JobCreateFlowItharmonyComponent,
    ],
    entryComponents: [
        JobCreateFlowItharmonyComponent,
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyJobCreateFlowItharmonyModule {}
