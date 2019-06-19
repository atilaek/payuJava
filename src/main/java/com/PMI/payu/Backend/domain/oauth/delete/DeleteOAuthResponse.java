package com.PMI.payu.Backend.domain.oauth.delete;

import lombok.*;
import org.springframework.lang.Nullable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteOAuthResponse {
    @Nullable
    private String status;
    @Nullable
    private String message;
}