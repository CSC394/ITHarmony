import { Component, OnInit } from '@angular/core';
import { CandidateProfileItharmony } from '../candidate-profile-itharmony/candidate-profile-itharmony.model';
import { User } from '../../shared/user/user.model';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { CandidateProfileItharmonyService } from '../candidate-profile-itharmony/candidate-profile-itharmony.service';
import { JhiEventManager } from 'ng-jhipster';
import { Principal } from '../../shared/auth/principal.service';

@Component({
  selector: 'jhi-user-reg-flow3-candidate',
  templateUrl: './user-reg-flow3-candidate.component.html',
  styles: []
})
export class UserRegFlow3CandidateComponent implements OnInit {
    router: Router;
    isSaving: boolean;
    userProfileExtra: UserProfileExtraItharmony;
    currentAccount: User;
    candidateProfile: CandidateProfileItharmony;

  constructor(
    private candidateProfileService: CandidateProfileItharmonyService,
    private userProfileExtraService: UserProfileExtraItharmonyService,
    private eventManager: JhiEventManager,
    private principal: Principal,
    private r: Router
    ) { this.router = r;
        this.candidateProfile = new CandidateProfileItharmony(); }

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
    if (this.candidateProfile.id !== undefined) {
        console.warn('saving candidate profile service first');
        this.subscribeToSaveResponseA(
            this.candidateProfileService.update(this.candidateProfile));
    } else {
        this.subscribeToSaveResponseA(
            this.candidateProfileService.create(this.candidateProfile));
    }
}

private subscribeToSaveResponseA(result: Observable<HttpResponse<CandidateProfileItharmony>>) {
    result.subscribe(  (res) => {
        this.userProfileExtra.candidateProfileId = res.body.id;
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
    console.warn('success, navigating to part 4');
    this.router.navigate(['/user-reg-flow4-both']);
}
}
