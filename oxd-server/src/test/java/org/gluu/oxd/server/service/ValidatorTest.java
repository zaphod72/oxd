package org.gluu.oxd.server.service;

import org.gluu.oxauth.model.exception.InvalidJwtException;
import org.gluu.oxauth.model.jwt.Jwt;
import org.gluu.oxd.server.HttpException;
import org.gluu.oxd.server.op.Validator;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;

public class ValidatorTest {

    @Test
    public void tokenWithMultiAudAndAzp_shouldBeValid() throws InvalidJwtException {
        //"aud": ["6b578a9b-7513-477a-9a7f-1343b487caf8","another_aud"],
        //"azp":"6b578a9b-7513-477a-9a7f-1343b487caf8"
        final Jwt idToken = Jwt.parse("eyJraWQiOiJjZmFiMzRlYy0xNjhkLTQ4OTUtODRiOC0xZjAyNzgwNDkxYzciLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiMnI1clZ2STdpMWxfcnNXZUV4bGRuUSIsImF1ZCI6WyI2YjU3OGE5Yi03NTEzLTQ3N2EtOWE3Zi0xMzQzYjQ4N2NhZjgiLCJhbm90aGVyX2F1ZCJdLCJhenAiOiI2YjU3OGE5Yi03NTEzLTQ3N2EtOWE3Zi0xMzQzYjQ4N2NhZjgiLCJzdWIiOiJzLV9aaXJWdDdOT0RkbldEQVVHcmpUMnFVWndLNmNYVGhiOXFWOTl2LXRnIiwiYXV0aF90aW1lIjoxNTY4MTg1MzI3LCJpc3MiOiJodHRwczovL2R1bW15LWlzc3Vlci5vcmciLCJleHAiOjE5NjgxODg5MzAsImlhdCI6MTU2ODE4NTMzMCwibm9uY2UiOiI3cjQ2dXQ2ZW11OWdpMTFnbjgwNDR1bTY0MCIsIm94T3BlbklEQ29ubmVjdFZlcnNpb24iOiJvcGVuaWRjb25uZWN0LTEuMCJ9.Q1WsYrrMx4Uo7ZT5X840yuPljdRM5AU1otNAlc7-XcCUWB2yzdHZ5ptNdcGUEKr3bHLN4f-YyBC4n6Yea60eFxTkjXKGpGJESRU690xJ_OHb69DzHXiRnbtBRcHRMUgra5CBC4WNqyTRc0SBJFGOVuNAceNyVLSP7zPXgGNQHcA");
        String clientId = "6b578a9b-7513-477a-9a7f-1343b487caf8";
        try {
            Validator.validateAudience(idToken, clientId);
        } catch (Exception e) {
            assertFalse(e instanceof HttpException);
        }
    }

    @Test
    public void tokenWithSingleAudArrayAndNoAzp_shouldBeValid() throws InvalidJwtException {
        //"aud": ["6b578a9b-7513-477a-9a7f-1343b487caf8"],
        final Jwt idToken = Jwt.parse("eyJraWQiOiJjZmFiMzRlYy0xNjhkLTQ4OTUtODRiOC0xZjAyNzgwNDkxYzciLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiMnI1clZ2STdpMWxfcnNXZUV4bGRuUSIsImF1ZCI6WyI2YjU3OGE5Yi03NTEzLTQ3N2EtOWE3Zi0xMzQzYjQ4N2NhZjgiXSwic3ViIjoicy1fWmlyVnQ3Tk9EZG5XREFVR3JqVDJxVVp3SzZjWFRoYjlxVjk5di10ZyIsImF1dGhfdGltZSI6MTU2ODE4NTMyNywiaXNzIjoiaHR0cHM6Ly9kdW1teS1pc3N1ZXIub3JnIiwiZXhwIjoxOTY4MTg4OTMwLCJpYXQiOjE1NjgxODUzMzAsIm5vbmNlIjoiN3I0NnV0NmVtdTlnaTExZ244MDQ0dW02NDAiLCJveE9wZW5JRENvbm5lY3RWZXJzaW9uIjoib3BlbmlkY29ubmVjdC0xLjAifQ.cP6DGPkYYnzDTHrH04F4Q48cPqH2T4R4RjGJmLr5QGA1pUYOOxvLj8Ak0EqmzV_83Zy0wgvyzFCv0xdi06BguUgnM4u6LL8V0hLzrdHIwJHvz5L5Gqbvs5Vg61CpP409lo0sHUN08zfN_WU3EWXK6JlSvFtE59jWSJWBF5pmLX4");
        String clientId = "6b578a9b-7513-477a-9a7f-1343b487caf8";
        try {
            Validator.validateAudience(idToken, clientId);
        } catch (Exception e) {
            assertFalse(e instanceof HttpException);
        }
    }

