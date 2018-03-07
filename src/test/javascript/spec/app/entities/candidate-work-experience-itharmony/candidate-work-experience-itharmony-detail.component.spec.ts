/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateWorkExperienceItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony-detail.component';
import { CandidateWorkExperienceItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.service';
import { CandidateWorkExperienceItharmony } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateWorkExperienceItharmony Management Detail Component', () => {
        let comp: CandidateWorkExperienceItharmonyDetailComponent;
        let fixture: ComponentFixture<CandidateWorkExperienceItharmonyDetailComponent>;
        let service: CandidateWorkExperienceItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateWorkExperienceItharmonyDetailComponent],
                providers: [
                    CandidateWorkExperienceItharmonyService
                ]
            })
            .overrideTemplate(CandidateWorkExperienceItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateWorkExperienceItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateWorkExperienceItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CandidateWorkExperienceItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.candidateWorkExperience).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
