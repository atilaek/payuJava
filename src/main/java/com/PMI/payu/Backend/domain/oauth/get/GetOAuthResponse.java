package com.PMI.payu.Backend.domain.oauth.get;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetOAuthResponse {

    @Nullable
    private String access_token;
    @Nullable
    private String token_type;
    @Nullable
    private Integer expires_in;
    @Nullable
    private String grant_type;
    @Nullable
    private String error;
    @Nullable
    private String error_description;

    public boolean isFailure() {
        return error != null;
    }
}