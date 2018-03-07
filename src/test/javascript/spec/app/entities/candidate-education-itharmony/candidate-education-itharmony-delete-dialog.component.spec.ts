/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateEducationItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony-delete-dialog.component';
import { CandidateEducationItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-education-itharmony/candidate-education-itharmony.service';

describe('Component Tests', () => {

    describe('CandidateEducationItharmony Management Delete Component', () => {
        let comp: CandidateEducationItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<CandidateEducationItharmonyDeleteDialogComponent>;
        let service: CandidateEducationItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateEducationItharmonyDeleteDialogComponent],
                providers: [
                    CandidateEducationItharmonyService
                ]
            })
            .overrideTemplate(CandidateEducationItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateEducationItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateEducationItharmonyService);
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
