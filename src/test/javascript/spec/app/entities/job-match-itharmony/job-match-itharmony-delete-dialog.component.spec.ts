/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { JobMatchItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony-delete-dialog.component';
import { JobMatchItharmonyService } from '../../../../../../main/webapp/app/entities/job-match-itharmony/job-match-itharmony.service';

describe('Component Tests', () => {

    describe('JobMatchItharmony Management Delete Component', () => {
        let comp: JobMatchItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<JobMatchItharmonyDeleteDialogComponent>;
        let service: JobMatchItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [JobMatchItharmonyDeleteDialogComponent],
                providers: [
                    JobMatchItharmonyService
                ]
            })
            .overrideTemplate(JobMatchItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(JobMatchItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobMatchItharmonyService);
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
