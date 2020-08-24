/* RunAmountTest.java
 * 
 * See LICENSE.txt in project root directory for license details.
 */ 

package com.aren.nhs_bsa_assignment;



import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

/**
 * JUnit test class for <code>RegularAmount.java</code>.
 * @author Aren Tyr.
 * @version 0.3 2020-08-24
 */


public class RunAmountTest
{
    
    public RunAmountTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    //@Before
    public void setUp()
    {
    }
    
    //@After
    public void tearDown()
    {
    }

    /**
     * Test of isDEBUG method, of class RunAmount.
     */
    @Test
    public void testIsDEBUG()
    {
        System.out.println("isDEBUG");
        boolean expResult = false;
        boolean result = RunAmount.isDEBUG();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setDEBUG method, of class RunAmount.
     */
    @Test
    public void testSetDEBUG()
    {
        System.out.println("setDEBUG");
        boolean DEBUG = false;
        RunAmount.setDEBUG(DEBUG);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isRUNTESTS method, of class RunAmount.
     */
    @Test
    public void testIsRUNTESTS()
    {
        System.out.println("isRUNTESTS");
        boolean expResult = false;
        boolean result = RunAmount.isRUNTESTS();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setRUNTESTS method, of class RunAmount.
     */
    @Test
    public void testSetRUNTESTS()
    {
        System.out.println("setRUNTESTS");
        boolean RUNTESTS = false;
        RunAmount.setRUNTESTS(RUNTESTS);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class RunAmount.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = {"4"};
        RunAmount.main(args);
        args[0] = "3";
        RunAmount.main(args);
        String[] wrongArgs = {"1", "2", "3"};
        RunAmount.main(wrongArgs);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }
    
    
    @Test
    public void testprintUsage()
    {
        //InputStream sysInBackup = System.in; // backup System.in to restore it later
        //ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
        //ByteArrayInputStream in = new ByteArrayInputStream(("100.00" + System.lineSeparator() + "0").getBytes());
        //RunAmount.interactiveMode();
        //RunAmount.main(new String[] {"1"});
        //System.setIn(in);
        //RunAmount test = new RunAmount();
        RunAmount.printFrequencyChoiceMenu();
    }
    
    
    @Test
    public void testrunValidation()
    {
        RunAmount.runValidation(null,null);
        RunAmount.runValidation("10.00", "1");
        RunAmount.runValidation("10.00", "2");
        RunAmount.runValidation("10.00", "3");
        RunAmount.runValidation("10.00", "4");
        RunAmount.runValidation("10.00", "5");
        RunAmount.runValidation("10.00", "6");
    }
    
}
