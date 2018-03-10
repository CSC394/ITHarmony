import { BaseEntity } from './../../shared';

export const enum JobType {
    'INTERNSHIP',
    'PARTTIME',
    'FULLTIME',
    'CONTRACT'
}

export class JobPostItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public positionTitle?: string,
        public positionType?: JobType,
        public description?: string,
        public city?: string,
        public state?: string,
        public startDate?: any,
        public degreeRequired?: boolean,
        public minimumGPA?: number,
        public skillsProfileId?: number,
        public jobMatches?: BaseEntity[],
        public userProfileExtraId?: number,
    ) {
        this.degreeRequired = false;
    }
}
