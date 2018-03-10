/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { CultureProfileItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony-detail.component';
import { CultureProfileItharmonyService } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.service';
import { CultureProfileItharmony } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CultureProfileItharmony Management Detail Component', () => {
        let comp: CultureProfileItharmonyDetailComponent;
        let fixture: ComponentFixture<CultureProfileItharmonyDetailComponent>;
        let service: CultureProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CultureProfileItharmonyDetailComponent],
                providers: [
                    CultureProfileItharmonyService
                ]
            })
            .overrideTemplate(CultureProfileItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CultureProfileItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CultureProfileItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.cultureProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
