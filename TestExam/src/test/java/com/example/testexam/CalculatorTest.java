package com.example.testexam;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() throws Exception {
        assertEquals(10, Calculator.add(2, 8));
    }

    @Test
    public void subtract() throws Exception {
        assertEquals(0, Calculator.subtract(10, 10));
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(0, Calculator.multiply(0, 10));
        assertEquals(10, Calculator.multiply(1, 10));
    }

    @Test
    public void division() throws Exception {
        assertEquals(2, Calculator.division(5, 2), 0);
        assertEquals(2, Calculator.division(4, 2));
    }

}