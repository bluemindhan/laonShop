import BaseResponse from '@/service/response/BaseResponse';
export default class GetItemsResponse extends BaseResponse {
    constructor() {
        super();
        this.totalCount = 0;
        this.pageNo = 0;
        this.pageSize = 0;
        this.products = null;
    }
}