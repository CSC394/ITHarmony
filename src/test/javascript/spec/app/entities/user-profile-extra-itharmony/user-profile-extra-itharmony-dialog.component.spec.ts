/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileExtraItharmonyDialogComponent } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony-dialog.component';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { CultureProfileItharmonyService } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony';
import { CandidateProfileItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony';
import { CompanyProfileItharmonyService } from '../../../../../../main/webapp/app/entities/company-profile-itharmony';
import { UserService } from '../../../../../../main/webapp/app/shared';

describe('Component Tests', () => {

    describe('UserProfileExtraItharmony Management Dialog Component', () => {
        let comp: UserProfileExtraItharmonyDialogComponent;
        let fixture: ComponentFixture<UserProfileExtraItharmonyDialogComponent>;
        let service: UserProfileExtraItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileExtraItharmonyDialogComponent],
                providers: [
                    CultureProfileItharmonyService,
                    CandidateProfileItharmonyService,
                    CompanyProfileItharmonyService,
                    UserService,
                    UserProfileExtraItharmonyService
                ]
            })
            .overrideTemplate(UserProfileExtraItharmonyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileExtraItharmonyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileExtraItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserProfileExtraItharmony(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userProfileExtra = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userProfileExtraListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserProfileExtraItharmony();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userProfileExtra = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userProfileExtraListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
