import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { SkillsProfileItharmony } from './skills-profile-itharmony.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SkillsProfileItharmony>;

@Injectable()
export class SkillsProfileItharmonyService {

    private resourceUrl =  SERVER_API_URL + 'api/skills-profiles';

    constructor(private http: HttpClient) { }

    create(skillsProfile: SkillsProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(skillsProfile);
        return this.http.post<SkillsProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(skillsProfile: SkillsProfileItharmony): Observable<EntityResponseType> {
        const copy = this.convert(skillsProfile);
        return this.http.put<SkillsProfileItharmony>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SkillsProfileItharmony>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SkillsProfileItharmony[]>> {
        const options = createRequestOption(req);
        return this.http.get<SkillsProfileItharmony[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SkillsProfileItharmony[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SkillsProfileItharmony = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SkillsProfileItharmony[]>): HttpResponse<SkillsProfileItharmony[]> {
        const jsonResponse: SkillsProfileItharmony[] = res.body;
        const body: SkillsProfileItharmony[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SkillsProfileItharmony.
     */
    private convertItemFromServer(skillsProfile: SkillsProfileItharmony): SkillsProfileItharmony {
        const copy: SkillsProfileItharmony = Object.assign({}, skillsProfile);
        return copy;
    }

    /**
     * Convert a SkillsProfileItharmony to a JSON which can be sent to the server.
     */
    private convert(skillsProfile: SkillsProfileItharmony): SkillsProfileItharmony {
        const copy: SkillsProfileItharmony = Object.assign({}, skillsProfile);
        return copy;
    }
}
