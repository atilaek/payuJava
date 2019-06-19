package com.PMI.payu.Backend.domain.common.response;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response {

    @Nullable
    private Status status;

    public boolean isFailure() {
        final String statusCode = status.getStatusCode();
        return !statusCode.isEmpty() && statusCode.equals("SUCCESS");
    }
}
