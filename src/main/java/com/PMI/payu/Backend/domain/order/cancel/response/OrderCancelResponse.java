package com.PMI.payu.Backend.domain.order.cancel.response;

import com.PMI.payu.Backend.domain.common.response.Response;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OrderCancelResponse extends Response {

    @Nullable
    private String orderId;
    @Nullable
    private String extOrderId;

}



