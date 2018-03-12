import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Principal } from '../../shared';

import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { UserTypeT } from 'src/main/webapp/app/entities/user-profile-extra-itharmony';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { Router } from '@angular/router';
import { User } from '../../shared/user/user.model';

@Component({
    selector: 'jhi-user-reg-flow',
    templateUrl: './user-reg-flow.component.html'
})
export class UserRegFlowComponent implements OnInit, OnDestroy {
    currentAccount: User;

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

    public setUserRole(r) {
        this.currentRole = r;
    }

    public createUserProfileExtra = () => {
        this.userProfileExtra = new UserProfileExtraItharmony();
        this.userProfileExtraService.query().subscribe((res) => {
            let alreadyfound = false;
            for (const upe of res.body){ // client-side filtering, why?
                if (upe.userId === this.currentAccount.id) {
                    console.warn('ALREADY EXISTS');
                    console.warn(upe.userId + ' ' + this.currentAccount.id + ' ' + upe.userTypeT);
                    alreadyfound = true;
                    break;
                }
            }
            if (!alreadyfound) {
                console.warn('DOES NOT EXIST' + ' ' + this.currentAccount.id);
                this.userProfileExtra.userId = this.currentAccount.id;
                this.userProfileExtra.userTypeT = this.currentRole;
                console.warn(this.userProfileExtra);
                console.warn(this.userProfileExtraService.create(this.userProfileExtra).subscribe((val) => this.router.navigate(['/user-reg-flow2'])));
            }

        }, (rese) => { console.warn('ERRRRRRR');
        });

    }
}
