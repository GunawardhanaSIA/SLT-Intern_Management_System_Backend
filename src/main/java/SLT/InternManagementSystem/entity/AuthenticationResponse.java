package SLT.InternManagementSystem.entity;

public class AuthenticationResponse {
    private final String token;
    private final String verificationUrl;

    public AuthenticationResponse(String token) {
        this.token = token;
        this.verificationUrl = null;
    }

    public AuthenticationResponse(String token, String verificationUrl) {
        this.token = token;
        this.verificationUrl = verificationUrl;
    }

    public String getJwt() {
        return token;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }
}