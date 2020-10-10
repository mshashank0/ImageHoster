package ImageHoster.constraint;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//Implements the ConstraintValidator interface which forces us to implement its methods.
//We will implements isValid function and define rules on password string
public class PasswordStrengthValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        /*
         a PasswordValidator object by passing an array of
         constraints that we want to enforce in our password.
         As per the assignment -
           1. At least one alphabet(uppercase of lowercase)
           2. At least one digit
           3. At least one special symbol other than alphabet
         */
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                // Add rule of at least one alphabet character(a-z or A-Z)
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),

                // Add rule of at least one digit character(0-9)
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // Add rule of at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1)

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        //Returns true if all conditions fullfil
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        //Else return false
        return false;
    }
}
