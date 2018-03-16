import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CultureProfileItharmony } from './culture-profile-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CultureProfileItharmony>;

@Injectable()
export class CultureProfileItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/culture-profiles';

    constructor(private http: HttpClient) { }

    create(cultureProfile: CultureProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(cultureProfile);
        return this.http.post<CultureProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(cultureProfile: CultureProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(cultureProfile);
        return this.http.put<CultureProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CultureProfileItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CultureProfileItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<CultureProfileItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CultureProfileItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CultureProfileItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CultureProfileItharmony[]>): HttpResponse<CultureProfileItharmony[]> {
        const jsonResponse: CultureProfileItharmony[] = res.body;
        const body: CultureProfileItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CultureProfileItharmony.
     */
    private convertItemFromServer(cultureProfile: CultureProfileItharmony): CultureProfileItharmony {
        const copy: CultureProfileItharmony = Object.assign({}, cultureProfile);
        return copy;
    }

    /**
     * Convert a CultureProfileItharmony to a JSON which can be sent to the server.
     */
    private convert(cultureProfile: CultureProfileItharmony): CultureProfileItharmony {
        const copy: CultureProfileItharmony = Object.assign({}, cultureProfile);
        return copy;
    }
}
