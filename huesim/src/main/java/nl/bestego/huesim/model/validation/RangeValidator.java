package nl.bestego.huesim.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Integer> {

    private int min, max;

    @Override
    public void initialize(Range range) {
        min = range.min();
        max = range.max();
    }

    @Override
    public boolean isValid(Integer waarde, ConstraintValidatorContext constraintValidatorContext) {
        return waarde >= min && waarde <= max;
    }


}
