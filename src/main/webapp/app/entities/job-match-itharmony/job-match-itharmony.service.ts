import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JobMatchItharmony } from './job-match-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<JobMatchItharmony>;

@Injectable()
export class JobMatchItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/job-matches';

    constructor(private http: HttpClient) { }

    create(jobMatch: JobMatchItharmony): Observable<EntityResponseType> {
        const copy = this.convert(jobMatch);
        return this.http.post<JobMatchItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(jobMatch: JobMatchItharmony): Observable<EntityResponseType> {
        const copy = this.convert(jobMatch);
        return this.http.put<JobMatchItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<JobMatchItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<JobMatchItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<JobMatchItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<JobMatchItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: JobMatchItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<JobMatchItharmony[]>): HttpResponse<JobMatchItharmony[]> {
        const jsonResponse: JobMatchItharmony[] = res.body;
        const body: JobMatchItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to JobMatchItharmony.
     */
    private convertItemFromServer(jobMatch: JobMatchItharmony): JobMatchItharmony {
        const copy: JobMatchItharmony = Object.assign({}, jobMatch);
        return copy;
    }

    /**
     * Convert a JobMatchItharmony to a JSON which can be sent to the server.
     */
    private convert(jobMatch: JobMatchItharmony): JobMatchItharmony {
        const copy: JobMatchItharmony = Object.assign({}, jobMatch);
        return copy;
    }
}
