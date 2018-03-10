/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobMatchItharmonyComponent } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.component';
import { JobMatchItharmonyService } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.service';
import { JobMatchItharmony } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.model';

describe('Component Tests', () => {

    describe('JobMatchItharmony Management Component', () => {
        let comp: JobMatchItharmonyComponent;
        let fixture: ComponentFixture<JobMatchItharmonyComponent>;
        let service: JobMatchItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobMatchItharmonyComponent],
                providers: [
                    JobMatchItharmonyService
                ]
            })
            .overrideTemplate(JobMatchItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobMatchItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobMatchItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new JobMatchItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.jobMatches[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
