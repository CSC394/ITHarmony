import { BaseEntity } from './../../shared';

export const enum UserTypeT {
    'CANDIDATE',
    'COMPANY',
    'ADMIN'
}

export class UserProfileExtraItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public userTypeT?: UserTypeT,
        public phone?: string,
        public city?: string,
        public state?: string,
        public bio?: string,
        public cultureProfileId?: number,
        public candidateProfileId?: number,
        public companyProfileId?: number,
        public candidateEducations?: BaseEntity[],
        public candidateWorkExperiences?: BaseEntity[],
        public skillsProfiles?: BaseEntity[],
        public jobPosts?: BaseEntity[],
        public jobMatches?: BaseEntity[],
        public userId?: number,
    ) {
    }
}
