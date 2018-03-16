import { Component, OnInit } from '@angular/core';
import { User } from '../../shared/user/user.model';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { Principal } from '../../shared/auth/principal.service';
import { CandidateEducationItharmony } from '../candidate-education-itharmony/candidate-education-itharmony.model';
import { CandidateEducationItharmonyService } from '../candidate-education-itharmony/candidate-education-itharmony.service';

@Component({
  selector: 'jhi-user-reg-flow5-candidate',
  templateUrl: './user-reg-flow5-candidate.component.html',
  styles: []
})
export class UserRegFlow5CandidateComponent implements OnInit {
    router: Router;
    isSaving: boolean;
    userProfileExtra: UserProfileExtraItharmony;
    currentAccount: User;
    candidateEducation: CandidateEducationItharmony;

  constructor(
    private eduProfileService: CandidateEducationItharmonyService,
    private userProfileExtraService: UserProfileExtraItharmonyService,
    private eventManager: JhiEventManager,
    private principal: Principal,
    private r: Router
    ) { this.router = r;
        this.candidateEducation = new CandidateEducationItharmony(); }

  ngOnInit() {
    this.principal.identity().then((u) => {
        this.currentAccount = u;
        this.userProfileExtraService.query().subscribe((res) => {
                let alreadyfound = false;
                for (const upe of res.body){ // client-side filtering, why?
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
        }, (rese) => { console.warn('ERRRRRRR');
        });

    } );
  }

save() {
    this.isSaving = true;
    this.candidateEducation.userProfileExtraId = this.userProfileExtra.id;
    if (this.candidateEducation.id !== undefined) {
        console.warn('saving education profile service first');
        this.subscribeToSaveResponseA(
            this.eduProfileService.update(this.candidateEducation));
    } else {
        this.subscribeToSaveResponseA(
            this.eduProfileService.create(this.candidateEducation));
    }
}

private subscribeToSaveResponseA(result: Observable<HttpResponse<CandidateEducationItharmony>>) {
    result.subscribe(  (res) => {
        // this.userProfileExtra.candidateEducations.push(res.body); // This doesn't exist I guess?
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
    this.eventManager.broadcast({ name: 'userProfileExtraListModification', content: 'OK'});
    this.isSaving = false;
    console.warn('success, navigating to part 6');
    this.router.navigate(['/user-reg-flow6-candidate']);
}
}
