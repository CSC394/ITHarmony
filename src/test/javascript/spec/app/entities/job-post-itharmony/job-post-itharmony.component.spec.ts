/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobPostItharmonyComponent } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.component';
import { JobPostItharmonyService } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.service';
import { JobPostItharmony } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.model';

describe('Component Tests', () => {

    describe('JobPostItharmony Management Component', () => {
        let comp: JobPostItharmonyComponent;
        let fixture: ComponentFixture<JobPostItharmonyComponent>;
        let service: JobPostItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobPostItharmonyComponent],
                providers: [
                    JobPostItharmonyService
                ]
            })
            .overrideTemplate(JobPostItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobPostItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobPostItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new JobPostItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.jobPosts[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
