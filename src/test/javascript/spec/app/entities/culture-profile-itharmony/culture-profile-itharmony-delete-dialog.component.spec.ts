/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { CultureProfileItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony-delete-dialog.component';
import { CultureProfileItharmonyService } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony/culture-profile-itharmony.service';

describe('Component Tests', () => {

    describe('CultureProfileItharmony Management Delete Component', () => {
        let comp: CultureProfileItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<CultureProfileItharmonyDeleteDialogComponent>;
        let service: CultureProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CultureProfileItharmonyDeleteDialogComponent],
                providers: [
                    CultureProfileItharmonyService
                ]
            })
            .overrideTemplate(CultureProfileItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CultureProfileItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CultureProfileItharmonyService);
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
