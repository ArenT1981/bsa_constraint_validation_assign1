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
 * @author aren
 */
public class RunAmount
{

    private static ArrayList<RegularAmount> getSampleAmountDataset()
    {
        ArrayList<RegularAmount> sampleAmountTestDataset = new ArrayList<RegularAmount>();

        // Test value 1
        RegularAmount amountTestValue1 = new RegularAmount();
        amountTestValue1.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue1.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue1);

        // Test value 2
        RegularAmount amountTestValue2 = new RegularAmount();
        amountTestValue2.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue2.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue2);

        // Test value 3
        RegularAmount amountTestValue3 = new RegularAmount();
        amountTestValue3.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue3.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue3);

        // Test value 4
        RegularAmount amountTestValue4 = new RegularAmount();
        amountTestValue4.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue4.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue4);

        // Test value 5
        RegularAmount amountTestValue5 = new RegularAmount();
        amountTestValue5.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue5.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue5);

        // Test value 6
        RegularAmount amountTestValue6 = new RegularAmount();
        amountTestValue6.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue6.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue6);

        // Test value 7
        RegularAmount amountTestValue7 = new RegularAmount();
        amountTestValue7.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue7.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue7);

        // Test value 8
        RegularAmount amountTestValue8 = new RegularAmount();
        amountTestValue8.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue8.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue8);

        // Test value 9
        RegularAmount amountTestValue9 = new RegularAmount();
        amountTestValue9.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue9.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue9);

        // Test value 10
        RegularAmount amountTestValue10 = new RegularAmount();
        amountTestValue10.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue10.setAmount("100.00");
        sampleAmountTestDataset.add(amountTestValue10);
        
        return sampleAmountTestDataset;

    }

    private static void validateSampleAmounts(ArrayList<RegularAmount> amountDataset)
    {
        for(RegularAmount amt : amountDataset)
        {
             System.out.println("Validating amount... ");
            // Validate the Bean/class
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<RegularAmount>> violations = validator.validate(amt);

            System.out.println("Size is: " + violations.size());

            // Display the validation messages
            for(ConstraintViolation<RegularAmount> violation : violations)
            {
                System.out.println(violation.getMessage());
            }
        }

    }


    /**
     *
     * @param args do something
     */
    public static void main(String args[])
    {

        System.out.println("Hello");

        ArrayList<RegularAmount> sampleAmount = RunAmount.getSampleAmountDataset();
        RunAmount.validateSampleAmounts(sampleAmount);

    }

}
