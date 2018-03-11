/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { Userreg2DeleteDialogComponent } from '../../../../../../main/webapp/app/entities/userreg-2/userreg-2-delete-dialog.component';
import { Userreg2Service } from '../../../../../../main/webapp/app/entities/userreg-2/userreg-2.service';

describe('Component Tests', () => {

    describe('Userreg2 Management Delete Component', () => {
        let comp: Userreg2DeleteDialogComponent;
        let fixture: ComponentFixture<Userreg2DeleteDialogComponent>;
        let service: Userreg2Service;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [Userreg2DeleteDialogComponent],
                providers: [
                    Userreg2Service
                ]
            })
            .overrideTemplate(Userreg2DeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(Userreg2DeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Userreg2Service);
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
