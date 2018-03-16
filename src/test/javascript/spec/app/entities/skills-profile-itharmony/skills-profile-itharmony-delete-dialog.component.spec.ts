/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { ItHarmonyTestModule } from '../../../test.module';
import { SkillsProfileItharmonyDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony-delete-dialog.component';
import { SkillsProfileItharmonyService } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.service';

describe('Component Tests', () => {

    describe('SkillsProfileItharmony Management Delete Component', () => {
        let comp: SkillsProfileItharmonyDeleteDialogComponent;
        let fixture: ComponentFixture<SkillsProfileItharmonyDeleteDialogComponent>;
        let service: SkillsProfileItharmonyService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [SkillsProfileItharmonyDeleteDialogComponent],
                providers: [
                    SkillsProfileItharmonyService
                ]
            })
            .overrideTemplate(SkillsProfileItharmonyDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SkillsProfileItharmonyDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkillsProfileItharmonyService);
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
