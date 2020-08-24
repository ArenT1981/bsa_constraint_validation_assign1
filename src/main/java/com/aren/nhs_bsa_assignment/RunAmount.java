/* RunAmount.java
 *
 * See LICENSE.txt in project root directory for license details.
 */
package com.aren.nhs_bsa_assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * A driver class used to demonstrate/test/examine the behaviour of
 * CheckRegularAmount/CheckRegularAmountValidator classes/JavaBean
 * ConstraintValidator.
 *
 * @author Aren Tyr
 * @version 0.7 - 2020-08-22
 */
public class RunAmount
{

    /**
     * Acts as a global debugging message switch (other classes reference it).
     */
    public static boolean DEBUG = false;

    /**
     * Acts as a switch to run through and display the explicit test/demo values
     * used as part of the development process.
     */
    private static boolean RUNTESTS = false;

    public static boolean isDEBUG()
    {
        return DEBUG;
    }

    public static void setDEBUG(boolean DEBUG)
    {
        RunAmount.DEBUG = DEBUG;
    }

    public static boolean isRUNTESTS()
    {
        return RUNTESTS;
    }

    public static void setRUNTESTS(boolean RUNTESTS)
    {
        RunAmount.RUNTESTS = RUNTESTS;
    }

    /**
     * Some bling/aesthetic touches for console output. Fancy console output
     * colours (works under Unix, should work on a Mac terminal, untested under
     * Windows but should work with recent builds undre Windows 10).
     *
     * @see
     * <a href="https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println">
     * StackExchange thread</a>.
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Internal development/test dataset. Since the validation class is mostly
     * an "invisible" entity that does the back-end work behind the scenes, this
     * at least gives us some output for sanity checking that the code is
     * working as expected.
     *
     * @return An array containing a series of <code>RegularAmount</code>
     * objects with <code>amount</code> and <code>Frequency</code> values to
     * pass to the validator class.
     */
    private static ArrayList<RegularAmount> getSampleAmountDataset()
    {
        ArrayList<RegularAmount> sampleAmountTestDataset = new ArrayList<>();

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

        // Test value 14 - valid number (FOUR_WEEK), no exact pence
        RegularAmount amountTestValue14 = new RegularAmount();
        amountTestValue14.setFrequency(RegularAmount.Frequency.FOUR_WEEK);
        amountTestValue14.setAmount("33.33");
        sampleAmountTestDataset.add(amountTestValue14);

        // Test value 15 - invalid number (LARGER then 10 char restriction!), and no exact pence
        RegularAmount amountTestValue15 = new RegularAmount();
        amountTestValue15.setFrequency(RegularAmount.Frequency.FOUR_WEEK);
        amountTestValue15.setAmount("999999999.99");
        sampleAmountTestDataset.add(amountTestValue15);

        // Test value 16 - valid number (just within 11 char restriction), correct pence
        RegularAmount amountTestValue16 = new RegularAmount();
        amountTestValue16.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue16.setAmount("99999999.99");
        sampleAmountTestDataset.add(amountTestValue16);

        // Test value 17 - valid number (just within 11 char restriction), correct pence
        RegularAmount amountTestValue17 = new RegularAmount();
        amountTestValue17.setFrequency(RegularAmount.Frequency.WEEK);
        amountTestValue17.setAmount("0");
        sampleAmountTestDataset.add(amountTestValue17);

        // Test value 18 - test empty constructor, should fail
        RegularAmount amountTestValue18 = new RegularAmount();
        sampleAmountTestDataset.add(amountTestValue18);

        return sampleAmountTestDataset;
    }

