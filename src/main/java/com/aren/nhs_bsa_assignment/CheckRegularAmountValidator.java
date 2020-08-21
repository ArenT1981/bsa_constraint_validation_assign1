/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import com.aren.nhs_bsa_assignment.RegularAmount.Frequency;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    // All correct currency/exact pence values must have <= 2 decimal points -
    // (e.g. 1p is smallest granularity in UK currency)
    // This function courtesy of StackExchange:
    // https://stackoverflow.com/questions/2296110/determine-number-of-decimal-place-using-bigdecimal
    // DEPRECATED: Replaced with inbuilt BigDecimal object and RoundingMode exception
    // A more elegant "Java-esque" solution
    /* 
    private int getNumberOfDecimalPlaces(BigDecimal bigDecimal)
    {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }
    */

    // FIXME: replace double with BigDecimal?
    private BigDecimal validFrequencyDivisor(Frequency inputFreq)
    {
        switch(inputFreq)
        {
            case WEEK:
                return new BigDecimal("1.00");
            case TWO_WEEK:
                return new BigDecimal("2.00");
            case FOUR_WEEK:
                return new BigDecimal("4.00");
            case MONTH:
                // FIXME: How to deal with edge case? Get current calendar month?
                // Use 4?
                return new BigDecimal("4.00");
            case QUARTER:
                return new BigDecimal("13.00");
            case YEAR:
                return new BigDecimal("52.00");
            default:
                return new BigDecimal("-1.00");
        }
    }

    private boolean isValidExactPenceAmount(String amt, BigDecimal divisor)
    {


        BigDecimal numerator = new BigDecimal(amt);
        BigDecimal result = new BigDecimal("-9999.9999");
        
        if(DEBUG)
        {
            System.out.println("Numerator is: " + numerator);
            System.out.println("Divisor is: " + divisor);
        }
        
        //int lessThanZero = 

        // Only accept positive/non-zero Amount, and reject all frequencies less than
        // 1 (i.e. the negative "default" case)
        try
        {
            if(numerator.compareTo(new BigDecimal("0")) > 0 && divisor.compareTo(new BigDecimal("1")) >= 0)
            {
                // Use RoundingMode.UNNECESSARY to throw ArithmeticException if _any_ rounding 
                // occurs outside of the scale of two decimal points
                result = numerator.divide(divisor, 2, RoundingMode.UNNECESSARY);
            } 
        }
        catch(ArithmeticException ae)
        {
            if(DEBUG)
            {
                System.out.println("Non-exact division within requested scale (two decimal places): " + ae);
            }
            return false;
        }

        if(DEBUG)
        {
            System.out.println("Double division result is: " + result);
        }

        // DEPRECATED (replaced with BigDecimal)
        //BigDecimal checkCurrencyPence = new BigDecimal(val);
        //return getNumberOfDecimalPlaces(checkCurrencyPence)<= 2;
        
        // If we reach here then exact division within two decimal points
        // must have occurred
        return true;
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
        BigDecimal frequencyCheck = validFrequencyDivisor(value.getFrequency());
        
        if(numericAmountCheck == true)
        {
            if(DEBUG)
            {
                System.out.println("Valid number: " + amt);
            }

            boolean validPenceAmount = isValidExactPenceAmount(amt, frequencyCheck);
            if(validPenceAmount)
            {
                if(DEBUG)
                {
                    System.out.println("VALID/EXACT Amount registered");
                }

                return true;
            }
            else
            {
                if(DEBUG)
                {
                    System.out.println("INVALID/NON-EXACT pence Amount registered");
                }

                return false;
            }
        }
        else
        {
            if(DEBUG)
            {
                System.out.println("Invalid Number format -> no possibility of exact pence: " + amt);
            }

            return false;
        }
    }
}
