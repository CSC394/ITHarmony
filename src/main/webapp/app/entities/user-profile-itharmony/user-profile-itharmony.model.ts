import { BaseEntity } from './../../shared';

export const enum UserType {
    'CANDIDATE',
    'COMPANY',
    'ADMIN'
}

export class UserProfileItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public userType?: UserType,
        public email?: string,
        public password?: string,
        public phone?: string,
        public city?: string,
        public state?: string,
        public profilePictureContentType?: string,
        public profilePicture?: any,
        public bio?: string,
        public cultureProfileId?: number,
        public candidateProfileId?: number,
        public companyProfileId?: number,
        public candidateEducations?: BaseEntity[],
        public candidateWorkExperiences?: BaseEntity[],
        public skillsProfiles?: BaseEntity[],
        public jobPosts?: BaseEntity[],
        public jobMatches?: BaseEntity[],
    ) {
    }
}
