/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcf2csv;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ahmad
 */
public class CommandLineTest {
    
    public CommandLineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVCFFilePath method, of class CommandLine.
     */
    @Test
    public void testGetVCFFilePath() {
        System.out.println("getVCFFilePath");
        CommandLine instance = null;
        String expResult = "";
        String result = instance.getVCFFilePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCSVFilePath method, of class CommandLine.
     */
    @Test
    public void testGetCSVFilePath() {
        System.out.println("getCSVFilePath");
        CommandLine instance = null;
        String expResult = "";
        String result = instance.getCSVFilePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doReverse method, of class CommandLine.
     */
    @Test
    public void testDoReverse() {
        System.out.println("doReverse");
        CommandLine instance = null;
        boolean expResult = false;
        boolean result = instance.doReverse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSet method, of class CommandLine.
     */
    @Test
    public void testIsSet() {
        System.out.println("isSet");
        CommandLine instance = null;
        boolean expResult = false;
        boolean result = instance.isSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
