/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { CompanyProfileItharmonyComponent } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.component';
import { CompanyProfileItharmonyService } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.service';
import { CompanyProfileItharmony } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CompanyProfileItharmony Management Component', () => {
        let comp: CompanyProfileItharmonyComponent;
        let fixture: ComponentFixture<CompanyProfileItharmonyComponent>;
        let service: CompanyProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CompanyProfileItharmonyComponent],
                providers: [
                    CompanyProfileItharmonyService
                ]
            })
            .overrideTemplate(CompanyProfileItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CompanyProfileItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CompanyProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CompanyProfileItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.companyProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
