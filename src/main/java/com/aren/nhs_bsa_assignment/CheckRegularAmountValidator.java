/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author aren
 */
public class CheckRegularAmountValidator implements ConstraintValidator<CheckRegularAmount, RegularAmount>
{
    //private RegularAmount regularAmountCheck;

    private boolean isValidNumericAmount(String inputAmount)
    {
        // Regular expression that requires number starts with at least one digit
        // and optionally has a decimal point & two digits
        return inputAmount.matches("^[0-9]+(\\.\\d{2})?$");
    }

    @Override
    public void initialize(CheckRegularAmount constraintAnnotation)
    {
        System.out.println("testing init method");

        //this.regularAmountCheck = constraintAnnotation;

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValid(RegularAmount value, ConstraintValidatorContext context)
    {
        boolean numericAmountCheck = isValidNumericAmount(value.getAmount());
        System.out.println("Valid number? = " + numericAmountCheck);
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
