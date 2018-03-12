import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Principal } from '../../shared';

import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { UserTypeT } from 'src/main/webapp/app/entities/user-profile-extra-itharmony';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { Router } from '@angular/router';

@Component({
    selector: 'jhi-user-reg-flow',
    templateUrl: './user-reg-flow.component.html'
})
export class UserRegFlowComponent implements OnInit, OnDestroy {
    currentAccount: any;

    userProfileExtra: UserProfileExtraItharmony;
    currentRole: any;

    roles = ['CANDIDATE', 'COMPANY'];

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private userProfileExtraService: UserProfileExtraItharmonyService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    ngOnDestroy() {

    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    public setUserRole(user) {
        this.currentRole = user;
    }

    public createUserProfileExtra = () => {
        this.userProfileExtra = new UserProfileExtraItharmony();
        this.userProfileExtraService.find(this.currentAccount.id).subscribe( (res) => {

            if (res.body.id !== this.currentAccount.id) {
                this.userProfileExtra.id = this.currentAccount.id;
                if (this.currentRole === 'Company') {
                    this.userProfileExtra.userTypeT = this.currentRole;
                    console.warn('a');
                } else {
                    this.userProfileExtra.userTypeT = this.currentRole;
                    console.warn('b');
                }

                console.warn(this.userProfileExtra);
                console.warn(this.userProfileExtraService.create(this.userProfileExtra).subscribe((val) => this.router.navigate(['/user-reg-flow2'])));
            } else {
                console.warn('You already have one of these');
                this.router.navigate(['/user-reg-flow2']);
            }

        });
    }
}
