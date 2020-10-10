package ImageHoster.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//A simple marker annotations which tell whether to add Annotation in Javadocs or not.
@Documented
//Marks an annotation as being a Bean Validation Constraint.
//The element validatedBy specifies the classes implementing the constraint which is PasswordStrengthValidator.
@Constraint(validatedBy = PasswordStrengthValidator.class)
//Location where it can be used. I've generalised for future use
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
//Defines for how long the annotation should be kept.
//RUNTIME so that it can be used by the runtime environment.
@Retention(RetentionPolicy.RUNTIME)
// Creating an custom annotation class
public @interface ValidPassword {

    String message() default "Error - Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
