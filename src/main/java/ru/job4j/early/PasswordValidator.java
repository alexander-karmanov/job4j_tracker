package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        boolean valid = false;
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        char[] charpassword = password.toCharArray();
        for (char ch : charpassword) {
            if (Character.isUpperCase(ch)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        valid = false;

        for (char ch : charpassword) {
            if (Character.isLowerCase(ch)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        valid = false;

        for (char ch : charpassword) {
            if (Character.isDigit(ch)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        valid = false;

        for (char ch : charpassword) {
            if (!Character.isLetterOrDigit(ch)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        valid = false;

        String[] inval = {"qwerty", "12345", "password", "admin", "user"};
        for (String inv : inval) {
            if (password.toLowerCase().contains(inv)) {
                throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
            }
        }
        return password;
    }
}
