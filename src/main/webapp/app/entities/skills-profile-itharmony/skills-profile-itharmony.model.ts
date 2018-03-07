import { BaseEntity } from './../../shared';

export const enum Specialty {
    'SOFTWARE_ENGINEERING',
    'WEB_DEVELOPMENT',
    'INFORMATION_TECHNOLOGY',
    'MOBILE_DEVELOPMENT',
    'DESIGN',
    'DATA_SCIENCE'
}

export class SkillsProfileItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public forCandidate?: boolean,
        public specialty?: Specialty,
        public specialtyXP?: number,
        public skill1?: string,
        public skill2?: string,
        public skill3?: string,
        public skill4?: string,
        public skill5?: string,
        public skill1XP?: number,
        public skill2XP?: number,
        public skill3XP?: number,
        public skill4XP?: number,
        public skill5XP?: number,
        public jobPostId?: number,
        public userProfileId?: number,
    ) {
        this.forCandidate = false;
    }
}
