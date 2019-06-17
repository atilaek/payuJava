package com.PMI.payu.Backend.domain.response;

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
        if (status.getStatusCode().equals("SUCCESS")) {
            return false;
        }
        return true;
    }
}
