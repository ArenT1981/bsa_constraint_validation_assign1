/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * An amazing class that does something.
 *
 * @author aren
 */
public class RunAmount
{
    // (Global) debugging messages on?
    public final static boolean DEBUG = false;

    // Run test dataset?
    private final static boolean RUNTESTS = true;
    
    // Some bling. Fancy console output colours (works under Unix)
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    private ArrayList<RegularAmount> getSampleAmountDataset()
    {
        ArrayList<RegularAmount> sampleAmountTestDataset = new ArrayList<RegularAmount>();

        // Test value 1 - valid number
        RegularAmount amountTestValue1 = new RegularAmount();
        amountTestValue1.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue1.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue1);

        // Test value 2 - valid number
        RegularAmount amountTestValue2 = new RegularAmount();
        amountTestValue2.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue2.setAmount("100000");
        sampleAmountTestDataset.add(amountTestValue2);

        // Test value 3 - invalid number (only one digit after decimal point)
        RegularAmount amountTestValue3 = new RegularAmount();
        amountTestValue3.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue3.setAmount("100.0");
        sampleAmountTestDataset.add(amountTestValue3);

        // Test value 4 - invalid number (contains a non-digit character)
        RegularAmount amountTestValue4 = new RegularAmount();
        amountTestValue4.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue4.setAmount("10f0.00");
        sampleAmountTestDataset.add(amountTestValue4);

        // Test value 5 - invalid number (contains a non-digit character)
        RegularAmount amountTestValue5 = new RegularAmount();
        amountTestValue5.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue5.setAmount("100.0a");
        sampleAmountTestDataset.add(amountTestValue5);

        // Test value 6 - invalid number (negative value)
        RegularAmount amountTestValue6 = new RegularAmount();
        amountTestValue6.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue6.setAmount("-100.00");
        sampleAmountTestDataset.add(amountTestValue6);

        // Test value 7 - invalid number (all characters)
        RegularAmount amountTestValue7 = new RegularAmount();
        amountTestValue7.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue7.setAmount("hello");
        sampleAmountTestDataset.add(amountTestValue7);

        // Test value 8 - invalid number (contains character)
        RegularAmount amountTestValue8 = new RegularAmount();
        amountTestValue8.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue8.setAmount("10hello");
        sampleAmountTestDataset.add(amountTestValue8);

        // Test value 9 - invalid number (empty string)
        RegularAmount amountTestValue9 = new RegularAmount();
        amountTestValue9.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue9.setAmount("");
        sampleAmountTestDataset.add(amountTestValue9);

        // Test value 10 - invalid number (special character)
        RegularAmount amountTestValue10 = new RegularAmount();
        amountTestValue10.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue10.setAmount("*");
        sampleAmountTestDataset.add(amountTestValue10);

        // Test value 11 - valid number (TWO_WEEK), correct pence
        RegularAmount amountTestValue11 = new RegularAmount();
        amountTestValue11.setFrequency(RegularAmount.Frequency.TWO_WEEK);
        amountTestValue11.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue11);

        // Test value 12 - valid number (FOUR_WEEK), correct pence
        RegularAmount amountTestValue12 = new RegularAmount();
        amountTestValue12.setFrequency(RegularAmount.Frequency.FOUR_WEEK);
        amountTestValue12.setAmount("100000");
        sampleAmountTestDataset.add(amountTestValue12);

        // Test value 13 - valid number (FOUR_WEEK), no exact pence
        RegularAmount amountTestValue13 = new RegularAmount();
        amountTestValue13.setFrequency(RegularAmount.Frequency.FOUR_WEEK);
        amountTestValue13.setAmount("99.99");
        sampleAmountTestDataset.add(amountTestValue13);

        return sampleAmountTestDataset;

    }

    private void validateSampleAmounts(ArrayList<RegularAmount> amountDataset)
    {
        for(RegularAmount amt : amountDataset)
        {
            if(DEBUG)
            { System.out.println("Validating amount... "); }
            // Validate the Bean/class
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<RegularAmount>> violations = validator.validate(amt);

            if(DEBUG)
            { System.out.println("Size is: " + violations.size()); }

            if(DEBUG)
            {
                // Display the validation messages
                for(ConstraintViolation<RegularAmount> violation : violations)
                {
                    System.out.println(violation.getMessage());
                }
            }
            
            System.out.println("====================="); 
            // Did it pass the ConstraintValidator without any problems? (i.e is it VALID?)
            if(violations.size() == 0)
            { System.out.println(ANSI_GREEN + "PASSED" + ANSI_RESET); }                
            else
            { System.out.println(ANSI_RED + "FAILED" + ANSI_RESET); }
                                      
            System.out.println("Amount: " + amt.getAmount());
            System.out.println("Frequency: " + amt.getFrequency());

        }

    }

    /**
     *
     * @param args do something
     */
    public static void main(String args[])
    {
        if(RUNTESTS)
        {
            if(DEBUG)
            { System.out.println("Initialising tests..."); }

            RunAmount testDataset = new RunAmount();
            testDataset.validateSampleAmounts(testDataset.getSampleAmountDataset());
        } 
        else
        {
            if(DEBUG)
            { System.out.println("Tests are not enabled."); }
        }
    }

}
