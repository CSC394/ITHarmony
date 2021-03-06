import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<number>;

@Injectable()
export class AlgoServiceService {

  private resourceUrl = SERVER_API_URL + 'api/algo/culture-algo';
  private resourceUrl2 = SERVER_API_URL + 'api/algo/skills-algo';

  constructor(private http: HttpClient) { }

  queryify(a: number[], b: number[]) {
    let s: String = '';
    for (const n of a) {
      if (n === undefined) {
        s = s + 'company=3&';
      } else { s = s + 'company=' + n.toString() + '&'; }
    }
    for (const n of b) {
      if (n === undefined) {
        s = s + 'candidate=3&';
      } else { s = s + 'candidate=' + n.toString() + '&'; }
    }
    return s;
  }

  queryify2(a: string[], b: number[], c: string[], d: number[]) {
    let s: String = '';
    for (const n of a) {
      if (n !== undefined) {
        s = s + 'candidateSkills=' + n.toString() + '&';
      }
    }
    for (const n of b) {
      if (n !== undefined) {
        s = s + 'candidateExperience=' + n.toString() + '&';
      }
    }
    for (const n of c) {
      if (n !== undefined) {
        s = s + 'jobSkills=' + n.toString() + '&';
      }
    }
    for (const n of d) {
      if (n !== undefined) {
        s = s + 'jobExperience=' + n.toString() + '&';
      }
    }
    return s;
  }

  find(a: number[], b: number[]): Observable<EntityResponseType> {
    const qstring = this.queryify(a, b);
    return this.http.get<number>(`${this.resourceUrl}?${qstring}`, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  find2(a: string[], b: number[], c: string[], d: number[]): Observable<EntityResponseType> {
    const qstring = this.queryify2(a, b, c, d);
    return this.http.get<number>(`${this.resourceUrl2}?${qstring}`, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  private convertResponse(res: EntityResponseType): EntityResponseType {
    const body: number = res.body;
    return res.clone({ body });
  }

  /**
   * Convert a returned JSON object to number.
   */
  private convertItemFromServer(num: number): number {
    const copy: number = Object.assign({}, num);
    return copy;
  }
}
