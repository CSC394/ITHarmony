import { Component, OnInit } from '@angular/core';
import { Principal } from '../../shared/auth/principal.service';
import { User } from 'src/main/webapp/app/shared';
import { JhiEventManager } from 'ng-jhipster';
import { CompanyProfileItharmonyService } from '../company-profile-itharmony/company-profile-itharmony.service';
import { UserProfileExtraItharmonyService } from '../user-profile-extra-itharmony/user-profile-extra-itharmony.service';
import { UserProfileExtraItharmony } from '../../entities/user-profile-extra-itharmony/user-profile-extra-itharmony.model';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { CompanyProfileItharmony } from '../company-profile-itharmony/company-profile-itharmony.model';

@Component({
  selector: 'jhi-user-reg-flow3-company',
  templateUrl: './user-reg-flow3-company.component.html',
  styles: []
})
export class UserRegFlow3CompanyComponent implements OnInit {
    router: Router;
    isSaving: boolean;
    userProfileExtra: UserProfileExtraItharmony;
    currentAccount: User;
    companyProfile: CompanyProfileItharmony;

  constructor(
    private companyProfileService: CompanyProfileItharmonyService,
    private userProfileExtraService: UserProfileExtraItharmonyService,
    private eventManager: JhiEventManager,
    private principal: Principal,
    private r: Router
    ) { this.router = r;
        this.companyProfile = new CompanyProfileItharmony(); }

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
        if (this.companyProfile.id !== undefined) {
            console.warn('saving company profile service first');
            this.subscribeToSaveResponseA(
                this.companyProfileService.update(this.companyProfile));
        } else {
            this.subscribeToSaveResponseA(
                this.companyProfileService.create(this.companyProfile));
        }
    }

    private subscribeToSaveResponseA(result: Observable<HttpResponse<CompanyProfileItharmony>>) {
        result.subscribe(  (res) => {
            this.userProfileExtra.companyProfileId = res.body.id;
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
