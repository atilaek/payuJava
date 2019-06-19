package com.PMI.payu.Backend.domain.order.create.response;

import com.PMI.payu.Backend.domain.common.response.Response;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OrderCreateResponse extends Response {

    @Nullable
    private String redirectUri;
    @Nullable
    private String orderId;
    @Nullable
    private String extOrderId;
}



