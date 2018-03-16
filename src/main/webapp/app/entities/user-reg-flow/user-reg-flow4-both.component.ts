import { Component, OnInit } from '@angular/core';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { UserProfileExtraItharmony, UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony';
import { CultureProfileItharmony } from '../culture-profile-itharmony/culture-profile-itharmony.model';
import { CultureProfileItharmonyService } from '../culture-profile-itharmony/culture-profile-itharmony.service';
import { Principal } from '../../shared/auth/principal.service';
import { User } from '../../shared/user/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-user-reg-flow4-both',
  templateUrl: './user-reg-flow4-both.component.html',
  styles: []
})
export class UserRegFlow4BothComponent implements OnInit {
    isCompany: boolean;
    cultureProfile: CultureProfileItharmony;
    router: Router;
    isSaving: boolean;
    userProfileExtra: UserProfileExtraItharmony;
    currentAccount: User;

  constructor(
    private jhiAlertService: JhiAlertService,
    private cultureProfileService: CultureProfileItharmonyService,
    private userProfileExtraService: UserProfileExtraItharmonyService,
    private eventManager: JhiEventManager,
    private principal: Principal,
    private r: Router
  ) {
      this.cultureProfile = new CultureProfileItharmony();
      this.router = r;
  }

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
                        this.isCompany = this.userProfileExtra.userTypeT.toString() === 'CANDIDATE';
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
    this.cultureProfile.userProfileExtraId = this.userProfileExtra.id;
    if (this.cultureProfile.id !== undefined) {
        console.warn('saving culture profile service first');
        this.subscribeToSaveResponseA(
            this.cultureProfileService.update(this.cultureProfile));
    } else {
        this.subscribeToSaveResponseA(
            this.cultureProfileService.create(this.cultureProfile));
    }
}

private subscribeToSaveResponseA(result: Observable<HttpResponse<CultureProfileItharmony>>) {
    result.subscribe(  (res) => {
        this.userProfileExtra.cultureProfileId = res.body.id;
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

private onSaveSuccess(result: CultureProfileItharmony) {
    this.eventManager.broadcast({ name: 'cultureProfileListModification', content: 'OK'});
    this.isSaving = false;
    if (this.userProfileExtra.userTypeT.toString() === 'CANDIDATE') {
        this.router.navigate(['/user-reg-flow5-candidate']);
    } else {
        this.router.navigate(['/job-post-itharmony']);

    }
}
}
