/*
 * 
 */
package com.stchome.hapi.proto;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author blee
 */
public class BrianOperationOutcomeExampleTest {

    public BrianOperationOutcomeExampleTest() {
    }

    /**
     * Test of buildClientFault404 method, of class
     * BrianOperationOutcomeExample.
     */
    @Test
    public void testBuildClientFault404() throws Exception {

        System.out.println("// HTTP 404 (Resource not found)");
        System.out.println(BrianOperationOutcomeExample.buildClientFault404());
    }

    /**
     * Test of buildClientFault405 method, of class
     * BrianOperationOutcomeExample.
     */
    @Test
    public void testBuildClientFault405() throws Exception {
        System.out.println("// HTTP 405 (Method not allowed)");
        System.out.println(BrianOperationOutcomeExample.buildClientFault405());
    }

    /**
     * Test of buildClientFault422 method, of class
     * BrianOperationOutcomeExample.
     */
    @Test
    public void testBuildClientFault422() throws Exception {
        System.out.println("// HTTP 405 (Client error, validation exception)");
        System.out.println(BrianOperationOutcomeExample.buildClientFault422());
    }

    /**
     * Test of buildNonTransientFault500 method, of class
     * BrianOperationOutcomeExample.
     */
    @Test
    public void testBuildNonTransientFault500() throws Exception {
        System.out.println("// HTTP 500 (Non-Transient Exception, not going to get better, don't retry)");
        System.out.println(BrianOperationOutcomeExample.buildNonTransientFault500());
    }

    /**
     * Test of buildTransientFault503 method, of class
     * BrianOperationOutcomeExample.
     */
    @Test
    public void testBuildTransientFault503() throws Exception {
        System.out.println("// HTTP 503 (Transient Exception, might get better later)");
        System.out.println(BrianOperationOutcomeExample.buildTransientFault503());
    }

}
