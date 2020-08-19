/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import com.aren.nhs_bsa_assignment.RegularAmount.Frequency;
import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author aren
 */
public class CheckRegularAmountValidator implements ConstraintValidator<CheckRegularAmount, RegularAmount>
{
    // Debugging messages on?
    private static final boolean DEBUG = RunAmount.DEBUG;

    private boolean isValidNumericAmount(String inputAmount)
    {
        // Regular expression that requires number starts with at least one digit
        // and optionally has a decimal point & two digits
        return inputAmount.matches("^[0-9]+(\\.\\d{2})?$");
    }

    // This function courtesy of StackExchange:
    // https://stackoverflow.com/questions/2296110/determine-number-of-decimal-place-using-bigdecimal
    // All correct currency/exact pence values must have <= 2 decimal points (e.g. 1p is smallest
    // granularity)
    private int getNumberOfDecimalPlaces(BigDecimal bigDecimal)
    {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    private double validFrequencyDivisor(Frequency inputFreq)
    {
        switch (inputFreq)
        {
            case WEEK:
                return 1.00;
            case TWO_WEEK:
                return 2.00;
            case FOUR_WEEK:
                return 4.00;
            case MONTH:
                // FIXME: How to deal with edge case? Get current calendar month?
                // Use 4?
                return 4.00;
            case QUARTER:
                return 13.00;
            case YEAR:
                return 52.00;
            default:
                return -1.00;
        }
    }

    private boolean isValidExactPenceAmount(String amt, double divisor)
    {
        if(DEBUG)
        { System.out.println("Divisor is: " + divisor); }

        double val = Double.parseDouble(amt) / divisor;
        
        if(DEBUG)
        { System.out.println("Double division result is: " + val); }

        BigDecimal checkCurrencyPence = new BigDecimal(val);
        return getNumberOfDecimalPlaces(checkCurrencyPence) <= 2; 
    }

    @Override
    public void initialize(CheckRegularAmount constraintAnnotation)
    {
        //System.out.println("testing init method");
        //this.regularAmountCheck = constraintAnnotation;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValid(RegularAmount value, ConstraintValidatorContext context)
    {
        String amt = value.getAmount();
        boolean numericAmountCheck = isValidNumericAmount(amt);
        double frequencyCheck = validFrequencyDivisor(value.getFrequency());

        if (numericAmountCheck == true)
        {
            if(DEBUG)
                System.out.println("Valid number: " + amt);
            
            boolean validPenceAmount = isValidExactPenceAmount(amt, frequencyCheck);
            if(validPenceAmount)
            {
                if(DEBUG)
                { System.out.println("VALID/EXACT Amount registered"); }
                
                return true;
            }
            else
            {
                if(DEBUG)
                { System.out.println("INVALID/NON-EXACT pence amount registered"); }
                
                return false;
            }
        } 
        else
        {
            if(DEBUG)
            { System.out.println("Invalid number: " + amt); }
            
            return false;
        }
    }
}
