import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    CultureProfileItharmonyService,
    CultureProfileItharmonyPopupService,
    CultureProfileItharmonyComponent,
    CultureProfileItharmonyDetailComponent,
    CultureProfileItharmonyDialogComponent,
    CultureProfileItharmonyPopupComponent,
    CultureProfileItharmonyDeletePopupComponent,
    CultureProfileItharmonyDeleteDialogComponent,
    cultureProfileRoute,
    cultureProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...cultureProfileRoute,
    ...cultureProfilePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CultureProfileItharmonyComponent,
        CultureProfileItharmonyDetailComponent,
        CultureProfileItharmonyDialogComponent,
        CultureProfileItharmonyDeleteDialogComponent,
        CultureProfileItharmonyPopupComponent,
        CultureProfileItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        CultureProfileItharmonyComponent,
        CultureProfileItharmonyDialogComponent,
        CultureProfileItharmonyPopupComponent,
        CultureProfileItharmonyDeleteDialogComponent,
        CultureProfileItharmonyDeletePopupComponent,
    ],
    providers: [
        CultureProfileItharmonyService,
        CultureProfileItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyCultureProfileItharmonyModule {}
