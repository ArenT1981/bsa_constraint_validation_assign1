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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for <code>RegularAmount.java</code>.
 *
 * @author Aren Tyr.
 * @version 0.9 2020-08-24
 */
public class RegularAmountTest
{

    private final static boolean VERBOSE = true;

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
            if(VERBOSE)
            { System.out.println("-> VALID amount/frequency. \nAmount: "
                    + ra.getAmount() + " \nFrequency: " + ra.getFrequency()); }
            return true;
        }
        else
        {
            if(VERBOSE)
            { System.out.println("-> INVALID amount/frequency. \nAmount: \""
                    + ra.getAmount() + "\" \nFrequency: " + ra.getFrequency()); }
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
        if(VERBOSE)
        {
            System.out.println("* Testing getting and setting Frequency...");
            System.out.println("==========================================");
        }
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
        if(VERBOSE)
        {
            System.out.println("* Testing getting and setting Amount...");
            System.out.println("==========================================");
        }
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
        if(VERBOSE)
        {
            System.out.println("* Testing CORRECT input values (-> VALIDATION = \"Is Valid\"...)");
            System.out.println("==========================================");
        }
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
        RegularAmount instance26 = new RegularAmount("33098.00", Frequency.QUARTER);
        // Edge cases, 11 digits
        RegularAmount instance27 = new RegularAmount("25384359897", Frequency.QUARTER); // £1,952,643,069
        RegularAmount instance28 = new RegularAmount("11554101.00", Frequency.QUARTER); // £887,777

        // 5. Test YEAR - Mod 52 zero remainder
        RegularAmount instance29 = new RegularAmount("0.52", Frequency.YEAR);
        RegularAmount instance30 = new RegularAmount("52", Frequency.YEAR);
        RegularAmount instance31 = new RegularAmount("520.00", Frequency.YEAR);
        RegularAmount instance32 = new RegularAmount("884.00", Frequency.YEAR);
        // Edge cases, 11 digits
        RegularAmount instance33 = new RegularAmount("91288860.00", Frequency.YEAR); // £1,755,555
        RegularAmount instance34 = new RegularAmount("61711269360", Frequency.YEAR); // £1,186,755,180

        assertTrue(runBeanConstraintValidator(instance1));
        assertTrue(runBeanConstraintValidator(instance2));
        assertTrue(runBeanConstraintValidator(instance2));
        assertTrue(runBeanConstraintValidator(instance3));
        assertTrue(runBeanConstraintValidator(instance4));
        assertTrue(runBeanConstraintValidator(instance5));
        assertTrue(runBeanConstraintValidator(instance6));
        assertTrue(runBeanConstraintValidator(instance7));
        assertTrue(runBeanConstraintValidator(instance8));
        assertTrue(runBeanConstraintValidator(instance9));
        assertTrue(runBeanConstraintValidator(instance10));
        assertTrue(runBeanConstraintValidator(instance11));
        assertTrue(runBeanConstraintValidator(instance12));
        assertTrue(runBeanConstraintValidator(instance13));
        assertTrue(runBeanConstraintValidator(instance14));
        assertTrue(runBeanConstraintValidator(instance15));
        assertTrue(runBeanConstraintValidator(instance16));
        assertTrue(runBeanConstraintValidator(instance17));
        assertTrue(runBeanConstraintValidator(instance18));
        assertTrue(runBeanConstraintValidator(instance19));
        assertTrue(runBeanConstraintValidator(instance20));
        assertTrue(runBeanConstraintValidator(instance21));
        assertTrue(runBeanConstraintValidator(instance22));
        assertTrue(runBeanConstraintValidator(instance23));
        assertTrue(runBeanConstraintValidator(instance24));
        assertTrue(runBeanConstraintValidator(instance25));
        assertTrue(runBeanConstraintValidator(instance26));
        assertTrue(runBeanConstraintValidator(instance27));
        assertTrue(runBeanConstraintValidator(instance28));
        assertTrue(runBeanConstraintValidator(instance29));
        assertTrue(runBeanConstraintValidator(instance30));
        assertTrue(runBeanConstraintValidator(instance31));
        assertTrue(runBeanConstraintValidator(instance32));
        assertTrue(runBeanConstraintValidator(instance33));
        assertTrue(runBeanConstraintValidator(instance34));
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
        if(VERBOSE)
        {
            System.out.println("Testing INCORRECT input values...(-> VALIDATION = \"Is NOT Valid\"...)");
            System.out.println("==========================================");
        }
       
        // 1. Test WEEK - Since division by 1, only other constraint violations are possible
        // Too big/too many characters:
        RegularAmount instance1  = new RegularAmount("1234567891234567890.01", Frequency.WEEK);
        RegularAmount instance2  = new RegularAmount("0.0000000000000000000000000000001", Frequency.WEEK);
        // Dumb inputs (not currency number) tested later...

        // 2. Test TWO_WEEK - Any odd pence values should gurantee to be invalid.
        RegularAmount instance3 = new RegularAmount("0.03", Frequency.TWO_WEEK);
        RegularAmount instance4 = new RegularAmount("3.77", Frequency.TWO_WEEK);
        RegularAmount instance5 = new RegularAmount("303.33", Frequency.TWO_WEEK);
        RegularAmount instance6 = new RegularAmount("444.01", Frequency.TWO_WEEK);
        RegularAmount instance7 = new RegularAmount("4444.43", Frequency.TWO_WEEK);
        //Edge cases, 11 digits
        RegularAmount instance8 = new RegularAmount("100000019.03", Frequency.TWO_WEEK);
        RegularAmount instance9 = new RegularAmount("222222222.27", Frequency.TWO_WEEK);

        // 3. Test FOUR_WEEK - Same principle as above
        RegularAmount instance10 = new RegularAmount("0.05", Frequency.FOUR_WEEK);
        RegularAmount instance11 = new RegularAmount("5.11", Frequency.FOUR_WEEK);
        RegularAmount instance12 = new RegularAmount("400.05", Frequency.FOUR_WEEK);
        RegularAmount instance13 = new RegularAmount("12000.63", Frequency.FOUR_WEEK);
        // Edge cases, 11 digits
        RegularAmount instance14 = new RegularAmount("88888888.87", Frequency.FOUR_WEEK);
        RegularAmount instance15 = new RegularAmount("444444444.01", Frequency.FOUR_WEEK);

        // 4. Test QUARTER - Mod 13 zero remainder
        RegularAmount instance16 = new RegularAmount("0.12", Frequency.QUARTER);
        RegularAmount instance17 = new RegularAmount("16", Frequency.QUARTER);
        RegularAmount instance18 = new RegularAmount("261.00", Frequency.QUARTER);
        RegularAmount instance19 = new RegularAmount("33098.03", Frequency.QUARTER);
        // Edge cases, 11 digits
        RegularAmount instance20 = new RegularAmount("25384359.98", Frequency.QUARTER);
        RegularAmount instance21 = new RegularAmount("11554101.07", Frequency.QUARTER);

        // 5. Test YEAR - Mod 52 zero remainder
        RegularAmount instance22 = new RegularAmount("0.50", Frequency.YEAR);
        RegularAmount instance23 = new RegularAmount("51", Frequency.YEAR);
        RegularAmount instance24 = new RegularAmount("521.00", Frequency.YEAR);
        RegularAmount instance25 = new RegularAmount("885.00", Frequency.YEAR);
        // Edge cases, 11 digits
        RegularAmount instance26 = new RegularAmount("91288860.13", Frequency.YEAR);
        RegularAmount instance27 = new RegularAmount("61711269.77", Frequency.YEAR);

        // Test non-numbers/dumb inputs
        RegularAmount instance28 = new RegularAmount("bad value", Frequency.WEEK);
        RegularAmount instance29 = new RegularAmount("\"\"\"\"\\\\", Frequency.WEEK);
        RegularAmount instance30 = new RegularAmount("100f.00", Frequency.TWO_WEEK);
        RegularAmount instance31 = new RegularAmount("100.0", Frequency.TWO_WEEK);
        RegularAmount instance32 = new RegularAmount("1.00.0", Frequency.FOUR_WEEK);
        RegularAmount instance33 = new RegularAmount("00.0", Frequency.FOUR_WEEK);
        RegularAmount instance34 = new RegularAmount("!!!", Frequency.FOUR_WEEK);
        RegularAmount instance35 = new RegularAmount("*", Frequency.YEAR);
        RegularAmount instance36 = new RegularAmount("hello@NaN", Frequency.WEEK);

        // Deny/don't allow zero (division by 0) as valid amount
        RegularAmount instance37 = new RegularAmount("0", Frequency.WEEK);
        RegularAmount instance38 = new RegularAmount("0", Frequency.TWO_WEEK);
        RegularAmount instance39 = new RegularAmount("0", Frequency.FOUR_WEEK);
        RegularAmount instance40 = new RegularAmount("0", Frequency.MONTH);
        RegularAmount instance41 = new RegularAmount("0", Frequency.QUARTER);
        RegularAmount instance42 = new RegularAmount("0", Frequency.YEAR);

        RegularAmount instance43 = new RegularAmount("0.00", Frequency.WEEK);
        RegularAmount instance44 = new RegularAmount("0.00", Frequency.TWO_WEEK);
        RegularAmount instance45 = new RegularAmount("0.00", Frequency.FOUR_WEEK);
        RegularAmount instance46 = new RegularAmount("0.00", Frequency.MONTH);
        RegularAmount instance47 = new RegularAmount("0.00", Frequency.QUARTER);
        RegularAmount instance48 = new RegularAmount("0.00", Frequency.YEAR);

        // Reject "MONTH" - what is a "month"? No actual defined amount of weeks..!
        RegularAmount instance49 = new RegularAmount("10", Frequency.MONTH);
        RegularAmount instance50 = new RegularAmount("10.00", Frequency.MONTH);
        RegularAmount instance51 = new RegularAmount("200", Frequency.MONTH);
        RegularAmount instance52 = new RegularAmount("500000.00", Frequency.MONTH);
        
        // Finally test empty instance (default constructor)
        RegularAmount instance53 = new RegularAmount();

        assertTrue(!runBeanConstraintValidator(instance1));
        assertTrue(!runBeanConstraintValidator(instance2));
        assertTrue(!runBeanConstraintValidator(instance3));
        assertTrue(!runBeanConstraintValidator(instance4));
        assertTrue(!runBeanConstraintValidator(instance5));
        assertTrue(!runBeanConstraintValidator(instance6));
        assertTrue(!runBeanConstraintValidator(instance7));
        assertTrue(!runBeanConstraintValidator(instance8));
        assertTrue(!runBeanConstraintValidator(instance9));
        assertTrue(!runBeanConstraintValidator(instance10));
        assertTrue(!runBeanConstraintValidator(instance11));
        assertTrue(!runBeanConstraintValidator(instance12));
        assertTrue(!runBeanConstraintValidator(instance13));
        assertTrue(!runBeanConstraintValidator(instance14));
        assertTrue(!runBeanConstraintValidator(instance15));
        assertTrue(!runBeanConstraintValidator(instance16));
        assertTrue(!runBeanConstraintValidator(instance17));
        assertTrue(!runBeanConstraintValidator(instance18));
        assertTrue(!runBeanConstraintValidator(instance19));
        assertTrue(!runBeanConstraintValidator(instance20));
        assertTrue(!runBeanConstraintValidator(instance21));
        assertTrue(!runBeanConstraintValidator(instance22));
        assertTrue(!runBeanConstraintValidator(instance23));
        assertTrue(!runBeanConstraintValidator(instance24));
        assertTrue(!runBeanConstraintValidator(instance25));
        assertTrue(!runBeanConstraintValidator(instance26));
        assertTrue(!runBeanConstraintValidator(instance27));
        assertTrue(!runBeanConstraintValidator(instance28));
        assertTrue(!runBeanConstraintValidator(instance29));
        assertTrue(!runBeanConstraintValidator(instance30));
        assertTrue(!runBeanConstraintValidator(instance31));
        assertTrue(!runBeanConstraintValidator(instance32));
        assertTrue(!runBeanConstraintValidator(instance33));
        assertTrue(!runBeanConstraintValidator(instance34));
        assertTrue(!runBeanConstraintValidator(instance35));
        assertTrue(!runBeanConstraintValidator(instance36));
        assertTrue(!runBeanConstraintValidator(instance37));
        assertTrue(!runBeanConstraintValidator(instance38));
        assertTrue(!runBeanConstraintValidator(instance39));
        assertTrue(!runBeanConstraintValidator(instance40));
        assertTrue(!runBeanConstraintValidator(instance41));
        assertTrue(!runBeanConstraintValidator(instance42));
        assertTrue(!runBeanConstraintValidator(instance43));
        assertTrue(!runBeanConstraintValidator(instance44));
        assertTrue(!runBeanConstraintValidator(instance45));
        assertTrue(!runBeanConstraintValidator(instance46));
        assertTrue(!runBeanConstraintValidator(instance47));
        assertTrue(!runBeanConstraintValidator(instance48));
        assertTrue(!runBeanConstraintValidator(instance49));
        assertTrue(!runBeanConstraintValidator(instance50));
        assertTrue(!runBeanConstraintValidator(instance51));
        assertTrue(!runBeanConstraintValidator(instance52));

        assertTrue(!runBeanConstraintValidator(instance53));
    }
}
