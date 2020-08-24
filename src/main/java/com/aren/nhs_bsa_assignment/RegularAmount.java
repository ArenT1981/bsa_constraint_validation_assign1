/* RegularAmount.java
 *
 * See LICENSE.txt in project root directory for license details.
 */
package com.aren.nhs_bsa_assignment;

import org.hibernate.validator.constraints.Length;

/**
 * Based on the supplied template class with a few modifications/additions.
 * 
 * @author Aren Tyr
 * @version 0.8 - 2020-08-22
 */
@CheckRegularAmount
public class RegularAmount
{

    /**
     * Setup default values (that will guarantee to fail validation).
     */
    public RegularAmount() 
    { 
        this("-1.00", RegularAmount.Frequency.WEEK);
    }
    
    /** 
     * An overloaded constructor for convenience, to setup <code>amount</code>
     * and <code>frequency</code> on object initialisation.
     * 
     * @param amt String expression representing a currency value in UK sterling.
     * 
     * @param frequency <code>RegularAmount.Frequency</code> enum value; one of <code>
     *        WEEK</code>, <code>TWO_WEEK</code>, <code>FOUR_WEEK</code>, 
     *        <code>MONTH</code>, <code>QUARTER</code>, or <code>YEAR</code>.
     */
    public RegularAmount(String amt, Frequency frequency)
    {
        this.amount = amt;
        this.frequency = frequency;
    }
    
    private Frequency frequency;
    
    // Restrict maximum input to 11 digits (characters) for security/safety.
    // This allows maximum values up to £99999999999 or £99999999.99 theoretically...!
    @Length(max=11,message="* Bad input. Greater than 11 characters.")
    private String amount; 
    
    /**
     * Standard getter to retrieve the <code>Frequency</code> enum value.
     * 
     * @return The currently set <code>Frequency</code> enum.
     */
    public Frequency getFrequency() { return frequency; } 

    /**
     * Standard setter to set the <code>Frequency</code> enum value.
     * 
     * @param frequency <code>RegularAmount.Frequency</code> enum value; one of <code>
     *        WEEK</code>, <code>TWO_WEEK</code>, <code>FOUR_WEEK</code>, 
     *        <code>MONTH</code>, <code>QUARTER</code>, or <code>YEAR</code>.
     */
    public void setFrequency(Frequency frequency) { this.frequency = frequency; } 

    /**
     * Standard getter to retrieve the <code>amount</code> string representing
     * the currency value (regardless of whether it represents a coherent/valid 
     * currency amount).
     * 
     * @return The currently set amount string. 
     */
    public String getAmount() { return amount; } 

    /**
     * Standard setter to set the <code>amount</code> string representing the
     * currency value.
     * 
     * @param amount A string to represent a currency amount; e.g. "9.99".
     */
    public void setAmount(String amount) { this.amount = amount; } 

    /**
     * Enumerated type that defines the set of terms that represents a time
     * series/weekly time amount. 
     * 
     * Acts as the divisor mapping value for determining whether an exact pence
     * amount is possible or not.
     */
    public enum Frequency { 

        /**
         *
         */
        WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR; };
}