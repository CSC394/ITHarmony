import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    CandidateWorkExperienceItharmonyService,
    CandidateWorkExperienceItharmonyPopupService,
    CandidateWorkExperienceItharmonyComponent,
    CandidateWorkExperienceItharmonyDetailComponent,
    CandidateWorkExperienceItharmonyDialogComponent,
    CandidateWorkExperienceItharmonyPopupComponent,
    CandidateWorkExperienceItharmonyDeletePopupComponent,
    CandidateWorkExperienceItharmonyDeleteDialogComponent,
    candidateWorkExperienceRoute,
    candidateWorkExperiencePopupRoute,
} from './';

const ENTITY_STATES = [
    ...candidateWorkExperienceRoute,
    ...candidateWorkExperiencePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CandidateWorkExperienceItharmonyComponent,
        CandidateWorkExperienceItharmonyDetailComponent,
        CandidateWorkExperienceItharmonyDialogComponent,
        CandidateWorkExperienceItharmonyDeleteDialogComponent,
        CandidateWorkExperienceItharmonyPopupComponent,
        CandidateWorkExperienceItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        CandidateWorkExperienceItharmonyComponent,
        CandidateWorkExperienceItharmonyDialogComponent,
        CandidateWorkExperienceItharmonyPopupComponent,
        CandidateWorkExperienceItharmonyDeleteDialogComponent,
        CandidateWorkExperienceItharmonyDeletePopupComponent,
    ],
    providers: [
        CandidateWorkExperienceItharmonyService,
        CandidateWorkExperienceItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyCandidateWorkExperienceItharmonyModule {}
