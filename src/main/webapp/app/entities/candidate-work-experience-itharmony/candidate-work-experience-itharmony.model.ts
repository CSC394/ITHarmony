import { BaseEntity } from './../../shared';

export class CandidateWorkExperienceItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public companyName?: string,
        public position?: string,
        public currentJob?: boolean,
        public startDate?: any,
        public endDate?: any,
        public description?: string,
        public userProfileId?: number,
    ) {
        this.currentJob = false;
    }
}
