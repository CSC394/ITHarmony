/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobPostItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony-delete-dialog.component';
import { JobPostItharmonyService } from '../../../../../../main/webapp/app/entities/job-post-itharmony/job-post-itharmony.service';

describe('Component Tests', () => {

    describe('JobPostItharmony Management Delete Component', () => {
        let comp: JobPostItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<JobPostItharmonyDeleteDialogComponent>;
        let service: JobPostItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobPostItharmonyDeleteDialogComponent],
                providers: [
                    JobPostItharmonyService
                ]
            })
            .overrideTemplate(JobPostItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobPostItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobPostItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
