import { BaseEntity } from './../../shared';

export const enum JobType {
    'INTERNSHIP',
    'PARTTIME',
    'FULLTIME',
    'CONTRACT'
}

export class CandidateProfileItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public desiredJobType?: JobType,
        public email?: string,
        public linkedIn?: string,
        public website?: string,
        public github?: string,
        public resumeContentType?: string,
        public resume?: any,
        public userProfileId?: number,
    ) {
    }
}
