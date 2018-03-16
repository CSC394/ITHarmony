import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    CandidateProfileItharmonyService,
    CandidateProfileItharmonyPopupService,
    CandidateProfileItharmonyComponent,
    CandidateProfileItharmonyDetailComponent,
    CandidateProfileItharmonyDialogComponent,
    CandidateProfileItharmonyPopupComponent,
    CandidateProfileItharmonyDeletePopupComponent,
    CandidateProfileItharmonyDeleteDialogComponent,
    candidateProfileRoute,
    candidateProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...candidateProfileRoute,
    ...candidateProfilePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CandidateProfileItharmonyComponent,
        CandidateProfileItharmonyDetailComponent,
        CandidateProfileItharmonyDialogComponent,
        CandidateProfileItharmonyDeleteDialogComponent,
        CandidateProfileItharmonyPopupComponent,
        CandidateProfileItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        CandidateProfileItharmonyComponent,
        CandidateProfileItharmonyDialogComponent,
        CandidateProfileItharmonyPopupComponent,
        CandidateProfileItharmonyDeleteDialogComponent,
        CandidateProfileItharmonyDeletePopupComponent,
    ],
    providers: [
        CandidateProfileItharmonyService,
        CandidateProfileItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyCandidateProfileItharmonyModule {}
