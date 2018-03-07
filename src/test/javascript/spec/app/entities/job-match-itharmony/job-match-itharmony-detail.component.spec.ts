/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobMatchItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony-detail.component';
import { JobMatchItharmonyService } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.service';
import { JobMatchItharmony } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.model';

describe('Component Tests', () => {

    describe('JobMatchItharmony Management Detail Component', () => {
        let comp: JobMatchItharmonyDetailComponent;
        let fixture: ComponentFixture<JobMatchItharmonyDetailComponent>;
        let service: JobMatchItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobMatchItharmonyDetailComponent],
                providers: [
                    JobMatchItharmonyService
                ]
            })
            .overrideTemplate(JobMatchItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobMatchItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobMatchItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new JobMatchItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.jobMatch).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
