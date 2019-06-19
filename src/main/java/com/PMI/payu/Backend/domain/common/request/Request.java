package com.PMI.payu.Backend.domain.common.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Request {

    @NonNull
    private String orderId;

}
