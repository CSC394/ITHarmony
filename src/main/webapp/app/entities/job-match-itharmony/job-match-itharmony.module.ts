import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    JobMatchItharmonyService,
    JobMatchItharmonyPopupService,
    JobMatchItharmonyComponent,
    JobMatchItharmonyDetailComponent,
    JobMatchItharmonyDialogComponent,
    JobMatchItharmonyPopupComponent,
    JobMatchItharmonyDeletePopupComponent,
    JobMatchItharmonyDeleteDialogComponent,
    jobMatchRoute,
    jobMatchPopupRoute,
} from './';

const ENTITY_STATES = [
    ...jobMatchRoute,
    ...jobMatchPopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JobMatchItharmonyComponent,
        JobMatchItharmonyDetailComponent,
        JobMatchItharmonyDialogComponent,
        JobMatchItharmonyDeleteDialogComponent,
        JobMatchItharmonyPopupComponent,
        JobMatchItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        JobMatchItharmonyComponent,
        JobMatchItharmonyDialogComponent,
        JobMatchItharmonyPopupComponent,
        JobMatchItharmonyDeleteDialogComponent,
        JobMatchItharmonyDeletePopupComponent,
    ],
    providers: [
        JobMatchItharmonyService,
        JobMatchItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyJobMatchItharmonyModule {}
