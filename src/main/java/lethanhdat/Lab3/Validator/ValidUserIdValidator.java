package lethanhdat.Lab3.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lethanhdat.Lab3.entity.User;
import lethanhdat.Lab3.Validator.annotation.ValidUserId;


public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null) {
            return true;
        }
        return user.getId() != null;
    }
}
