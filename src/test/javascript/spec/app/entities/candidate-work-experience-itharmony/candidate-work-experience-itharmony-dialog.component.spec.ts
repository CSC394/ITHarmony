/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateWorkExperienceItharmonyDialogComponent } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony-dialog.component';
import { CandidateWorkExperienceItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.service';
import { CandidateWorkExperienceItharmony } from '../../../../../../main/webapp/app/entities/candidate-work-experience-itharmony/candidate-work-experience-itharmony.model';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony';

describe('Component Tests', () => {

    describe('CandidateWorkExperienceItharmony Management Dialog Component', () => {
        let comp: CandidateWorkExperienceItharmonyDialogComponent;
        let fixture: ComponentFixture<CandidateWorkExperienceItharmonyDialogComponent>;
        let service: CandidateWorkExperienceItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateWorkExperienceItharmonyDialogComponent],
                providers: [
                    UserProfileExtraItharmonyService,
                    CandidateWorkExperienceItharmonyService
                ]
            })
            .overrideTemplate(CandidateWorkExperienceItharmonyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateWorkExperienceItharmonyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateWorkExperienceItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CandidateWorkExperienceItharmony(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.candidateWorkExperience = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'candidateWorkExperienceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CandidateWorkExperienceItharmony();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.candidateWorkExperience = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'candidateWorkExperienceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
