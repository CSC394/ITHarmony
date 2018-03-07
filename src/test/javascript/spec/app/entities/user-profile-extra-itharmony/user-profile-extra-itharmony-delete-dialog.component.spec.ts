/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileExtraItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony-delete-dialog.component';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.service';

describe('Component Tests', () => {

    describe('UserProfileExtraItharmony Management Delete Component', () => {
        let comp: UserProfileExtraItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<UserProfileExtraItharmonyDeleteDialogComponent>;
        let service: UserProfileExtraItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileExtraItharmonyDeleteDialogComponent],
                providers: [
                    UserProfileExtraItharmonyService
                ]
            })
            .overrideTemplate(UserProfileExtraItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileExtraItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileExtraItharmonyService);
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
