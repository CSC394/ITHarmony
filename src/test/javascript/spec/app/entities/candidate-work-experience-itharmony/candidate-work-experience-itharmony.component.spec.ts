/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateWorkExperienceItharmonyComponent } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.component';
import { CandidateWorkExperienceItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.service';
import { CandidateWorkExperienceItharmony } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateWorkExperienceItharmony Management Component', () => {
        let comp: CandidateWorkExperienceItharmonyComponent;
        let fixture: ComponentFixture<CandidateWorkExperienceItharmonyComponent>;
        let service: CandidateWorkExperienceItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateWorkExperienceItharmonyComponent],
                providers: [
                    CandidateWorkExperienceItharmonyService
                ]
            })
            .overrideTemplate(CandidateWorkExperienceItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateWorkExperienceItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateWorkExperienceItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CandidateWorkExperienceItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.candidateWorkExperiences[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
