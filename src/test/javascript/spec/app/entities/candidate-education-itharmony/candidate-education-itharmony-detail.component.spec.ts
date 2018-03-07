/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateEducationItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony-detail.component';
import { CandidateEducationItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.service';
import { CandidateEducationItharmony } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateEducationItharmony Management Detail Component', () => {
        let comp: CandidateEducationItharmonyDetailComponent;
        let fixture: ComponentFixture<CandidateEducationItharmonyDetailComponent>;
        let service: CandidateEducationItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateEducationItharmonyDetailComponent],
                providers: [
                    CandidateEducationItharmonyService
                ]
            })
            .overrideTemplate(CandidateEducationItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateEducationItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateEducationItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CandidateEducationItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.candidateEducation).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
