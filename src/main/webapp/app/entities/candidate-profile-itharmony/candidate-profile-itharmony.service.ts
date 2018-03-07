import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CandidateProfileItharmony } from './candidate-profile-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CandidateProfileItharmony>;

@Injectable()
export class CandidateProfileItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/candidate-profiles';

    constructor(private http: HttpClient) { }

    create(candidateProfile: CandidateProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(candidateProfile);
        return this.http.post<CandidateProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(candidateProfile: CandidateProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(candidateProfile);
        return this.http.put<CandidateProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CandidateProfileItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CandidateProfileItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<CandidateProfileItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CandidateProfileItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CandidateProfileItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CandidateProfileItharmony[]>): HttpResponse<CandidateProfileItharmony[]> {
        const jsonResponse: CandidateProfileItharmony[] = res.body;
        const body: CandidateProfileItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CandidateProfileItharmony.
     */
    private convertItemFromServer(candidateProfile: CandidateProfileItharmony): CandidateProfileItharmony {
        const copy: CandidateProfileItharmony = Object.assign({}, candidateProfile);
        return copy;
    }

    /**
     * Convert a CandidateProfileItharmony to a JSON which can be sent to the server.
     */
    private convert(candidateProfile: CandidateProfileItharmony): CandidateProfileItharmony {
        const copy: CandidateProfileItharmony = Object.assign({}, candidateProfile);
        return copy;
    }
}
