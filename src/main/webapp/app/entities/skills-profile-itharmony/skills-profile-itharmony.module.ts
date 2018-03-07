import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    SkillsProfileItharmonyService,
    SkillsProfileItharmonyPopupService,
    SkillsProfileItharmonyComponent,
    SkillsProfileItharmonyDetailComponent,
    SkillsProfileItharmonyDialogComponent,
    SkillsProfileItharmonyPopupComponent,
    SkillsProfileItharmonyDeletePopupComponent,
    SkillsProfileItharmonyDeleteDialogComponent,
    skillsProfileRoute,
    skillsProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...skillsProfileRoute,
    ...skillsProfilePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SkillsProfileItharmonyComponent,
        SkillsProfileItharmonyDetailComponent,
        SkillsProfileItharmonyDialogComponent,
        SkillsProfileItharmonyDeleteDialogComponent,
        SkillsProfileItharmonyPopupComponent,
        SkillsProfileItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        SkillsProfileItharmonyComponent,
        SkillsProfileItharmonyDialogComponent,
        SkillsProfileItharmonyPopupComponent,
        SkillsProfileItharmonyDeleteDialogComponent,
        SkillsProfileItharmonyDeletePopupComponent,
    ],
    providers: [
        SkillsProfileItharmonyService,
        SkillsProfileItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonySkillsProfileItharmonyModule {}
