/**
 * All rights reserved -- Copyright 2015 Gluu Inc.
 */
package org.gluu.oxd.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.StringUtils;
import org.gluu.oxauth.model.common.SubjectType;
import org.gluu.oxauth.model.crypto.encryption.BlockEncryptionAlgorithm;
import org.gluu.oxauth.model.crypto.encryption.KeyEncryptionAlgorithm;
import org.gluu.oxauth.model.crypto.signature.SignatureAlgorithm;

import java.util.Arrays;

/**
 * @author Yuriy Zabrovarnyy
 * @version 0.9, 09/08/2013
 */

public enum ErrorResponseCode {

    INTERNAL_ERROR_UNKNOWN(500, "internal_error", "Unknown internal server error occurs."),
    INTERNAL_ERROR_NO_PARAMS(400, "bad_request", "Command parameters are not specified or otherwise malformed."),
    BAD_REQUEST_NO_OXD_ID(400, "bad_request", "oxd_id is empty or not specified or is otherwise invalid (not registered)."),
    BAD_REQUEST_NO_CODE(400, "bad_request", "'code' is empty or not specified."),
    BAD_REQUEST_NO_STATE(400, "bad_request", "'state' is empty or not specified."),
    BAD_REQUEST_STATE_NOT_VALID(400, "bad_request", "'state' is not registered."),
    BAD_REQUEST_INVALID_CODE(400, "bad_request", "'code' is invalid."),
    BAD_REQUEST_NO_REFRESH_TOKEN(400, "bad_request", "'refresh token' is empty or not specified."),
    NO_ID_TOKEN_RETURNED(500, "no_id_token", "id_token is not returned. Please check: 1) OP log file for error (oxauth.log) 2) whether 'openid' scope is present for 'get_authorization_url' command"),
    NO_ID_TOKEN_PARAM(400, "no_id_token", "id_token is not provided in request to oxd."),
    NO_ACCESS_TOKEN_RETURNED(500, "no_access_token", "access_token is not returned by OP. Please check OP configuration."),
    ACCESS_TOKEN_INSUFFICIENT_SCOPE(403, "access_token_insufficient_scope", "access_token does not have `oxd` scope. Make sure a) scope exists on AS b) register_site is registered with 'oxd' scope c) get_client_token has 'oxd' scope in request"),
    INVALID_NONCE(400, "invalid_nonce", "Nonce value is not registered by oxd."),
    INVALID_STATE(400, "invalid_state", "State value is not registered by oxd."),
    INVALID_ID_TOKEN_BAD_NONCE(500, "invalid_id_token_bad_nonce", "Invalid id_token. Nonce value from token does not match nonce from request."),
    INVALID_ID_TOKEN_BAD_AUDIENCE(500, "invalid_id_token_bad_audience", "Invalid id_token. Audience value from token does not match audience from request."),
    INVALID_ID_TOKEN_BAD_AUTHORIZED_PARTY(500, "invalid_id_token_bad_authorized_party", "Invalid id_token. Authorized party value from token does not match client_id of client."),
    INVALID_ID_TOKEN_EXPIRED(500, "invalid_id_token_expired", "Invalid id_token. id_token expired."),
    INVALID_ID_TOKEN_BAD_ISSUER(500, "invalid_id_token_bad_issuer", "Invalid id_token. Bad issuer."),
    INVALID_ID_TOKEN_BAD_SIGNATURE(500, "invalid_id_token_bad_signature", "Invalid id_token. Bad signature."),
    INVALID_ID_TOKEN_UNKNOWN(500, "invalid_id_token_unknown", "Invalid id_token, validation fail due to exception, please check oxd-server.log for details."),
    INVALID_ACCESS_TOKEN_BAD_HASH(500, "invalid_access_token_bad_hash", "access_token is invalid. Hash of access_token does not match hash from id_token (at_hash)."),
    INVALID_AUTHORIZATION_CODE_BAD_HASH(500, "invalid_authorization_code_bad_hash", "Authorization code is invalid. Hash of authorization code does not match hash from id_token (c_hash)."),
    INVALID_REGISTRATION_CLIENT_URL(500, "invalid_registration_client_url", "Registration client URL is invalid. Please check registration_client_url response parameter from IDP (http://openid.net/specs/openid-connect-registration-1_0.html#RegistrationResponse)."),
    INVALID_OXD_ID(400, "invalid_oxd_id", "Invalid oxd_id. Unable to find the site for oxd_id. It does not exist or has been removed from the server. Please use the register_site command to register a site."),
    INVALID_REQUEST(400, "invalid_request", "Request is invalid. It doesn't contains all required parameters or otherwise is malformed."),
    INVALID_REQUEST_SCOPES_REQUIRED(400, "invalid_request", "Request is invalid. Scopes are required parameter in request."),
    INVALID_CLIENT_SECRET_REQUIRED(400, "invalid_client_secret", "client_secret is required parameter in request (skip client_id if you wish to dynamically register client.)."),
    INVALID_CLIENT_ID_REQUIRED(400, "invalid_client_id", "client_id is required parameter in request (skip client_secret if you wish to dynamically register client.)."),
    UNSUPPORTED_OPERATION(500, "unsupported_operation", "Operation is not supported by server error."),
    INVALID_OP_HOST(400, "invalid_op_host", "Invalid op_host (empty or blank)."),
    INVALID_ALLOWED_OP_HOST_URL(400, "invalid_allowed_op_host_url", "Please check 1) The urls in allowed_op_hosts field of oxd-server.yml are valid. 2) If op_host url is valid."),
    RESTRICTED_OP_HOST(400, "restricted_op_host", "oxd server is not allowed to access op_host. Please check if op_host url is present in allowed_op_hosts field of oxd-server.yml."),
    BLANK_ACCESS_TOKEN(403, "blank_access_token", "access_token is blank. Command is protected by access_token, please provide valid token or otherwise switch off protection in configuration with protect_commands_with_access_token=false"),
    INVALID_ACCESS_TOKEN(403, "invalid_access_token", "Invalid access_token. Command is protected by access_token, please provide valid token or otherwise switch off protection in configuration with protect_commands_with_access_token=false"),
    NO_CLIENT_ID_IN_INTROSPECTION_RESPONSE(500, "invalid_introspection_response", "AS returned introspection response with empty/blank client_id which is required by oxd. Please check your AS installation and make sure AS return client_id for introspection call (CE 3.1.0 or later)."),
    INACTIVE_ACCESS_TOKEN(403, "inactive_access_token", "Inactive access_token. Command is protected by access_token, please provide valid token or otherwise switch off protection in configuration with protect_commands_with_access_token=false"),
    INVALID_REDIRECT_URI(400, "invalid_redirect_uri", "Invalid redirect_uri (empty, blank or invalid)."),
    INVALID_SCOPE(400, "invalid_scope", "Invalid scope parameter (empty or blank)."),
    INVALID_ACR_VALUES(400, "invalid_acr_values", "Invalid acr_values parameter (empty or blank)."),
    INVALID_SIGNATURE_ALGORITHM(400, "invalid_algorithm", "Invalid algorithm provided. Valid algorithms are: " + Arrays.toString(SignatureAlgorithm.values())),
    INVALID_KEY_ENCRYPTION_ALGORITHM(400, "invalid_algorithm", "Invalid algorithm provided. Valid algorithms are: " + Arrays.toString(KeyEncryptionAlgorithm.values())),
    INVALID_SUBJECT_TYPE(400, "invalid_subject_type", "Invalid subject type provided. Valid algorithms are: " + Arrays.toString(SubjectType.values())),
    INVALID_BLOCK_ENCRYPTION_ALGORITHM(400, "invalid_algorithm", "Invalid algorithm provided. Valid algorithms are: " + Arrays.toString(BlockEncryptionAlgorithm.values())),
    NO_CONNECT_DISCOVERY_RESPONSE(500, "no_connect_discovery_response", "Unable to fetch Connect discovery response /.well-known/openid-configuration"),
    NO_REGISTRATION_ENDPOINT(500, "invalid_request", "OP does not support dynamic client registration. Please register client manually and provide client_id and client_secret to register_site command."),
    NO_UMA_DISCOVERY_RESPONSE(500, "no_uma_discovery_response", "Unable to fetch UMA discovery response /.well-known/uma2-configuration"),
    NO_UMA_RESOURCES_TO_PROTECT(400, "invalid_uma_request", "Resources list to protect is empty or blank. Please check it according to protocol definition at " + CoreUtils.DOC_URL),
    NO_UMA_HTTP_METHOD(400, "invalid_http_method", "http_method is not specified or otherwise not GET or POST or PUT or DELETE. Please check it according to protocol definition at " + CoreUtils.DOC_URL),
    NO_UMA_PATH_PARAMETER(400, "invalid_path_parameter", "path parameter is not specified or otherwise not valid"),
    NO_UMA_TICKET_PARAMETER(400, "invalid_ticket_parameter", "ticket parameter is not specified or otherwise is not valid"),
    NO_UMA_CLAIMS_REDIRECT_URI_PARAMETER(400, "invalid_claims_redirect_uri_parameter", "claims_redirect_uri parameter is not specified or otherwise is not valid"),
    NO_UMA_RPT_PARAMETER(400, "invalid_rpt_parameter", "rpt parameter is not specified or otherwise is not valid"),
    INVALID_CLAIM_TOKEN_OR_CLAIM_TOKEN_FORMAT(400, "invalid_claim_token_or_claim_token_form", "Claim token or claim token format is invalid."),
    UMA_NEED_INFO(403, "need_info", "The authorization server needs additional information in order to determine whether the client is authorized to have these permissions."),
    UMA_HTTP_METHOD_NOT_UNIQUE(400, "http_method_not_unique", "HTTP method defined in JSON must be unique within given PATH (but occurs more then one time)."),
    UMA_FAILED_TO_VALIDATE_SCOPE_EXPRESSION(400, "invalid_scope_expression", "The scope expression is invalid. Please check the documentation and make sure it is a valid JsonLogic expression."),
    UMA_PROTECTION_FAILED_BECAUSE_RESOURCES_ALREADY_EXISTS(400, "uma_protection_exists", "Server already has UMA Resources registered for this oxd_id. It is possible to overwrite it if provide overwrite=true for uma_rs_protect command (existing resources will be removed and new UMA Resources added)."),
    FAILED_TO_GET_END_SESSION_ENDPOINT(500, "no_end_session_endpoint_at_op", "OP does not provide end_session_endpoint at /.well-known/openid-configuration."),
    FAILED_TO_GET_RPT(500, "internal_error", "Failed to get RPT."),
    FAILED_TO_REMOVE_SITE(500, "remove_site_failed", "Failed to remove site."),
    REDIRECT_URI_IS_NOT_REGISTERED(400, "redirect_uri_is_not_registered", "The authorization redirect uri is not registered."),
    FAILED_TO_GET_DISCOVERY(500, "failed_to_get_discovery", "Failed to get OP discovery configuration."),
    SSL_HANDSHAKE_ERROR(500, "ssl_handshake_error", "Unable to find valid certification path to requested target. Please check if key_store_path in oxd configuration is correct."),
    INVALID_ALGORITHM(500, "invalid_algorithm", "Invalid algorithm provided (empty or null)."),
    ALGORITHM_NOT_SUPPORTED(500, "algorithm_not_supported", "Algorithm not supported.");

    private final int httpStatus;
    private final String code;
    private final String description;

    ErrorResponseCode(int httpStatus, String code, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getDescription() {
        return description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static ErrorResponseCode fromValue(String v) {
        if (StringUtils.isNotBlank(v)) {
            for (ErrorResponseCode t : values()) {
                if (t.getCode().equalsIgnoreCase(v)) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ErrorResponseCode");
        sb.append("{value='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
