import { BaseEntity } from './../../shared';

export const enum DegreeType {
    'ASSOCIATES',
    'BACHELOR',
    'MASTER',
    'DOCTORATE'
}

export class CandidateEducationItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public schoolName?: string,
        public degreeType?: DegreeType,
        public inProgress?: boolean,
        public graduationDate?: any,
        public major?: string,
        public gpa?: number,
        public userProfileId?: number,
    ) {
        this.inProgress = false;
    }
}
