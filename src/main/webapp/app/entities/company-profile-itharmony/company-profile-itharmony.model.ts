import { BaseEntity } from './../../shared';

export class CompanyProfileItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public companyName?: string,
        public postings?: number,
        public email?: string,
        public industry?: string,
        public userProfileId?: number,
    ) {
    }
}
