/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ItHarmonyTestModule } from '../../../test.module';
import { CandidateProfileItharmonyComponent } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.component';
import { CandidateProfileItharmonyService } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.service';
import { CandidateProfileItharmony } from '../../../../../../main/webapp/app/entities/candidate-profile-itharmony/candidate-profile-itharmony.model';

describe('Component Tests', () => {

    describe('CandidateProfileItharmony Management Component', () => {
        let comp: CandidateProfileItharmonyComponent;
        let fixture: ComponentFixture<CandidateProfileItharmonyComponent>;
        let service: CandidateProfileItharmonyService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ItHarmonyTestModule],
                declarations: [CandidateProfileItharmonyComponent],
                providers: [
                    CandidateProfileItharmonyService
                ]
            })
            .overrideTemplate(CandidateProfileItharmonyComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CandidateProfileItharmonyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidateProfileItharmonyService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CandidateProfileItharmony(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.candidateProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
