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
 * @version 1.0 - 2020-08-24
 */
@CheckRegularAmount
public class RegularAmount
{

    /**
     * Setup default values (that usefully will guarantee to fail validation).
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
    
    /** 
     * Use @Length annotation to restrict maximum input to 11 digits (characters) 
     * for security/safety.
     * 
     * This allows maximum values up to £99999999999 or £99999999.99 theoretically...!
     */
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
         * Represents one week = mapped to a divisor of 1.0.
         */
        WEEK, 
        
        /**
         * Represents two weeks = mapped to a divisor of 2.0.
         */
        TWO_WEEK, 
        
        /**
         * Represents four weeks = mapped a divisor of 4.0.
         */
        FOUR_WEEK, 
        
        /**
         * Represents one month, but not accepted as a valid weekly divisor. 
         * 
         * A month does not constitute any exact number of weeks; it is only ever
         * four weeks for February, and only then for non-leap years. Therefore 
         * specified as mapping to a divisor of -1.0 here by design.
         */
        MONTH, 
        
        /**
         * Represents one yearly quarter = mapped to a divisor of 13.0.
         */
        QUARTER, 
        
        /**
         * Represents one entire year = mapped to a divisor of 52.0 by design.
         * 
         * NOTE: A year technically does not exactly map to 52. However, here we
         * are deferring to the requirements specification which states to map to 
         * 52.
         */
        YEAR; };
}