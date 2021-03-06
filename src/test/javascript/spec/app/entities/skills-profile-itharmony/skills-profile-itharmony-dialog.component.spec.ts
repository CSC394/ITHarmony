/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { SkillsProfileItharmonyDialogComponent } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony-dialog.component';
import { SkillsProfileItharmonyService } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.service';
import { SkillsProfileItharmony } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.model';
import { JobPostItharmonyService } from '../../../../../../main/webapp/app/entities/job-post-itharmony';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony';

describe('Component Tests', () => {

    describe('SkillsProfileItharmony Management Dialog Component', () => {
        let comp: SkillsProfileItharmonyDialogComponent;
        let fixture: ComponentFixture<SkillsProfileItharmonyDialogComponent>;
        let service: SkillsProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [SkillsProfileItharmonyDialogComponent],
                providers: [
                    JobPostItharmonyService,
                    UserProfileExtraItharmonyService,
                    SkillsProfileItharmonyService
                ]
            })
            .overrideTemplate(SkillsProfileItharmonyDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SkillsProfileItharmonyDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkillsProfileItharmonyService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SkillsProfileItharmony(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.skillsProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'skillsProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SkillsProfileItharmony();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.skillsProfile = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'skillsProfileListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
