
package com.PMI.payu.Backend.domain.auth;

import org.apache.http.NameValuePair;
import org.springframework.lang.Nullable;

import java.util.List;

public class AuthResponse {

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
            if (error.isEmpty() || error_description.isEmpty()) {
                return false;
            }
        return true;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AuthResponse() {
    }

    /**
     *
     * @param token_type
     * @param access_token
     * @param grant_type
     * @param expires_in
     */
    public AuthResponse(String access_token, String token_type, Integer expires_in, String grant_type,
                       String error, String error_description) {
        super();
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.grant_type = grant_type;
        this.error = error;
        this.error_description = error_description;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Nullable
    public String getError() {
        return error;
    }

    public void setError(@Nullable String error) {
        this.error = error;
    }

    @Nullable
    public String getError_description() {
        return error_description;
    }

    public void setError_description(@Nullable String error_description) {
        this.error_description = error_description;
    }
}