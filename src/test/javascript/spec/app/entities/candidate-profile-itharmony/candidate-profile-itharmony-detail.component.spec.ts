/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateProfileItharmonyDetailComponent } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony-detail.component';
import { CandidateProfileItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.service';
import { CandidateProfileItharmony } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateProfileItharmony Management Detail Component', () => {
        let comp: CandidateProfileItharmonyDetailComponent;
        let fixture: ComponentFixture<CandidateProfileItharmonyDetailComponent>;
        let service: CandidateProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateProfileItharmonyDetailComponent],
                providers: [
                    CandidateProfileItharmonyService
                ]
            })
            .overrideTemplate(CandidateProfileItharmonyDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateProfileItharmonyDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CandidateProfileItharmony(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.candidateProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
