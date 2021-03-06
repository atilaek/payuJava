package com.PMI.payu.Backend.domain.common.response;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Status {
    @Nullable
    private String statusCode;

    @Nullable
    private String severity;

    @Nullable
    private String code;

    @Nullable
    private String codeLiteral;

    @Nullable
    private String statusDesc;
}
