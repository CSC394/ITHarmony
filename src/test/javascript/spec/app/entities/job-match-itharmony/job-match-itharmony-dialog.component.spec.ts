/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobMatchItharmonyDialogComponent } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony-dialog.component';
import { JobMatchItharmonyService } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.service';
import { JobMatchItharmony } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.model';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony';
import { JobPostItharmonyService } from '../../../../../../main/webapp/app/entities/job-post-itharmony';

describe('Component Tests', () => {

    describe('JobMatchItharmony Management Dialog Component', () => {
        let comp: JobMatchItharmonyDialogComponent;
        let fixture: ComponentFixture<JobMatchItharmonyDialogComponent>;
        let service: JobMatchItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobMatchItharmonyDialogComponent],
                providers: [
                    UserProfileExtraItharmonyService,
                    JobPostItharmonyService,
                    JobMatchItharmonyService
                ]
            })
            .overrideTemplate(JobMatchItharmonyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobMatchItharmonyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobMatchItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JobMatchItharmony(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.jobMatch = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'jobMatchListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new JobMatchItharmony();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.jobMatch = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'jobMatchListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
