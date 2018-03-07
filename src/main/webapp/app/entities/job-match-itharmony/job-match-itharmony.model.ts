import { BaseEntity } from './../../shared';

export class JobMatchItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public skillRank?: number,
        public cultureRank?: number,
        public active?: boolean,
        public candidateApplied?: boolean,
        public companyRejected?: boolean,
        public userProfileExtraId?: number,
        public jobPostId?: number,
    ) {
        this.active = false;
        this.candidateApplied = false;
        this.companyRejected = false;
    }
}
