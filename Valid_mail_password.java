import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

// practical 2
public class Valid_mail_password {

    // Regular expression for email validation
    private static final String EMAIL_PATTERN = "[a-z]+@[a-z]+\\.(com|in)";

    // Regular expression for password validation
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static void main(String[] args) {
        // Email validation
        Scanner sc=new Scanner(System.in);
        String emailToValidate = sc.next();
        if (isValidEmail(emailToValidate)) {
            System.out.println("Email is valid");
        } else {
            System.out.println("Email is not valid");
        }

        // Password validation
        String passwordToValidate =sc.next();
        if (isValidPassword(passwordToValidate)) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is not valid");
        }
    }

    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    
}