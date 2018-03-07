/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { CultureProfileItharmonyComponent } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.component';
import { CultureProfileItharmonyService } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.service';
import { CultureProfileItharmony } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CultureProfileItharmony Management Component', () => {
        let comp: CultureProfileItharmonyComponent;
        let fixture: ComponentFixture<CultureProfileItharmonyComponent>;
        let service: CultureProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CultureProfileItharmonyComponent],
                providers: [
                    CultureProfileItharmonyService
                ]
            })
            .overrideTemplate(CultureProfileItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CultureProfileItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CultureProfileItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.cultureProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
