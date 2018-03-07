import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    UserProfileItharmonyService,
    UserProfileItharmonyPopupService,
    UserProfileItharmonyComponent,
    UserProfileItharmonyDetailComponent,
    UserProfileItharmonyDialogComponent,
    UserProfileItharmonyPopupComponent,
    UserProfileItharmonyDeletePopupComponent,
    UserProfileItharmonyDeleteDialogComponent,
    userProfileRoute,
    userProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...userProfileRoute,
    ...userProfilePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserProfileItharmonyComponent,
        UserProfileItharmonyDetailComponent,
        UserProfileItharmonyDialogComponent,
        UserProfileItharmonyDeleteDialogComponent,
        UserProfileItharmonyPopupComponent,
        UserProfileItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        UserProfileItharmonyComponent,
        UserProfileItharmonyDialogComponent,
        UserProfileItharmonyPopupComponent,
        UserProfileItharmonyDeleteDialogComponent,
        UserProfileItharmonyDeletePopupComponent,
    ],
    providers: [
        UserProfileItharmonyService,
        UserProfileItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyUserProfileItharmonyModule {}
