/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileExtraItharmonyComponent } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.component';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';

describe('Component Tests', () => {

    describe('UserProfileExtraItharmony Management Component', () => {
        let comp: UserProfileExtraItharmonyComponent;
        let fixture: ComponentFixture<UserProfileExtraItharmonyComponent>;
        let service: UserProfileExtraItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileExtraItharmonyComponent],
                providers: [
                    UserProfileExtraItharmonyService
                ]
            })
            .overrideTemplate(UserProfileExtraItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileExtraItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileExtraItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UserProfileExtraItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userProfileExtras[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
