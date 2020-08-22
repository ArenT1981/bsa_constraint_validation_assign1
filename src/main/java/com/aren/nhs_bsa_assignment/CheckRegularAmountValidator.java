/* RegularAmountTest.java
 *
 * See LICENSE.txt in project root directory for license details.
 */
package com.aren.nhs_bsa_assignment;

import com.aren.nhs_bsa_assignment.RegularAmount.Frequency;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Java Bean validator class that implements <code>ConstraintValidator</code>.
 * 
 * Responsible for ensuring that all <code>amount</code> values are validated as valid if
 * they divide into an exact number of pence, based on their weekly frequency value,
 * or are validated as invalid otherwise.
 * 
 * @author Aren Tyr.
 * @version 0.6 - 2020-08-22
 */
public class CheckRegularAmountValidator implements ConstraintValidator<CheckRegularAmount, RegularAmount>
{

    /**
     * Debugging message switch.
     */
    private static final boolean DEBUG = RunAmount.DEBUG;

    
    /**
     * Internal method that employs a regular expression to only accept valid numerical 
     * representations of a currency (in this case, British sterling).
     * 
     * Accepts currency amounts that either feature no pence designation (e.g. 
     * say, <code>100</code> to represent <code>£100.00</code>) or fully qualified 
     * pence designation featuring a decimal point and two digits (e.g. <code>40.50</code>
     * is fine, <code>40.5</code> is not).
     * 
     * @param inputAmount An amount representing a currency value, either with no 
     *        decimal fraction specified, <b>or</b> with a decimal point and exactly 
     *        two digits representing pence.
     * 
     * @return <code>true</code> if the regular expression detects a string of the 
     *         form "xxx" or "xxx.xx" where <b>x</b> represents a numerical digit
     *         [0-9], or <code>false</code> otherwise.
     */
    private boolean isValidNumericAmount(String inputAmount)
    {
        // Regular expression that requires number starts with at least one digit
        // and optionally has a decimal point & two digits
        return inputAmount.matches("^[0-9]+(\\.\\d{2})?$");
    }

    /* DEPRECATED: Replaced with inbuilt BigDecimal object and RoundingMode exception.
       
       BigDecimal is a more elegant "Java-esque" solution that makes use of inbuilt types.
    
       -------------------------------------------------------------------------
    
       All correct currency/exact pence values must have <= 2 decimal points -
       (e.g. 1p is smallest granularity in UK currency)
       This function courtesy of StackExchange:
       https://stackoverflow.com/questions/2296110/determine-number-of-decimal-place-using-bigdecimal
     
    private int getNumberOfDecimalPlaces(BigDecimal bigDecimal)
    {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }
    */

     /**
     * Internal method to convert the enum type <code>Frequency</code> into 
     * a valid numerical divisor.
     * 
     * <code>MONTH</code> is assigned to -1.00/error condition since a month, by definition,
     * does not precisely specify any exact number of weeks (four weeks merely being a 
     * social convention rather than mathematical reality, since months vary from 28-31 
     * days).
     * 
     * @param inputFreq The given <code>RegularAmount.Frequency</code> enum to convert
     *        to a numerical value so that division can be performed. 
     * 
     * @return <code>BigDecimal</code> value that represents numerical value of the
     *         specified frequency (e.g. "FOUR_WEEK" yields 4.00)
     */
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
                return new BigDecimal("-1.00");
            case QUARTER:
                return new BigDecimal("13.00");
            case YEAR:
                return new BigDecimal("52.00");
            default:
                return new BigDecimal("-1.00");
        }
    }

     /**
     * Internal method to actually determine whether the amount is exactly divisible to 
     * a whole pence amount.
     * 
     * Performs the bulk of the work in this class in terms of validating amounts.
     * Employs the <code>BigDecimal</code> object with <code>RoundingMode.UNNECCESARY</code>
     * which will throw an <code>ArithmeticException</code> if the the result of
     * the division does not yield an exact value within the specified scale. Here,
     * scale is <code>2</code> (i.e. two decimal points), since our maximum resolution
     * is a currency pence (UK Sterling).
     * 
     * @param amt The given string representing a numerical currency in an acceptable
     *        format (e.g. "400", "495.34", "50", "10.00", "1"). See 
     *        <code>isValidNumericAmount</code> method above.
     * 
     * @param divisor A <code>BigDecimal</code> value for dividing by.
     * 
     * @return <code>true</code> if the amount <code>amt</code> divides into an 
     *         exact pence amount as per the <code>divisor</code> value, 
     *         <code>false</code> otherwise.
     */   
    private boolean isValidExactPenceAmount(String amt, BigDecimal divisor)
    {


        BigDecimal numerator = new BigDecimal(amt);
        BigDecimal result = new BigDecimal("-9999.9999");
        
        if(DEBUG)
        {
            System.out.println("Numerator is: " + numerator);
            System.out.println("Divisor is: " + divisor);
        }
        
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

        //DEPRECATED (replaced with BigDecimal)
        //BigDecimal checkCurrencyPence = new BigDecimal(val);
        //return getNumberOfDecimalPlaces(checkCurrencyPence)<= 2;
        
        // If we reach here then exact division within two decimal points
        // must have occurred
        return true;
    }

    /**
     * Default/required <code>initialize</code> function. No customisation.
     * 
     * @param constraintAnnotation The <code>CheckRegularAmount</code> constraint
     *        wrapper class.
     */
    @Override
    public void initialize(CheckRegularAmount constraintAnnotation)
    {
    }

    /**
     *
     * Required <code>isValid</code> function that returns the result of the validation.
     * 
     * Calls internal <code>isValidNumericAmount</code>, <code>validFrequencyDivisor</code>,
     * and <code>isValidExactPenceAmount</code> methods to determine validation result.
     * 
     * @param value The <code>RegularAmount</code> instance to be validated.
     * 
     * @param context The validation context.
     * 
     * @return <code>true</code> if the given amount divides into an exactly weekly 
     *         amount as per the specified frequency, <code>false</code> otherwise. 
     */
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
