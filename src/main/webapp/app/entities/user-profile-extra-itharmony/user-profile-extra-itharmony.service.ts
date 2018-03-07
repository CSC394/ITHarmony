import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserProfileExtraItharmony } from './user-profile-extra-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserProfileExtraItharmony>;

@Injectable()
export class UserProfileExtraItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/user-profile-extras';

    constructor(private http: HttpClient) { }

    create(userProfileExtra: UserProfileExtraItharmony): Observable<EntityResponseType> {
        const copy = this.convert(userProfileExtra);
        return this.http.post<UserProfileExtraItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userProfileExtra: UserProfileExtraItharmony): Observable<EntityResponseType> {
        const copy = this.convert(userProfileExtra);
        return this.http.put<UserProfileExtraItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserProfileExtraItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserProfileExtraItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserProfileExtraItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserProfileExtraItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserProfileExtraItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserProfileExtraItharmony[]>): HttpResponse<UserProfileExtraItharmony[]> {
        const jsonResponse: UserProfileExtraItharmony[] = res.body;
        const body: UserProfileExtraItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserProfileExtraItharmony.
     */
    private convertItemFromServer(userProfileExtra: UserProfileExtraItharmony): UserProfileExtraItharmony {
        const copy: UserProfileExtraItharmony = Object.assign({}, userProfileExtra);
        return copy;
    }

    /**
     * Convert a UserProfileExtraItharmony to a JSON which can be sent to the server.
     */
    private convert(userProfileExtra: UserProfileExtraItharmony): UserProfileExtraItharmony {
        const copy: UserProfileExtraItharmony = Object.assign({}, userProfileExtra);
        return copy;
    }
}
