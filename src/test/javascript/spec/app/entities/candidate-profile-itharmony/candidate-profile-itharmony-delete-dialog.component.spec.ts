/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateProfileItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony-delete-dialog.component';
import { CandidateProfileItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.service';

describe('Component Tests', () => {

    describe('CandidateProfileItharmony Management Delete Component', () => {
        let comp: CandidateProfileItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<CandidateProfileItharmonyDeleteDialogComponent>;
        let service: CandidateProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateProfileItharmonyDeleteDialogComponent],
                providers: [
                    CandidateProfileItharmonyService
                ]
            })
            .overrideTemplate(CandidateProfileItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateProfileItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateProfileItharmonyService);
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
