import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ItHarmonyUserProfileItharmonyModule } from './user-profile-itharmony/user-profile-itharmony.module';
import { ItHarmonyCandidateProfileItharmonyModule } from './candidate-profile-itharmony/candidate-profile-itharmony.module';
import { ItHarmonyCompanyProfileItharmonyModule } from './company-profile-itharmony/company-profile-itharmony.module';
import { ItHarmonyCultureProfileItharmonyModule } from './culture-profile-itharmony/culture-profile-itharmony.module';
import { ItHarmonySkillsProfileItharmonyModule } from './skills-profile-itharmony/skills-profile-itharmony.module';
import { ItHarmonyJobPostItharmonyModule } from './job-post-itharmony/job-post-itharmony.module';
import { ItHarmonyCandidateEducationItharmonyModule } from './candidate-education-itharmony/candidate-education-itharmony.module';
import { ItHarmonyCandidateWorkExperienceItharmonyModule } from './candidate-work-experience-itharmony/candidate-work-experience-itharmony.module';
import { ItHarmonyJobMatchItharmonyModule } from './job-match-itharmony/job-match-itharmony.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ItHarmonyUserProfileItharmonyModule,
        ItHarmonyCandidateProfileItharmonyModule,
        ItHarmonyCompanyProfileItharmonyModule,
        ItHarmonyCultureProfileItharmonyModule,
        ItHarmonySkillsProfileItharmonyModule,
        ItHarmonyJobPostItharmonyModule,
        ItHarmonyCandidateEducationItharmonyModule,
        ItHarmonyCandidateWorkExperienceItharmonyModule,
        ItHarmonyJobMatchItharmonyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyEntityModule {}
