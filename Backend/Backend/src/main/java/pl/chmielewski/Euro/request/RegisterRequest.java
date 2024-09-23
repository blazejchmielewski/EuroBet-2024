package pl.chmielewski.Euro.request;

import pl.chmielewski.Euro.Authentication.user.Department;

public record RegisterRequest (
        String firstname,
        String lastname,
        String email,
        Department department
){

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
