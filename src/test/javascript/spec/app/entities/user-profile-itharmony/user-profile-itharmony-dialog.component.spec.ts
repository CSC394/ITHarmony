/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileItharmonyDialogComponent } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony-dialog.component';
import { UserProfileItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.service';
import { UserProfileItharmony } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.model';
import { CultureProfileItharmonyService } from '../../../../../../main/webapp/app/entities/culture-profile-itharmony';
import { CandidateProfileItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony';
import { CompanyProfileItharmonyService } from '../../../../../../main/webapp/app/entities/company-profile-itharmony';

describe('Component Tests', () => {

    describe('UserProfileItharmony Management Dialog Component', () => {
        let comp: UserProfileItharmonyDialogComponent;
        let fixture: ComponentFixture<UserProfileItharmonyDialogComponent>;
        let service: UserProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileItharmonyDialogComponent],
                providers: [
                    CultureProfileItharmonyService,
                    CandidateProfileItharmonyService,
                    CompanyProfileItharmonyService,
                    UserProfileItharmonyService
                ]
            })
            .overrideTemplate(UserProfileItharmonyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileItharmonyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserProfileItharmony(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserProfileItharmony();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
