import { Component, OnInit } from '@angular/core';

import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserService } from '../../shared/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { User, Principal } from '../../shared';
import { UserTypeT } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.model';

@Component({
  selector: 'jhi-user-reg-flow2',
  templateUrl: './user-reg-flow2.component.html',
  styles: []
})
export class UserRegFlow2Component implements OnInit {

  subscription: any;
  currentAccount: User;
  userProfileExtra: UserProfileExtraItharmony;
  isCompany: boolean;

  constructor(
      private userService: UserService,
      private route: ActivatedRoute,
      private userProfileExtraService: UserProfileExtraItharmonyService,
      private principal: Principal
  ) { }

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

}
