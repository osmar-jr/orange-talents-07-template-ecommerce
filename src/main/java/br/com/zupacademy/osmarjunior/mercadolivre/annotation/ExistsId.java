package br.com.zupacademy.osmarjunior.mercadolivre.annotation;

import br.com.zupacademy.osmarjunior.mercadolivre.validator.ExistsIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistsIdValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface ExistsId {

    String message() default "Id entered doesn't exist on database.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<?> classDomain();
    String attributeName();
}
