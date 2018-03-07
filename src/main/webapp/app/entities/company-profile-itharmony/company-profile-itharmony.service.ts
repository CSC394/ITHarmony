import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CompanyProfileItharmony } from './company-profile-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CompanyProfileItharmony>;

@Injectable()
export class CompanyProfileItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/company-profiles';

    constructor(private http: HttpClient) { }

    create(companyProfile: CompanyProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(companyProfile);
        return this.http.post<CompanyProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(companyProfile: CompanyProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(companyProfile);
        return this.http.put<CompanyProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CompanyProfileItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CompanyProfileItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<CompanyProfileItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CompanyProfileItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CompanyProfileItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CompanyProfileItharmony[]>): HttpResponse<CompanyProfileItharmony[]> {
        const jsonResponse: CompanyProfileItharmony[] = res.body;
        const body: CompanyProfileItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CompanyProfileItharmony.
     */
    private convertItemFromServer(companyProfile: CompanyProfileItharmony): CompanyProfileItharmony {
        const copy: CompanyProfileItharmony = Object.assign({}, companyProfile);
        return copy;
    }

    /**
     * Convert a CompanyProfileItharmony to a JSON which can be sent to the server.
     */
    private convert(companyProfile: CompanyProfileItharmony): CompanyProfileItharmony {
        const copy: CompanyProfileItharmony = Object.assign({}, companyProfile);
        return copy;
    }
}
