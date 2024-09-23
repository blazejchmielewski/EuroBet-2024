package pl.chmielewski.Euro.request;

public record EditUserRequest(
        String firstname,
        String lastname,
        String email,
        String department
) {
    @Override
    public String toString() {
        return "EditRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
