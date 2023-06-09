package lethanhdat.Lab3.Validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lethanhdat.Lab3.Validator.ValidUsernameValidator;

@Constraint(validatedBy = ValidUsernameValidator.class)
public @interface ValidUsername {
    String message() default "Username already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}