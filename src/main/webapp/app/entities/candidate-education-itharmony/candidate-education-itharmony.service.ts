import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CandidateEducationItharmony } from './candidate-education-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CandidateEducationItharmony>;

@Injectable()
export class CandidateEducationItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/candidate-educations';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(candidateEducation: CandidateEducationItharmony): Observable<EntityResponseType> {
        const copy = this.convert(candidateEducation);
        return this.http.post<CandidateEducationItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(candidateEducation: CandidateEducationItharmony): Observable<EntityResponseType> {
        const copy = this.convert(candidateEducation);
        return this.http.put<CandidateEducationItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CandidateEducationItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CandidateEducationItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<CandidateEducationItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CandidateEducationItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CandidateEducationItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CandidateEducationItharmony[]>): HttpResponse<CandidateEducationItharmony[]> {
        const jsonResponse: CandidateEducationItharmony[] = res.body;
        const body: CandidateEducationItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CandidateEducationItharmony.
     */
    private convertItemFromServer(candidateEducation: CandidateEducationItharmony): CandidateEducationItharmony {
        const copy: CandidateEducationItharmony = Object.assign({}, candidateEducation);
        copy.graduationDate = this.dateUtils
            .convertLocalDateFromServer(candidateEducation.graduationDate);
        return copy;
    }

    /**
     * Convert a CandidateEducationItharmony to a JSON which can be sent to the server.
     */
    private convert(candidateEducation: CandidateEducationItharmony): CandidateEducationItharmony {
        const copy: CandidateEducationItharmony = Object.assign({}, candidateEducation);
        copy.graduationDate = this.dateUtils
            .convertLocalDateToServer(candidateEducation.graduationDate);
        return copy;
    }
}
