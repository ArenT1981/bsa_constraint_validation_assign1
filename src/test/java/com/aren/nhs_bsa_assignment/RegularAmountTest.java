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
 * JUnit test class for <code>RegularAmount.java</code>.
 * 
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
     *        <code>ConstraintValidation</code> on.
     * 
     * @return <code>true</code> if <code>RegularAmount</code> has no validation
     *         errors, <code>false</code> otherwise.
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
        System.out.println("* Testing CORRECT input values (-> VALIDATION = \"Is Valid\"...");
        System.out.println("==========================================");
        // 1. Test WEEK - Any valid currency amount (below max @length) should validate
        RegularAmount instance1  = new RegularAmount("0.01", Frequency.WEEK);
        RegularAmount instance2  = new RegularAmount("0.09", Frequency.WEEK);
        RegularAmount instance3  = new RegularAmount("1", Frequency.WEEK);
        RegularAmount instance4  = new RegularAmount("9", Frequency.WEEK);
        RegularAmount instance5  = new RegularAmount("400.00", Frequency.WEEK);
        RegularAmount instance6  = new RegularAmount("1000.00", Frequency.WEEK);
        RegularAmount instance7  = new RegularAmount("9999.99", Frequency.WEEK);
        // Edge cases, 11 digits:
        RegularAmount instance8  = new RegularAmount("12312312.12", Frequency.WEEK);
        RegularAmount instance9  = new RegularAmount("77777777777", Frequency.WEEK);

        // 2. Test TWO_WEEK - Obviously need to be a modulus 2 zero remainder value
        RegularAmount instance10 = new RegularAmount("0.02", Frequency.TWO_WEEK);
        RegularAmount instance11 = new RegularAmount("2", Frequency.TWO_WEEK);
        RegularAmount instance12 = new RegularAmount("200.00", Frequency.TWO_WEEK);
        RegularAmount instance13 = new RegularAmount("400.00", Frequency.TWO_WEEK);
        RegularAmount instance14 = new RegularAmount("4444.44", Frequency.TWO_WEEK);
        //Edge cases, 11 digits
        RegularAmount instance15 = new RegularAmount("88888888888", Frequency.TWO_WEEK);
        RegularAmount instance16 = new RegularAmount("22222222.22", Frequency.TWO_WEEK);

        // 3. Test FOUR_WEEK - Mod 4 zero remainder
        RegularAmount instance17 = new RegularAmount("0.04", Frequency.FOUR_WEEK);
        RegularAmount instance18 = new RegularAmount("4", Frequency.FOUR_WEEK);
        RegularAmount instance19 = new RegularAmount("400.00", Frequency.FOUR_WEEK);
        RegularAmount instance20 = new RegularAmount("12000.00", Frequency.FOUR_WEEK);
        // Edge cases, 11 digits
        RegularAmount instance21 = new RegularAmount("88888888.88", Frequency.FOUR_WEEK);
        RegularAmount instance22 = new RegularAmount("44444444444", Frequency.FOUR_WEEK);
        
        // 4. Test QUARTER - Mod 13 zero remainder
        RegularAmount instance23 = new RegularAmount("0.13", Frequency.QUARTER);
        RegularAmount instance24 = new RegularAmount("13", Frequency.QUARTER);
        RegularAmount instance25 = new RegularAmount("260.00", Frequency.QUARTER);
        RegularAmount instance26 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance27 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance28 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance29 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance30 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance31 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance32 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance33 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance34 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance35 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance36 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance37 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance38 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance39 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance40 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance41 = new RegularAmount("44444444444", Frequency.QUARTER);
        RegularAmount instance42 = new RegularAmount("44444444444", Frequency.QUARTER);
        



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
