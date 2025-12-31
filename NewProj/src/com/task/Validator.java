
package com.task;

import java.util.regex.Pattern;

public class Validator {


    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z][A-Za-z0-9_]{4,19}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");


    public static void validateUsername(String username)
            throws Exceptions.InvalidUsernameException {

        if (username == null) {
            throw new Exceptions.InvalidUsernameException("Username cannot be null");
        }
        if (username.isBlank()) {
            throw new Exceptions.InvalidUsernameException("Username cannot be blank");
        }
        if (username.length() < 5 || username.length() > 20) {
            throw new Exceptions.InvalidUsernameException("Username length must be 5–20 characters");
        }
        if (!Character.isLetter(username.charAt(0))) {
            throw new Exceptions.InvalidUsernameException("Username must start with a letter");
        }
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new Exceptions.InvalidUsernameException(
                "Username may contain only letters, digits, and underscore (_)"
            );
        }
    }


    public static void validatePassword(String password)
            throws Exceptions.InvalidPasswordException {

        if (password == null) {
            throw new Exceptions.InvalidPasswordException("Password cannot be null");
        }
        if (password.isBlank()) {
            throw new Exceptions.InvalidPasswordException("Password cannot be blank");
        }
        if (password.length() < 8) {
            throw new Exceptions.InvalidPasswordException("Password must be at least 8 characters long");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new Exceptions.InvalidPasswordException(
                "Password must include at least one uppercase letter, one lowercase letter, " +
                "one digit, and one special character (@$!%*?&). Allowed characters are letters, digits, and @$!%*?&"
            );
        }
    }

    public static void validatePasswordMatch(String password, String confirmPassword)
            throws Exceptions.InvalidPasswordException {

        if (password == null || confirmPassword == null) {
            throw new Exceptions.InvalidPasswordException("Password and confirm password cannot be null");
        }
        if (!password.equals(confirmPassword)) {
            throw new Exceptions.InvalidPasswordException("Password and confirm password do not match");
        }
    }
}
