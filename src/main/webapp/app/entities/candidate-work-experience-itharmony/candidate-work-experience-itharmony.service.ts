import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CandidateWorkExperienceItharmony } from './candidate-work-experience-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CandidateWorkExperienceItharmony>;

@Injectable()
export class CandidateWorkExperienceItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/candidate-work-experiences';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(candidateWorkExperience: CandidateWorkExperienceItharmony):
        Observable<EntityResponseType> {
        const copy = this.convert(candidateWorkExperience);
        return this.http.post<CandidateWorkExperienceItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(candidateWorkExperience: CandidateWorkExperienceItharmony):
        Observable<EntityResponseType> {
        const copy = this.convert(candidateWorkExperience);
        return this.http.put<CandidateWorkExperienceItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CandidateWorkExperienceItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CandidateWorkExperienceItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<CandidateWorkExperienceItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CandidateWorkExperienceItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CandidateWorkExperienceItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CandidateWorkExperienceItharmony[]>): HttpResponse<CandidateWorkExperienceItharmony[]> {
        const jsonResponse: CandidateWorkExperienceItharmony[] = res.body;
        const body: CandidateWorkExperienceItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CandidateWorkExperienceItharmony.
     */
    private convertItemFromServer(candidateWorkExperience: CandidateWorkExperienceItharmony): CandidateWorkExperienceItharmony {
        const copy: CandidateWorkExperienceItharmony = Object.assign({}, candidateWorkExperience);
        copy.startDate = this.dateUtils
            .convertLocalDateFromServer(candidateWorkExperience.startDate);
        copy.endDate = this.dateUtils
            .convertLocalDateFromServer(candidateWorkExperience.endDate);
        return copy;
    }

    /**
     * Convert a CandidateWorkExperienceItharmony to a JSON which can be sent to the server.
     */
    private convert(candidateWorkExperience: CandidateWorkExperienceItharmony): CandidateWorkExperienceItharmony {
        const copy: CandidateWorkExperienceItharmony = Object.assign({}, candidateWorkExperience);
        copy.startDate = this.dateUtils
            .convertLocalDateToServer(candidateWorkExperience.startDate);
        copy.endDate = this.dateUtils
            .convertLocalDateToServer(candidateWorkExperience.endDate);
        return copy;
    }
}
