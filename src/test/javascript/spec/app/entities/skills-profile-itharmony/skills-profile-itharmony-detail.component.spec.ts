/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { SkillsProfileItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony-detail.component';
import { SkillsProfileItharmonyService } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.service';
import { SkillsProfileItharmony } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.model';

describe('Component Tests', () => {

    describe('SkillsProfileItharmony Management Detail Component', () => {
        let comp: SkillsProfileItharmonyDetailComponent;
        let fixture: ComponentFixture<SkillsProfileItharmonyDetailComponent>;
        let service: SkillsProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [SkillsProfileItharmonyDetailComponent],
                providers: [
                    SkillsProfileItharmonyService
                ]
            })
            .overrideTemplate(SkillsProfileItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SkillsProfileItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkillsProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SkillsProfileItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.skillsProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
