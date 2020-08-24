/* CheckRegularAmount.java
 *
 * See LICENSE.txt in project root directory for license details.
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
 * @author Aren Tyr
 * @version 1.0 - 2020-08-22
 * 
 * <code>ConstraintValidator</code> annotation interface that calls 
 * <code>CheckRegularAmountValidator</code>.
 * 
 * Primarily targeted at a class level through <code>ElementType.TYPE</code>, 
 * since the validation is dependent on the result of both <code>amount</code> 
 * and <code>frequency</code> in combination. Body of the interface is standard
 * defaults.
 * 
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
    String message() default "Validation error detected.";

    Class<?>[] groups() default 
    {
    };

    Class<? extends Payload>[] payload() default 
    {
    };
}
