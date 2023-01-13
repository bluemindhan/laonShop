import BaseResponse from '@/service/response/BaseResponse';
export default class ItemDetailResponse extends BaseResponse {
    constructor() {
        super();
        this.itemVo = null;
        // this.commentVoList = null;
        // this.imageVoList = null;
    }
}