import BaseResponse from '@/service/response/BaseResponse';
export default class GetProductDetailResponse extends BaseResponse {
    constructor() {
        super();
        this.productVo = null;
        this.productImageVoList = null;
    }
}