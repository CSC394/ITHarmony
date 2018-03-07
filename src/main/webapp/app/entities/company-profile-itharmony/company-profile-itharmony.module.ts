import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ItHarmonySharedModule } from '../../shared';
import {
    CompanyProfileItharmonyService,
    CompanyProfileItharmonyPopupService,
    CompanyProfileItharmonyComponent,
    CompanyProfileItharmonyDetailComponent,
    CompanyProfileItharmonyDialogComponent,
    CompanyProfileItharmonyPopupComponent,
    CompanyProfileItharmonyDeletePopupComponent,
    CompanyProfileItharmonyDeleteDialogComponent,
    companyProfileRoute,
    companyProfilePopupRoute,
} from './';

const ENTITY_STATES = [
    ...companyProfileRoute,
    ...companyProfilePopupRoute,
];

@NgModule({
    imports: [
        ItHarmonySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CompanyProfileItharmonyComponent,
        CompanyProfileItharmonyDetailComponent,
        CompanyProfileItharmonyDialogComponent,
        CompanyProfileItharmonyDeleteDialogComponent,
        CompanyProfileItharmonyPopupComponent,
        CompanyProfileItharmonyDeletePopupComponent,
    ],
    entryComponents: [
        CompanyProfileItharmonyComponent,
        CompanyProfileItharmonyDialogComponent,
        CompanyProfileItharmonyPopupComponent,
        CompanyProfileItharmonyDeleteDialogComponent,
        CompanyProfileItharmonyDeletePopupComponent,
    ],
    providers: [
        CompanyProfileItharmonyService,
        CompanyProfileItharmonyPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ItHarmonyCompanyProfileItharmonyModule {}
