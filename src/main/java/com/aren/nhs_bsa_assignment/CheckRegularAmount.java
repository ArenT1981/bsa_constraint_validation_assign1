/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author aren
 */
@Documented
@Constraint(validatedBy = CheckRegularAmountValidator.class)
@Target(
{
    ElementType.TYPE, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRegularAmount
{

    String message() default "Default message indicating valdiation error."; //{com.aren.nhs_bsa_assignment.RegularAmount}";

    Class<?>[] groups() default 
    {
    };

    Class<? extends Payload>[] payload() default 
    {
    };
}
