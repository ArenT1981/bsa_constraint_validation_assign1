/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author aren
 */
public class RunAmount
{
    
    public static void main(String args[])
    {
        RegularAmount newAmount = new RegularAmount();
        
        newAmount.setFrequency(RegularAmount.Frequency.WEEK);
        newAmount.setAmount("100.00");
        
        System.out.println("Hello");
        
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(newAmount);
        
        System.out.println("Size is: " + violations.size());
        
        for (ConstraintViolation<RegularAmount> violation : violations) 
        {
            System.out.println(violation.getMessage());
        }
    }
    
}
