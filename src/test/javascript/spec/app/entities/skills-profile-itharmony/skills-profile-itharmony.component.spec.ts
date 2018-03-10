/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { SkillsProfileItharmonyComponent } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.component';
import { SkillsProfileItharmonyService } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.service';
import { SkillsProfileItharmony } from '../../../../../../main/webapp/app/entities/skills-profile-itharmony/skills-profile-itharmony.model';

describe('Component Tests', () => {

    describe('SkillsProfileItharmony Management Component', () => {
        let comp: SkillsProfileItharmonyComponent;
        let fixture: ComponentFixture<SkillsProfileItharmonyComponent>;
        let service: SkillsProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [SkillsProfileItharmonyComponent],
                providers: [
                    SkillsProfileItharmonyService
                ]
            })
            .overrideTemplate(SkillsProfileItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SkillsProfileItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SkillsProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SkillsProfileItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.skillsProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
