import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserProfileItharmony } from './user-profile-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserProfileItharmony>;

@Injectable()
export class UserProfileItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/user-profiles';

    constructor(private http: HttpClient) { }

    create(userProfile: UserProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(userProfile);
        return this.http.post<UserProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userProfile: UserProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(userProfile);
        return this.http.put<UserProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserProfileItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserProfileItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserProfileItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserProfileItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserProfileItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserProfileItharmony[]>): HttpResponse<UserProfileItharmony[]> {
        const jsonResponse: UserProfileItharmony[] = res.body;
        const body: UserProfileItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserProfileItharmony.
     */
    private convertItemFromServer(userProfile: UserProfileItharmony): UserProfileItharmony {
        const copy: UserProfileItharmony = Object.assign({}, userProfile);
        return copy;
    }

    /**
     * Convert a UserProfileItharmony to a JSON which can be sent to the server.
     */
    private convert(userProfile: UserProfileItharmony): UserProfileItharmony {
        const copy: UserProfileItharmony = Object.assign({}, userProfile);
        return copy;
    }
}