    @Test
    public void tokenWithSingleAudStringAndNoAzp_shouldBeValid() throws InvalidJwtException {
        //"aud": "6b578a9b-7513-477a-9a7f-1343b487caf8",
        final Jwt idToken = Jwt.parse("eyJraWQiOiJjZmFiMzRlYy0xNjhkLTQ4OTUtODRiOC0xZjAyNzgwNDkxYzciLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiMnI1clZ2STdpMWxfcnNXZUV4bGRuUSIsImF1ZCI6IjZiNTc4YTliLTc1MTMtNDc3YS05YTdmLTEzNDNiNDg3Y2FmOCIsInN1YiI6InMtX1ppclZ0N05PRGRuV0RBVUdyalQycVVad0s2Y1hUaGI5cVY5OXYtdGciLCJhdXRoX3RpbWUiOjE1NjgxODUzMjcsImlzcyI6Imh0dHBzOi8vZHVtbXktaXNzdWVyLm9yZyIsImV4cCI6MTk2ODE4ODkzMCwiaWF0IjoxNTY4MTg1MzMwLCJub25jZSI6IjdyNDZ1dDZlbXU5Z2kxMWduODA0NHVtNjQwIiwib3hPcGVuSURDb25uZWN0VmVyc2lvbiI6Im9wZW5pZGNvbm5lY3QtMS4wIn0.PqnRiAhXqdeTbW1_JdRl6rLDMn36ists9Eq1n_2vOKYjGs_VxxkcdQfCt93KfC3WqEObhjlKDzwp6YUXi_7Wqta58ftUz0FU2jB7np3mq5m8lY_hKVhoZJMvxzMbCkiH-8jwtq9MZKEw3qyrwQEHQ0l21tograWD80gRedaQuD4");
        String clientId = "6b578a9b-7513-477a-9a7f-1343b487caf8";
        try {
            Validator.validateAudience(idToken, clientId);
        } catch (Exception e) {
            assertFalse(e instanceof HttpException);
        }
    }

    @Test
    public void tokenWithNAzpNotClientId_shouldNotValid() throws InvalidJwtException {
        //"aud": ["6b578a9b-7513-477a-9a7f-134-
        // 3b487caf8"],
        //"azp":"Not_equal_to_client_id"
        final Jwt idToken = Jwt.parse("eyJraWQiOiJjZmFiMzRlYy0xNjhkLTQ4OTUtODRiOC0xZjAyNzgwNDkxYzciLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiMnI1clZ2STdpMWxfcnNXZUV4bGRuUSIsImF1ZCI6WyI2YjU3OGE5Yi03NTEzLTQ3N2EtOWE3Zi0xMzQzYjQ4N2NhZjgiXSwiYXpwIjoiTm90X2VxdWFsX3RvX2NsaWVudF9pZCIsInN1YiI6InMtX1ppclZ0N05PRGRuV0RBVUdyalQycVVad0s2Y1hUaGI5cVY5OXYtdGciLCJhdXRoX3RpbWUiOjE1NjgxODUzMjcsImlzcyI6Imh0dHBzOi8vZHVtbXktaXNzdWVyLm9yZyIsImV4cCI6MTk2ODE4ODkzMCwiaWF0IjoxNTY4MTg1MzMwLCJub25jZSI6IjdyNDZ1dDZlbXU5Z2kxMWduODA0NHVtNjQwIiwib3hPcGVuSURDb25uZWN0VmVyc2lvbiI6Im9wZW5pZGNvbm5lY3QtMS4wIn0.A8nBQuJhnhUtx-KoDrh1DipJ9w9Sil9dY0H-whsHvmA6RBHQHf3c5F2r6jqT8wSQeT7PJn164OIZ7F0abOhynUmXpsS7mp8JsDs4CuIeXlyBMTFNJRiI9Rzm-lJ3rr3fMbla_r2Y3YDHU4VLxGB3FkY6KRt3FnSt-d37a4GY1WE");
        String clientId = "6b578a9b-7513-477a-9a7f";
        try {
            Validator.validateAudience(idToken, clientId);
        } catch (Exception e) {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException)e;
                assertFalse(httpException.getCode().getCode().equals("invalid_id_token_bad_authorized_party"));
            }
        }
    }
}
