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
public class PermutationTest {
    
    public PermutationTest() {
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
     * Test of setData method, of class Permutation.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        Permutation instance = new Permutation();
        instance.setData();
        int result = instance.getNumberOfElementsJson();
        Assertions.assertEquals(3, result, "setData - default - number of elements");
    }
    
    /**
     * Test of setClear method, of class Permutation.
     */
    @Test
    public void testSetClear() {
        System.out.println("setClear");
        Permutation instance = new Permutation();
        instance.setClear();
        int result = instance.getNumberOfElementsJson();
        Assertions.assertEquals(0, result, "setClear - clearing data before run new permutation");
    }

    /**
     * Test of setValue method, of class Permutation.
     */
    @Test
    public void testSetValue_String() {
        System.out.println("setValue");
        String input = "[1, 2]";
        Permutation instance = new Permutation();
        instance.setValue(input);
        int result = instance.getNumberOfElementsJson();
        Assertions.assertEquals(2, result, "setData - custom");
    }

    /**
     * Test of createData method, of class Permutation.
     */
    @Test
    public void testCreateData() {
        System.out.println("createData");
        Permutation instance = new Permutation();
        instance.createData();
        String result = instance.getProgress();
        Assertions.assertTrue(result.contains("0%"), "Run of thread.");
    }

    /**
     * Test of getProgress method, of class Permutation.
     */
    @Test
    public void testGetProgress() {
        System.out.println("getProgress");
        Permutation instance = new Permutation();
        String result = instance.getProgress();
        Assertions.assertTrue(result.equals("0%"), "Progress of permutation");
    }

    /**
     * Test of getValue method, of class Permutation.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Permutation instance = new Permutation();
        String expResult = "[]";
        String result = instance.getValue();
        Assertions.assertEquals(expResult, result, "getValue");
    }

    /**
     * Test of getNumberOfElementsJson method, of class Permutation.
     */
    @Test
    public void testGetNumberOfElementsJson() {
        System.out.println("getNumberOfElementsJson");
        Permutation instance = new Permutation();
        int expResult = 0;
        int result = instance.getNumberOfElementsJson();
        Assertions.assertEquals(expResult, result, "getNumberOfElementsJson");
    }

    /**
     * Test of getMaxElements method, of class Permutation.
     */
    @Test
    public void testGetMaxElements() {
        System.out.println("getMaxElements");
        Permutation instance = new Permutation();
        int expResult = 19;
        int result = instance.getMaxElements();
        Assertions.assertEquals(expResult, result, "getMaxElements.");
    }

    /**
     * Test of setValue method, of class Permutation.
     */
    @Test
    public void testSetValue_0args() {
        System.out.println("setValue");
        Permutation instance = new Permutation();
        instance.setValue();
        int result = instance.getNumberOfElementsJson();
        Assertions.assertEquals(3, result, "setData - default");
    }
    
}
