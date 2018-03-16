import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<number>;

@Injectable()
export class AlgoServiceService {

  private resourceUrl = SERVER_API_URL + 'api/algo/culture-algo';

  constructor(private http: HttpClient) { }

  queryify(a: number[], b: number[]) {
    let s: String = '';
    for (const n of a) {
      s = s + 'company=' + n.toString() + '&';
    }
    for (const n of b) {
      s = s + 'candidate=' + n.toString() + '&';
    }
    return s;
  }

  find(a: number[], b: number[]): Observable<EntityResponseType> {
    const qstring = this.queryify(a, b);
    return this.http.get<number>(`${this.resourceUrl}?${qstring}`, { observe: 'response' })
      .map((res: EntityResponseType) => this.convertResponse(res));
  }

  private convertResponse(res: EntityResponseType): EntityResponseType {
    console.warn(res.body);
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
