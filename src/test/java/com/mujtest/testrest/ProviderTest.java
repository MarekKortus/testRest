/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mujtest.testrest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Marek
 */
public class ProviderTest {
    
    public ProviderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMessage method, of class Provider.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Provider instance = new Provider();
        String expResult = "[]";
        String result = instance.getMessage();
        Assertions.assertEquals(expResult, result, "");
    }

    /**
     * Test of getProgress method, of class Provider.
     */
    @Test
    public void testGetProgress() {
        System.out.println("getProgress");
        Provider instance = new Provider();
        String expResult = "0%";
        String result = instance.getProgress();
        Assertions.assertEquals(expResult, result, "");
    }

    /**
     * Test of setMessage method, of class Provider.
     */
    @Test
    public void testSetMessage_0args() {
        System.out.println("setMessage - default");
        Provider instance = new Provider();
        instance.setMessage();
        int expResult = 3;
        int result = instance.getNumberOfElements();
        Assertions.assertEquals(expResult, result, "setMessage - default");
    }

    /**
     * Test of setMessage method, of class Provider.
     */
    @Test
    public void testSetMessage_String() {
        System.out.println("setMessage - user");
        String json = "[1]";
        Provider instance = new Provider();
        instance.setMessage(json);
        int expResult = 1;
        int result = instance.getNumberOfElements();
        Assertions.assertEquals(expResult, result, "setMessage - user");
    }

    /**
     * Test of getNumberOfElements method, of class Provider.
     */
    @Test
    public void testGetNumberOfElements() {
        System.out.println("getNumberOfElements");
        Provider instance = new Provider();
        int expResult = 0;
        int result = instance.getNumberOfElements();
        Assertions.assertEquals(expResult, result, "");
    }

    /**
     * Test of getMaxElements method, of class Provider.
     */
    @Test
    public void testGetMaxElements() {
        System.out.println("getMaxElements");
        Provider instance = new Provider();
        int expResult = 19;
        int result = instance.getMaxElements();
        Assertions.assertEquals(expResult, result, "");
    }
    
}
