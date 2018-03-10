/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { CompanyProfileItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony-detail.component';
import { CompanyProfileItharmonyService } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.service';
import { CompanyProfileItharmony } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CompanyProfileItharmony Management Detail Component', () => {
        let comp: CompanyProfileItharmonyDetailComponent;
        let fixture: ComponentFixture<CompanyProfileItharmonyDetailComponent>;
        let service: CompanyProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CompanyProfileItharmonyDetailComponent],
                providers: [
                    CompanyProfileItharmonyService
                ]
            })
            .overrideTemplate(CompanyProfileItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CompanyProfileItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CompanyProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CompanyProfileItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.companyProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
