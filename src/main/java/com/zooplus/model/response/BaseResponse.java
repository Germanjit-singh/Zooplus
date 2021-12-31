package com.zooplus.model.response;

import com.zooplus.enums.RequestStatus;
import lombok.Data;
/**
 * @author germanjit singh version 1.0
 * */
@Data
public class BaseResponse {
    private String errorMsg;
    private RequestStatus status = RequestStatus.SUCCESS;

    public BaseResponse(String errorMsg, RequestStatus status) {
        this.errorMsg = errorMsg;
        this.status = status;
    }

    public BaseResponse() {
    }
}
