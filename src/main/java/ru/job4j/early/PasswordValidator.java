package ru.job4j.early;

import java.util.Locale;

public class PasswordValidator {

    public static String validate(String password) {
        boolean valid = false;
        char[] charpassword = password.toCharArray();

        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        for (char ch : charpassword) {
            if (Character.isUpperCase(ch)) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        valid = false;

        for (char ch : charpassword) {
            if (Character.isLowerCase(ch)) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        valid = false;

        for (char ch : charpassword) {
            if (Character.isDigit(ch)) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        valid = false;

        for (char ch : charpassword) {
            if (!Character.isLetterOrDigit(ch)) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        valid = false;

        if (password.toLowerCase().contains("qwerty")
            || (password.toLowerCase().contains("12345"))
            || (password.toLowerCase().contains("password"))
            || (password.toLowerCase().contains("admin"))
            || (password.toLowerCase().contains("user"))) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }

        return password;
    }

    public static void main(String[] args) {
        String string = "Ln2$mrQWerTY12";
        String st = string.toLowerCase();
        System.out.println(st);
        System.out.println(st.contains("qwerty"));
    }
}
