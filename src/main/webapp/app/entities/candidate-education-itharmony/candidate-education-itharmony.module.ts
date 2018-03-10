import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    CandidateEducationItharmonyService,
    CandidateEducationItharmonyPopupService,
    CandidateEducationItharmonyComponent,
    CandidateEducationItharmonyDetailComponent,
    CandidateEducationItharmonyDialogComponent,
    CandidateEducationItharmonyPopupComponent,
    CandidateEducationItharmonyDeletePopupComponent,
    CandidateEducationItharmonyDeleteDialogComponent,
    candidateEducationRoute,
    candidateEducationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...candidateEducationRoute,
    ...candidateEducationPopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CandidateEducationItharmonyComponent,
        CandidateEducationItharmonyDetailComponent,
        CandidateEducationItharmonyDialogComponent,
        CandidateEducationItharmonyDeleteDialogComponent,
        CandidateEducationItharmonyPopupComponent,
        CandidateEducationItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        CandidateEducationItharmonyComponent,
        CandidateEducationItharmonyDialogComponent,
        CandidateEducationItharmonyPopupComponent,
        CandidateEducationItharmonyDeleteDialogComponent,
        CandidateEducationItharmonyDeletePopupComponent,
    ],
    providers: [
        CandidateEducationItharmonyService,
        CandidateEducationItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyCandidateEducationItharmonyModule {}
