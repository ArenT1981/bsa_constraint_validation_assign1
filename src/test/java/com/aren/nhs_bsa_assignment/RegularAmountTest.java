/* RegularAmountTest.java
 *
 * See LICENSE.txt in project root directory for license details.
 */
package com.aren.nhs_bsa_assignment;

import static com.aren.nhs_bsa_assignment.RegularAmount.Frequency;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

/**
 * @author Aren Tyr.
 * @version 0.6 2020-08-22
 */
public class RegularAmountTest
{

    // Necessary Bean validators
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    /**
     * Specialist constructor to setup a test Bean instance.
     */
    @BeforeAll
    public static void createValidator()
    {
        // Setup the validators
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Deletes/closes the test Bean instance after tests are complete.
     */
    @AfterAll
    public static void close()
    {
        validatorFactory.close();
    }

    /**
     * Wrapper method that validates/checks Bean instance for validation errors.
     *
     * @param ra A <code>RegularAmount</code> instance to perform
     * <code>ConstraintValidation</code> on.
     * @return <code>true</code> if <code>RegularAmount</code> has no validation
     * errors, <code>false</code> otherwise.
     */
    private boolean runBeanConstraintValidator(RegularAmount ra)
    {
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(ra);
        if(violations.isEmpty())
        {
            System.out.println("-> VALID amount/frequency. \nAmount: "
                    + ra.getAmount() + " \nFrequency: " + ra.getFrequency());
            return true;
        }
        else
        {
            System.out.println("-> INVALID amount/frequency. \nAmount: \""
                    + ra.getAmount() + "\" \nFrequency: " + ra.getFrequency());
            return false;
        }
    }

    /**
     * JUnit test of <code>setFrequency</code> and <code>getFrequency</code>
     * methods, of class <code>RegularAmount</code>.
     *
     * Mostly just a test of state, since it is just a simple getter &amp;
     * setter, but here for completeness.
     */
    @Test
    public void testGetAndSetFrequency()
    {
        System.out.println("* Testing getting and setting Frequency...");
        System.out.println("==========================================");
        RegularAmount instance = new RegularAmount();
        instance.setFrequency(RegularAmount.Frequency.WEEK);
        RegularAmount.Frequency expResult = Frequency.WEEK;
        RegularAmount.Frequency result = instance.getFrequency();
        assertEquals(expResult, result);
    }

    /**
     * JUnit test of <code>setFrequency</code> and <code>getFrequency</code>
     * methods, of class <code>RegularAmount</code>.
     *
     * Mostly just a test of state, since it is just a simple getter &amp;
     * setter, but here for completeness.
     */
    @Test
    public void testGetAndSetAmount()
    {
        System.out.println("* Testing getting and setting Amount...");
        System.out.println("==========================================");
        RegularAmount instance = new RegularAmount();
        instance.setAmount("100.00");
        String expResult = "100.00";
        String result = instance.getAmount();
        assertEquals(expResult, result);
    }

    /**
     * JUnit test that tests the <code>ConstraintValidator</code> class across a
     * series of correct inputs to see that is is validating/accepting as
     * expected.
     */
    @Test
    public void correctValuesWholeNumberOfPence()
    {
        System.out.println("* Testing CORRECT input values...");
        System.out.println("==========================================");
        RegularAmount instance1 = new RegularAmount("100", Frequency.WEEK);
        RegularAmount instance2 = new RegularAmount("400.00", Frequency.FOUR_WEEK);
        assertTrue(runBeanConstraintValidator(instance1));
        assertTrue(runBeanConstraintValidator(instance2));
    }

    /**
     * JUnit test that tests the <code>ConstraintValidator</code> class across a
     * series of incorrect inputs to see that is is rejecting as expected.
     *
     * This function tests a sequence of values all of which should validate as
     * bad/incorrect values (hence the "!" before the return value).
     */
    @Test
    public void incorrectValuesNOTWholeNumberOfPence()
    {
        System.out.println("Testing INCORRECT input values...");
        System.out.println("==========================================");
        RegularAmount instance1 = new RegularAmount("bad value", Frequency.WEEK);
        RegularAmount instance2 = new RegularAmount("99.99", Frequency.FOUR_WEEK);
        assertTrue(!runBeanConstraintValidator(instance1));
        assertTrue(!runBeanConstraintValidator(instance2));
    }

}
