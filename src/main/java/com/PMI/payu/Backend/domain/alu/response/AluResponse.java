
package com.PMI.payu.Backend.domain.alu.response;

import com.PMI.payu.Backend.domain.response.Response;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AluResponse extends Response {

    @Nullable
    private String redirectUri;
    @Nullable
    private String orderId;
    @Nullable
    private String extOrderId;

}



