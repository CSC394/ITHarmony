import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    JobPostItharmonyService,
    JobPostItharmonyPopupService,
    JobPostItharmonyComponent,
    JobPostItharmonyDetailComponent,
    JobPostItharmonyDialogComponent,
    JobPostItharmonyPopupComponent,
    JobPostItharmonyDeletePopupComponent,
    JobPostItharmonyDeleteDialogComponent,
    jobPostRoute,
    jobPostPopupRoute,
} from './';

const ENTITY_STATES = [
    ...jobPostRoute,
    ...jobPostPopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        JobPostItharmonyComponent,
        JobPostItharmonyDetailComponent,
        JobPostItharmonyDialogComponent,
        JobPostItharmonyDeleteDialogComponent,
        JobPostItharmonyPopupComponent,
        JobPostItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        JobPostItharmonyComponent,
        JobPostItharmonyDialogComponent,
        JobPostItharmonyPopupComponent,
        JobPostItharmonyDeleteDialogComponent,
        JobPostItharmonyDeletePopupComponent,
    ],
    providers: [
        JobPostItharmonyService,
        JobPostItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyJobPostItharmonyModule {}
