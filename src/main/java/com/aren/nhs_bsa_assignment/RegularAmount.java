/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import org.hibernate.validator.constraints.Length;
/**
 *
 * @author Aren Tyr.
 */

@CheckRegularAmount
public class RegularAmount
{
    public RegularAmount() { }
    
    // Provide a handy overloaded constructor for convenience
    public RegularAmount(String amt, Frequency freq)
    {
        this.amount = amt;
        this.frequency = freq;
    }
    
    private Frequency frequency;
    
    // restrict maximum input to 11 digits (characters) for security/safety
    // this allows maximum values up to £99999999999 or £99999999.99 theoretically...!
    @Length(max=11,message="* Bad input. Greater than 11 characters.")
    private String amount; 
    
    public Frequency getFrequency() { return frequency; } 
    public void setFrequency(Frequency frequency) { this.frequency = frequency; } 
    public String getAmount() { return amount; } 
    public void setAmount(String amount) { this.amount = amount; } 

    public enum Frequency { WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR; };


}