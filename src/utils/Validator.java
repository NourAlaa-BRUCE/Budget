package src.utils;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,16}$");
    }
    public static boolean isValidPhoneNumber(String phone) {
        // مصر +20 ثم 10 أرقام
        return phone.matches("^\\+20\\d{10}$");
    }
}