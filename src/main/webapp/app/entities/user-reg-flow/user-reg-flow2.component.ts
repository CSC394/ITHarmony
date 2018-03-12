import { Component, OnInit } from '@angular/core';

import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserService } from '../../shared/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { User, Principal } from '../../shared';
import { UserTypeT } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { JhiEventManager } from 'ng-jhipster';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-user-reg-flow2',
  templateUrl: './user-reg-flow2.component.html',
  styles: []
})
export class UserRegFlow2Component implements OnInit {
  isSaving: boolean;

  subscription: any;
  currentAccount: User;
  userProfileExtra: UserProfileExtraItharmony;
  isCompany: boolean;
  router: Router;

  constructor(
      private userService: UserService,
      private route: ActivatedRoute,
      private userProfileExtraService: UserProfileExtraItharmonyService,
      private principal: Principal,
      private eventManager: JhiEventManager,
      private r: Router
  ) {
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
                        break;
                    }
                }
                if (!alreadyfound) {
                    console.warn('DOES NOT EXIST (bad news)' + this.currentAccount.id);
                } else {
                    this.isCompany = this.userProfileExtra.userTypeT.toString() === 'COMPANY';
                }
        }, (rese) => { console.warn('ERRRRRRR');
        });

    } );
  }

    save() {
        this.isSaving = true;
        if (this.userProfileExtra.id !== undefined) {
            console.warn('saving successfully with: ' + this.userProfileExtra);
            this.subscribeToSaveResponse(
                this.userProfileExtraService.update(this.userProfileExtra));
        } else {
            this.subscribeToSaveResponse(
                this.userProfileExtraService.create(this.userProfileExtra));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserProfileExtraItharmony>>) {
        result.subscribe((res: HttpResponse<UserProfileExtraItharmony>) =>
            this.onSaveSuccess(res.body));
    }

    private onSaveSuccess(result: UserProfileExtraItharmony) {
        this.eventManager.broadcast({ name: 'userProfileExtraListModification', content: 'OK'});
        this.isSaving = false;
        console.warn('success, navigating to part 3');
        if (this.isCompany) {
            // this.router.navigate(['/user-reg-flow3-company']);
        } else {
            // this.router.navigate(['/user-reg-flow3-candidate']);
        }
    }
}
