import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/user/user.model';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { Principal } from '../../shared/auth/principal.service';
import { SkillsProfileItharmony } from '../skills-profile-itharmony/skills-profile-itharmony.model';
import { SkillsProfileItharmonyService } from '../skills-profile-itharmony/skills-profile-itharmony.service';
import { JobMatchItharmonyService } from '../job-match-itharmony/job-match-itharmony.service';
import { CultureProfileItharmonyService } from '../culture-profile-itharmony/culture-profile-itharmony.service';
import { CultureProfileItharmony } from '../culture-profile-itharmony/culture-profile-itharmony.model';
import { CompanyProfileItharmonyService } from '../company-profile-itharmony/company-profile-itharmony.service';
import { AlgoServiceService } from './algo-service.service';
import { JobPostItharmonyService } from '../job-post-itharmony/job-post-itharmony.service';
import { JobPostItharmony } from '../job-post-itharmony/job-post-itharmony.model';
import { JobMatchItharmony } from '../job-match-itharmony/job-match-itharmony.model';

@Component({
    selector: 'jhi-user-reg-flow7-candidate',
    templateUrl: './user-reg-flow7-candidate.component.html',
    styles: [],
    providers: [AlgoServiceService]
})
export class UserRegFlow7CandidateComponent implements OnInit {
    router: Router;
    isSaving: boolean;
    userProfileExtra: UserProfileExtraItharmony;
    currentAccount: User;
    skillsProfile: SkillsProfileItharmony;
    cultureProfile: CultureProfileItharmony;

    constructor(
        private skillProfileService: SkillsProfileItharmonyService,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private jobMatchItharmonyService: JobMatchItharmonyService,
        private jobPostItharmonyService: JobPostItharmonyService,
        private cultureProfileItharmonyService: CultureProfileItharmonyService,
        private companyProfileService: CompanyProfileItharmonyService,
        private algoservice: AlgoServiceService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private r: Router
    ) {
        this.router = r;
        this.skillsProfile = new SkillsProfileItharmony();
    }

    ngOnInit() {
        this.principal.identity().then((u) => {
            this.currentAccount = u;
            this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body) { // client-side filtering, why?
                    if (upe.userId === this.currentAccount.id) {
                        console.warn('Found it!');
                        console.warn(upe.userId + ' ' + this.currentAccount.id + ' ' + upe.userTypeT);
                        this.userProfileExtra = upe;
                        this.cultureProfileItharmonyService.find(this.userProfileExtra.cultureProfileId).subscribe((res2) => {
                            this.cultureProfile = res2.body;
                        });
                        alreadyfound = true;
                        break;
                    }
                }
                if (!alreadyfound) {
                    console.warn('DOES NOT EXIST (bad news)' + this.currentAccount.id);
                }
            }, (rese) => {
                console.warn('ERRRRRRR');
            });

        });
    }

    save() {
        this.isSaving = true;
        this.skillsProfile.userProfileExtraId = this.userProfileExtra.id;
        if (this.skillsProfile.id !== undefined) {
            console.warn('saving work experience service first');
            this.subscribeToSaveResponseA(
                this.skillProfileService.update(this.skillsProfile));
        } else {
            this.subscribeToSaveResponseA(
                this.skillProfileService.create(this.skillsProfile));
        }
    }

    private subscribeToSaveResponseA(result: Observable<HttpResponse<SkillsProfileItharmony>>) {
        result.subscribe((res) => {
            // this.userProfileExtra.skillsProfiles.push(res.body); //this doesn't exist?
            if (this.userProfileExtra.id !== undefined) {
                console.warn('saving successfully (user profile extra) with: ' + this.userProfileExtra);
                this.subscribeToSaveResponse(
                    this.userProfileExtraService.update(this.userProfileExtra));
            } else {
                this.subscribeToSaveResponse(
                    this.userProfileExtraService.create(this.userProfileExtra));
            }
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserProfileExtraItharmony>>) {
        result.subscribe((res: HttpResponse<UserProfileExtraItharmony>) =>
            this.onSaveSuccess(res.body));
    }

    private onSaveSuccess(result: UserProfileExtraItharmony) {
        this.eventManager.broadcast({ name: 'userProfileExtraListModification', content: 'OK' });
        this.isSaving = false;
        console.warn('success, profile complete');
        this.router.navigate(['/user-reg-flow7-candidate']);

        console.warn('running alg now');
        // given skillsprofile and cultureprofile

        this.jobPostItharmonyService.query().subscribe((res2) => {
            for (const jobpost of res2.body) {

                // Do a skillsprofile match
                this.skillProfileService.find(jobpost.skillsProfileId).subscribe((res3) => {
                    let currentCultureMatch: Number = 0;
                    let currentSkillsMatch: Number = 0;
                    const a = this.skillsProfile;
                    const b = res3.body;
                    const inputA = [a.skill1, a.skill2, a.skill3, a.skill4, a.skill5];
                    const inputAXP = [a.skill1XP, a.skill2XP, a.skill3XP, a.skill4XP, a.skill5XP];
                    const inputB = [b.skill1, b.skill2, b.skill3, b.skill4, b.skill5];
                    const inputBXP = [b.skill1XP, b.skill2XP, b.skill3XP, b.skill4XP, b.skill5XP];
                    this.algoservice.find2(inputA, inputAXP, inputB, inputBXP).subscribe((res7) => { currentSkillsMatch = res7.body; });

                    // do a cultureprofile match.
                    this.userProfileExtraService.find(jobpost.userProfileExtraId).subscribe((res4) => { // find who posted this job
                        this.cultureProfileItharmonyService.find(res4.body.cultureProfileId).subscribe((res5) => { // find their culture profile
                            const c = this.cultureProfile;
                            const d = res5.body;
                            const inputC = [c.amenities, c.companysize, c.dresscode, c.floorplan, c.groupWork, c.hours, c.meetings, c.outings, c.pace, c.philanthropy, c.rules];
                            const inputD = [d.amenities, d.companysize, d.dresscode, d.floorplan, d.groupWork, d.hours, d.meetings, d.outings, d.pace, d.philanthropy, d.rules];
                            this.algoservice.find(inputC, inputD).subscribe((res6) => {
                                currentCultureMatch = res6.body;
                                console.log(currentCultureMatch);
                                console.log(currentSkillsMatch);
                                console.log((currentCultureMatch.valueOf() + currentSkillsMatch.valueOf()) / 2);

                                const currentJobMatch: JobMatchItharmony = new JobMatchItharmony();
                                currentJobMatch.jobPostId = jobpost.id;
                                currentJobMatch.userProfileExtraId = this.userProfileExtra.id;
                                currentJobMatch.cultureRank = currentCultureMatch.valueOf();
                                currentJobMatch.skillRank = currentCultureMatch.valueOf();
                                this.jobMatchItharmonyService.create(currentJobMatch);
                            });
                        });
                    });
                });
            }
        });

    }

    private getCultureProfile(id: number) {
        console.warn(id);
        this.cultureProfileItharmonyService.query().subscribe((res) => {
            for (const cultureprofile of res.body) {
                if (cultureprofile.id === id) {
                    console.warn(id);
                    return cultureprofile;
                }
            }
        });
        return null;

    }
}
