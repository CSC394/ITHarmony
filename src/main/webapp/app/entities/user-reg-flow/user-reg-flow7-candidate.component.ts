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

@Component({
    selector: 'jhi-user-reg-flow7-candidate',
    templateUrl: './user-reg-flow7-candidate.component.html',
    styles: []
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
        private cultureProfileItharmonyService: CultureProfileItharmonyService,
        private companyProfileService: CompanyProfileItharmonyService,
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
            this.cultureProfileItharmonyService.query().subscribe((res) => {
                for (const cult of res.body) {
                    if (cult.userProfileExtraId === this.userProfileExtra.id) {
                        this.cultureProfile = cult;
                        break;
                    }
                }
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

        // for each company:
        this.companyProfileService.query().subscribe((res) => {
            for (const company of res.body) {
                const currentCultureProfile: CultureProfileItharmony = this.getCultureProfile(company.userProfileExtraId);


            }
        })
        // pull company's cultureprofile
        // for each job:
        // pull job's skillsprofile
    }

    private getCultureProfile(companyid: number) {
        this.cultureProfileItharmonyService.query().subscribe((res) => {
            for (const cultureprofile of res.body) {
                if (cultureprofile.userProfileExtraId === companyid) {
                    return cultureprofile;
                }
            }
        });
        return null;

    }
}