    /**
     * Internal method that takes in an array of <code>RegularAmount</code>
     * objects and performs validation on them, displaying any violation
     * messages.
     *
     * @param amountDataset an <code>ArrayList</code> of
     * <code>RegularAmount</code> objects to validate.
     */
    private static void validateSampleAmounts(ArrayList<RegularAmount> amountDataset)
    {
        for(RegularAmount amt : amountDataset)
        {
            System.out.println("=====================");

            if(DEBUG)
            {
                System.out.println("Validating amount... ");
            }

            // Validate the Bean/class
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<RegularAmount>> violations = validator.validate(amt);

            if(DEBUG)
            {
                System.out.println("Violations detected: " + violations.size());
            }

            if(DEBUG)
            {
                // Display the validation messages
                for(ConstraintViolation<RegularAmount> violation : violations)
                {
                    System.out.println(violation.getMessage());
                }
            }

            // Did it pass the ConstraintValidator without any problems? (i.e is it VALID?)
            if(violations.size() == 0)
            {
                System.out.println(ANSI_GREEN + "PASSED:" + ANSI_RESET);
                // NOTE: Aggregate/count valid results to show at the end
            }
            else
            {
                System.out.println(ANSI_RED + "FAILED:" + ANSI_RESET);
            }

            System.out.println("-> Amount: " + amt.getAmount());
            System.out.println("-> Frequency: " + amt.getFrequency());

        }

    }

    private static void printFrequencyChoiceMenu()
    {
        System.out.println("Please enter frequency number [1-6], where frequency is one of:");
        System.out.println("[1] - WEEK");
        System.out.println("[2] - TWO_WEEK");
        System.out.println("[3] - FOUR_WEEK");
        System.out.println("[4] - MONTH");
        System.out.println("[5] - QUARTER");
        System.out.println("[6] - YEAR");
        System.out.println("");
        System.out.println("Enter 0 to quit application.");
        System.out.print("> ");

    }

    private static void runValidation(String inputAmount, String frequencyChoice)
    {

        RegularAmount amount;

        if(inputAmount == null)
        {
            inputAmount = "-1.00";
        }

        if(frequencyChoice == null)
        {
            frequencyChoice = "0";
        }

        switch(frequencyChoice)
        {
            case "1":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.WEEK);
                break;
            case "2":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.TWO_WEEK);
                break;
            case "3":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.FOUR_WEEK);
                break;
            case "4":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.MONTH);
                break;
            case "5":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.QUARTER);
                break;
            case "6":
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.YEAR);
                break;
            default:
                amount = new RegularAmount(inputAmount, RegularAmount.Frequency.WEEK);
                frequencyChoice = "0";
                System.out.println("Quitting application...");
                break;
        }

        if(!frequencyChoice.equals("0"))
        {
            ArrayList<RegularAmount> validateAmount = new ArrayList<>();
            validateAmount.add(amount);
            validateSampleAmounts(validateAmount);
        }

    }

    private static void interactiveMode()
    {
        String inputAmount;
        String frequencyChoice;
        
        System.out.println("");
        System.out.println("INTERACTIVE MODE");
        System.out.println("");

        // Keep getting user input until they enter 0 for Frequency to quit application
        do
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter the currency amount: ");
            System.out.print("> ");
            inputAmount = keyboard.nextLine();
            printFrequencyChoiceMenu();
            frequencyChoice = keyboard.nextLine();
            runValidation(inputAmount, frequencyChoice);
        }
        while(!frequencyChoice.equals("0"));

    }

    private static void printUsage()
    {
        System.out.println("");
        System.out.println("============================");
        System.out.println("Usage: ");
        System.out.println("java -jar NHS_BSA_Assigment1.jar ARG");
        System.out.println("");
        System.out.println("Where ARG (without quotes) is one of: ");
        System.out.println("\"1\" : Run validator in interactive mode.");
        System.out.println("\"2\" : Interactive mode, verbose messaging");
        System.out.println("\"3\" : Run validator on example test values.");
        System.out.println("\"4\" : Example test values, verbose messagaging.");
    }

    private static void runTests()
    {
        //RunAmount testDataset = new RunAmount();
        //testDataset.validateSampleAmounts(testDataset.getSampleAmountDataset());
        validateSampleAmounts(getSampleAmountDataset());

    }

    /**
     * Main entry point.
     *
     * @param args Specify operation mode.
     */
    public static void main(String args[])
    {
        if(args.length == 1)
        {
            switch(args[0])
            {

                case "1":
                    interactiveMode();
                    break;
                case "2":
                    setDEBUG(true);
                    interactiveMode();
                    break;
                case "3":
                    setRUNTESTS(true);
                    runTests();
                    break;
                case "4":
                    setRUNTESTS(true);
                    setDEBUG(true);
                    System.out.println("Initialising tests...");
                    runTests();
                    break;
                default:
                    printUsage();
                    break;

            }
        }
        else
        {
            printUsage();
        }
    }

}
