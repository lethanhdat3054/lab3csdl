package lethanhdat.Lab3.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lethanhdat.Lab3.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lethanhdat.Lab3.Validator.annotation.ValidUsername;

public class ValidUsernameValidator  implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context){
        if(userRepository==null){
            return true;
        }
        return userRepository.findByUsername(username)==null;
    }
}
