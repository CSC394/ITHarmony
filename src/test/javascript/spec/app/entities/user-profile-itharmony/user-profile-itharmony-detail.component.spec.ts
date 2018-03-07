/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony-detail.component';
import { UserProfileItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.service';
import { UserProfileItharmony } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.model';

describe('Component Tests', () => {

    describe('UserProfileItharmony Management Detail Component', () => {
        let comp: UserProfileItharmonyDetailComponent;
        let fixture: ComponentFixture<UserProfileItharmonyDetailComponent>;
        let service: UserProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileItharmonyDetailComponent],
                providers: [
                    UserProfileItharmonyService
                ]
            })
            .overrideTemplate(UserProfileItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UserProfileItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
