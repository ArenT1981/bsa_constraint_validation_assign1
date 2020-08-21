/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Aren Tyr.
 */
public class RegularAmountTest
{

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator()
    {
        // Setup the validators
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close()
    {
        validatorFactory.close();
    }

    protected void setUp() throws Exception
    {
    }

    protected void tearDown() throws Exception
    {
    }

    private boolean runBeanConstraintValidator(RegularAmount ra)
    {
        Set<ConstraintViolation<RegularAmount>> violations = validator.validate(ra);
        if(violations.isEmpty())
        {
            System.out.println("-> VALID amount/frequency. \nAmount: " + 
                    ra.getAmount() + " \nFrequency: " + ra.getFrequency());
            return true;
        }
        else
        {
            System.out.println("-> INVALID amount/frequency. \nAmount: \"" + 
                    ra.getAmount() + "\" \nFrequency: " + ra.getFrequency());
            return false;
        }

    }

    /**
     * Test of getFrequency method, of class RegularAmount.
     *
     * Mostly just a test of state, since it is just a simple getter & setter,
     * but here for completeness.
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
     * Test of getAmount method, of class RegularAmount.
     *
     * Mostly just a test of state, since it is just a simple getter & setter,
     * but here for completeness.
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
     * Tests the ConstraintValidator against a sequence of invalid inputs.
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
