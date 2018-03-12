import { Component, OnInit } from '@angular/core';

import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { UserTypeT } from 'src/main/webapp/app/entities/user-profile-extra-itharmony';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserService } from '../../shared/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { User, Principal } from '../../shared';

@Component({
  selector: 'jhi-user-reg-flow2',
  templateUrl: './user-reg-flow2.component.html',
  styles: []
})
export class UserRegFlow2Component implements OnInit {

  subscription: any;
  user: User;
  usertype: UserTypeT;

  constructor(
      private userService: UserService,
      private route: ActivatedRoute,
      private userProfileExtraItharmonyService: UserProfileExtraItharmonyService,
      private principal: Principal
  ) { }

  ngOnInit() {
    this.principal.identity().then((u) => {
        this.user = u;
        this.userProfileExtraItharmonyService.find(this.user.id).subscribe((res) => { this.usertype = res.body.userTypeT; console.warn(this.usertype); });

    } );
  }

}
