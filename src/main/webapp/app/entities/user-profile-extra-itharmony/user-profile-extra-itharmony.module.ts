import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    UserProfileExtraItharmonyService,
    UserProfileExtraItharmonyPopupService,
    UserProfileExtraItharmonyComponent,
    UserProfileExtraItharmonyDetailComponent,
    UserProfileExtraItharmonyDialogComponent,
    UserProfileExtraItharmonyPopupComponent,
    UserProfileExtraItharmonyDeletePopupComponent,
    UserProfileExtraItharmonyDeleteDialogComponent,
    userProfileExtraRoute,
    userProfileExtraPopupRoute,
} from './';

const ENTITY_STATES = [
    ...userProfileExtraRoute,
    ...userProfileExtraPopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserProfileExtraItharmonyComponent,
        UserProfileExtraItharmonyDetailComponent,
        UserProfileExtraItharmonyDialogComponent,
        UserProfileExtraItharmonyDeleteDialogComponent,
        UserProfileExtraItharmonyPopupComponent,
        UserProfileExtraItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        UserProfileExtraItharmonyComponent,
        UserProfileExtraItharmonyDialogComponent,
        UserProfileExtraItharmonyPopupComponent,
        UserProfileExtraItharmonyDeleteDialogComponent,
        UserProfileExtraItharmonyDeletePopupComponent,
    ],
    providers: [
        UserProfileExtraItharmonyService,
        UserProfileExtraItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyUserProfileExtraItharmonyModule {}
