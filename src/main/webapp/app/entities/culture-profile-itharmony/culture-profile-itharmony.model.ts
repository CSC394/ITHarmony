import { BaseEntity } from './../../shared';

export class CultureProfileItharmony implements BaseEntity {
    constructor(
        public id?: number,
        public dresscode?: number,
        public companysize?: number,
        public floorplan?: number,
        public hours?: number,
        public groupWork?: number,
        public pace?: number,
        public rules?: number,
        public amenities?: number,
        public meetings?: number,
        public philanthropy?: number,
        public outings?: number,
        public userProfileExtraId?: number,
    ) {
    }
}
