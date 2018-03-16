/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { CompanyProfileItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony-delete-dialog.component';
import { CompanyProfileItharmonyService } from '../../../../../../main/webapp/app/entities/company-profile-itharmony/company-profile-itharmony.service';

describe('Component Tests', () => {

    describe('CompanyProfileItharmony Management Delete Component', () => {
        let comp: CompanyProfileItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<CompanyProfileItharmonyDeleteDialogComponent>;
        let service: CompanyProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CompanyProfileItharmonyDeleteDialogComponent],
                providers: [
                    CompanyProfileItharmonyService
                ]
            })
            .overrideTemplate(CompanyProfileItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CompanyProfileItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CompanyProfileItharmonyService);
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
