package za.ac.cput.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Helper {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    // ==================== PHONE VALIDATION ====================

    public static boolean isValidPhoneNumber(String phoneNumber, String regionCode) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, regionCode);
            return phoneUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            System.err.println("Phone parsing error: " + e.getErrorType());
            return false;
        }
    }

    public static boolean isValidSouthAfricanPhoneNumber(String phoneNumber) {
        return isValidPhoneNumber(phoneNumber, "ZA");
    }

    // ==================== NULL & EMPTY CHECKS ====================

    public static void requireNotEmptyOrNull(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    public static void requireNonNull(Object object, String fieldName) {
        if (object == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static boolean requireNotNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }

    public static boolean requireNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }

    public static boolean requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return true;
    }

    // ==================== EMAIL VALIDATION ====================

    public static boolean isValidEmailWithRegex(String email) {
        String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern PATTERN = Pattern.compile(REGEX_EMAIL);
        requireNotEmptyOrNull(email, "Email");
        return PATTERN.matcher(email).matches();
    }

    public static boolean isValidUniversityEmail(String email) {
        requireNotEmptyOrNull(email, "University Email");
        return email != null && email.toLowerCase().endsWith("@cput.ac.za") && isValidEmailWithRegex(email);
    }

    public static boolean isValidEmailWithApacheCommons(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isValidEmail(String email) {
        if (!isValidEmailWithApacheCommons(email)) {
            throw new IllegalArgumentException("Email is an invalid format");
        }
        return true;
    }

    // ==================== RATING VALIDATION ====================

    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public static void requireValidRating(int rating) {
        if (!isValidRating(rating)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }

    // ==================== PRICE VALIDATION ====================

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static void requireValidPrice(double price) {
        if (!isValidPrice(price)) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    // ==================== QUANTITY VALIDATION ====================

    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public static void requireValidQuantity(int quantity) {
        if (!isValidQuantity(quantity)) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    // ==================== STUDENT NUMBER VALIDATION ====================


    public static boolean isValidStudentNumber(String studentNumber) {
        return studentNumber != null && !studentNumber.isEmpty();
    }

    public static boolean compareStudentNumbers(String entered, String actual) {
        return entered != null && entered.equals(actual);
    }

    // ==================== DATE VALIDATION ====================

    public static boolean isFutureDate(LocalDateTime date) {
        requireNonNull(date, "Date");
        return date.isAfter(LocalDateTime.now());
    }

    public static boolean isPastDate(LocalDateTime date) {
        requireNonNull(date, "Date");
        return date.isBefore(LocalDateTime.now());
    }

    // ==================== STRING UTILITIES ====================

    public static String truncateString(String str, int maxLength) {
        if (str == null) return null;
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength) + "...";
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    // ==================== ID GENERATION HELPERS ====================

    public static String generateUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    public static String generateReceiptNumber() {
        return "RCP-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }

    public static String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }

    public static String generateTrackingNumber() {
        return "TRK-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }

    public static String generateOrderReference() {
        return "ORD-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }

    public static String generateConfirmationNumber() {
        return "CONF-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }
}
