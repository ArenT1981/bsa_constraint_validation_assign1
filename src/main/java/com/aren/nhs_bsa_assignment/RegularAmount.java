/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aren.nhs_bsa_assignment;

import javax.validation.Constraint;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Length;
/**
 *
 * @author aren
 */

@CheckRegularAmount(groups = {Default.class })
public class RegularAmount
{
    private Frequency frequency;
    
    @Length(min=10,message="Test message. Less than 10.")
    private String amount; 
    
    public Frequency getFrequency() { return frequency; } 
    public void setFrequency(Frequency frequency) { this.frequency = frequency; } 
    public String getAmount() { return amount; } 
    public void setAmount(String amount) { this.amount = amount; } 

    public enum Frequency { WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR; };


}