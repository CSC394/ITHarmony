import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ItHarmonyUserProfileExtraItharmonyModule } from './user-profile-extra-itharmony/user-profile-extra-itharmony.module';
import { ItHarmonyCandidateProfileItharmonyModule } from './candidate-profile-itharmony/candidate-profile-itharmony.module';
import { ItHarmonyCompanyProfileItharmonyModule } from './company-profile-itharmony/company-profile-itharmony.module';
import { ItHarmonyCultureProfileItharmonyModule } from './culture-profile-itharmony/culture-profile-itharmony.module';
import { ItHarmonySkillsProfileItharmonyModule } from './skills-profile-itharmony/skills-profile-itharmony.module';
import { ItHarmonyJobPostItharmonyModule } from './job-post-itharmony/job-post-itharmony.module';
import { ItHarmonyCandidateEducationItharmonyModule } from './candidate-education-itharmony/candidate-education-itharmony.module';
import { ItHarmonyCandidateWorkExperienceItharmonyModule } from './candidate-work-experience-itharmony/candidate-work-experience-itharmony.module';
import { ItHarmonyJobMatchItharmonyModule } from './job-match-itharmony/job-match-itharmony.module';
import { ItHarmonyJobCreateFlowItharmonyModule } from './job-create-flow-itharmony/job-create-flow-itharmony.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ItHarmonyUserProfileExtraItharmonyModule,
        ItHarmonyCandidateProfileItharmonyModule,
        ItHarmonyCompanyProfileItharmonyModule,
        ItHarmonyCultureProfileItharmonyModule,
        ItHarmonySkillsProfileItharmonyModule,
        ItHarmonyJobPostItharmonyModule,
        ItHarmonyCandidateEducationItharmonyModule,
        ItHarmonyCandidateWorkExperienceItharmonyModule,
        ItHarmonyJobMatchItharmonyModule,
        ItHarmonyJobCreateFlowItharmonyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyEntityModule {}
