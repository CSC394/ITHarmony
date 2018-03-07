import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { JobPostItharmony } from './job-post-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<JobPostItharmony>;

@Injectable()
export class JobPostItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/job-posts';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(jobPost: JobPostItharmony): Observable<EntityResponseType> {
        const copy = this.convert(jobPost);
        return this.http.post<JobPostItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(jobPost: JobPostItharmony): Observable<EntityResponseType> {
        const copy = this.convert(jobPost);
        return this.http.put<JobPostItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<JobPostItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<JobPostItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<JobPostItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<JobPostItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: JobPostItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<JobPostItharmony[]>): HttpResponse<JobPostItharmony[]> {
        const jsonResponse: JobPostItharmony[] = res.body;
        const body: JobPostItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to JobPostItharmony.
     */
    private convertItemFromServer(jobPost: JobPostItharmony): JobPostItharmony {
        const copy: JobPostItharmony = Object.assign({}, jobPost);
        copy.startDate = this.dateUtils
            .convertLocalDateFromServer(jobPost.startDate);
        return copy;
    }

    /**
     * Convert a JobPostItharmony to a JSON which can be sent to the server.
     */
    private convert(jobPost: JobPostItharmony): JobPostItharmony {
        const copy: JobPostItharmony = Object.assign({}, jobPost);
        copy.startDate = this.dateUtils
            .convertLocalDateToServer(jobPost.startDate);
        return copy;
    }
}
