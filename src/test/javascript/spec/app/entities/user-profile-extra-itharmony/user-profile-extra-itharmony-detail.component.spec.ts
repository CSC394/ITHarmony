/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileExtraItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony-detail.component';
import { UserProfileExtraItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../../../../../main/webapp/app/entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';

describe('Component Tests', () => {

    describe('UserProfileExtraItharmony Management Detail Component', () => {
        let comp: UserProfileExtraItharmonyDetailComponent;
        let fixture: ComponentFixture<UserProfileExtraItharmonyDetailComponent>;
        let service: UserProfileExtraItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileExtraItharmonyDetailComponent],
                providers: [
                    UserProfileExtraItharmonyService
                ]
            })
            .overrideTemplate(UserProfileExtraItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileExtraItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileExtraItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UserProfileExtraItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userProfileExtra).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
