/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobPostItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony-detail.component';
import { JobPostItharmonyService } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.service';
import { JobPostItharmony } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.model';

describe('Component Tests', () => {

    describe('JobPostItharmony Management Detail Component', () => {
        let comp: JobPostItharmonyDetailComponent;
        let fixture: ComponentFixture<JobPostItharmonyDetailComponent>;
        let service: JobPostItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobPostItharmonyDetailComponent],
                providers: [
                    JobPostItharmonyService
                ]
            })
            .overrideTemplate(JobPostItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobPostItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobPostItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new JobPostItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.jobPost).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
