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
    
    private void testFunction()
    {
        
        System.out.println("testing 123");
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
        System.out.println(value.getFrequency());
        testFucntion();
        return false;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
