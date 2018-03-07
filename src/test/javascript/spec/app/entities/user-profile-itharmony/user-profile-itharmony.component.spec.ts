/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { UserProfileItharmonyComponent } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.component';
import { UserProfileItharmonyService } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.service';
import { UserProfileItharmony } from '../../../../../../main/webapp/app/entities/user-profile-itharmony/user-profile-itharmony.model';

describe('Component Tests', () => {

    describe('UserProfileItharmony Management Component', () => {
        let comp: UserProfileItharmonyComponent;
        let fixture: ComponentFixture<UserProfileItharmonyComponent>;
        let service: UserProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [UserProfileItharmonyComponent],
                providers: [
                    UserProfileItharmonyService
                ]
            })
            .overrideTemplate(UserProfileItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UserProfileItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
