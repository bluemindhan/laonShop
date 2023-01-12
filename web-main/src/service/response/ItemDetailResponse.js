import BaseResponse from '@/service/response/BaseResponse';
export default class ItemDetailResponse extends BaseResponse {
    constructor() {
        super();
        this.itemVo = null;
        // this.image = '',
        // this.itemDetail = '';
        // this.itemId = 0;
        // this.likeCnt = 0;
        // this.name = '';
        // this.price = 0;
    }
}