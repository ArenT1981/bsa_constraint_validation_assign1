/* RunAmountTest.java
 * 
 * See LICENSE.txt in project root directory for license details.
 */
package com.aren.nhs_bsa_assignment;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * JUnit test class for <code>RunAmount.java</code>.
 *
 * @author Aren Tyr.
 * @version 0.9 2020-08-24
 */
public class RunAmountTest
{
    /** 
     * Test default constructor.
     * 
     * Here for code coverage.
     */
    @Test
    public void testEmptyConstructor()
    {
        // Do nothing but instantiate
        RunAmount newRun = new RunAmount();
    }

    /**
     * JUnit test of <code>isDEBUG</code> and <code>setDEBUG</code> methods, of
     * class <code>RunAmount</code>.
     *
     * Mostly just a test of state, since it is just a simple getter &amp;
     * setter, but here for completeness.
     */
    @Test
    public void testIsDEBUGandSetDEBUG()
    {
        boolean expResult = false;
        boolean result = RunAmount.isDEBUG();
        assertEquals(expResult, result);
        boolean DEBUG = true;
        RunAmount.setDEBUG(DEBUG);
        assertEquals(RunAmount.isDEBUG(), true);
        DEBUG = false;
        RunAmount.setDEBUG(DEBUG);
        assertEquals(RunAmount.isDEBUG(), false);

    }

    /**
     * JUnit test of <code>isRUNTESTS</code> and <code>setRUNTESTS</code>
     * methods, of class <code>RunAmount</code>.
     *
     * Mostly just a test of state, since it is just a simple getter &amp;
     * setter, but here for completeness.
     */
    @Test
    public void testIsRUNTESTSandSetRUNTESTS()
    {

        boolean expResult = false;
        boolean result = RunAmount.isRUNTESTS();
        assertEquals(expResult, result);
        boolean RUNTESTS = true;
        RunAmount.setRUNTESTS(RUNTESTS);
        assertEquals(RunAmount.isRUNTESTS(), true);
        RUNTESTS = false;
        RunAmount.setRUNTESTS(RUNTESTS);
        assertEquals(RunAmount.isRUNTESTS(), false);
    }
     
    /**
     * Test of all of the non-interactive branches from the main method.
     */
    @Test
    public void testMain()
    {
        String[] args = {"4"};
        RunAmount.main(args);
        args[0] = "3";
        RunAmount.main(args);

        // default branch of parseChoice, non-existant option
        args[0] = "5";
        RunAmount.main(args);

        // This will trigger the "default"/printUsage() branch
        String[] wrongArgs = {"1", "2", "3"};
        RunAmount.main(wrongArgs);
    }

    /**
     * Test interactive branch "1".
     *
     * These are split into separate test methods to avoid issues with
     * pollution/buffering of the input stream cache.
     */
    @Test
    public void testMainInter1()
    {
        System.setIn(new ByteArrayInputStream("10.00\n0\n".getBytes()));
        RunAmount.main(new String[] {"1"});
    }

    /**
     * Test interactive branch "2".
     *
     * This one causes the while loop to iterate one more time.
     */
    @Test
    public void testMainInter2()
    {
        System.setIn(new ByteArrayInputStream("10.00\n3\n50.00\n0\n".getBytes()));
        RunAmount.main(new String[] {"2"});
    }

    /**
     * Test all branches of the validation switch statement.
     */
    @Test
    public void testrunValidation()
    {
        RunAmount.runValidation(null, null);
        RunAmount.runValidation("60.00", "1");
        RunAmount.runValidation("50.00", "2");
        RunAmount.runValidation("40.00", "3");
        RunAmount.runValidation("30.00", "4");
        RunAmount.runValidation("20.00", "5");
        RunAmount.runValidation("10.00", "6");
    }

}
