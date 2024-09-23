package pl.chmielewski.Euro.request;

public record LoginRequest(
        String email,
        String password
) {

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
