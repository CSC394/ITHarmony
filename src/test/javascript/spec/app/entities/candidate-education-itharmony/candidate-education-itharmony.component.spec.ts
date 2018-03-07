/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateEducationItharmonyComponent } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.component';
import { CandidateEducationItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.service';
import { CandidateEducationItharmony } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateEducationItharmony Management Component', () => {
        let comp: CandidateEducationItharmonyComponent;
        let fixture: ComponentFixture<CandidateEducationItharmonyComponent>;
        let service: CandidateEducationItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateEducationItharmonyComponent],
                providers: [
                    CandidateEducationItharmonyService
                ]
            })
            .overrideTemplate(CandidateEducationItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateEducationItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateEducationItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CandidateEducationItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.candidateEducations[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
