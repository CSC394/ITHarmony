/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { Userreg2Component } from '../../../../../../main/webapp/app/entities/userreg-2/userreg-2.component';
import { Userreg2Service } from '../../../../../../main/webapp/app/entities/userreg-2/userreg-2.service';
import { Userreg2 } from '../../../../../../main/webapp/app/entities/userreg-2/userreg-2.model';

describe('Component Tests', () => {

    describe('Userreg2 Management Component', () => {
        let comp: Userreg2Component;
        let fixture: ComponentFixture<Userreg2Component>;
        let service: Userreg2Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [Userreg2Component],
                providers: [
                    Userreg2Service
                ]
            })
            .overrideTemplate(Userreg2Component, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Userreg2Component);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Userreg2Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Userreg2(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userreg2S[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
