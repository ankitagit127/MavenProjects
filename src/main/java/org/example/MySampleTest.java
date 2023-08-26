package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MySampleTest {

    @BeforeClass
    public void setup() {
        System.out.println("This is Before Class method");
    }

    @Test
    public void test1() {
        System.out.println("Test 1");
        int a = 6;
        int b = 8;

        Assert.assertEquals(a + b, 14);
    }

    @Test
    public void test2() {
        System.out.println("Test 2");
        int a = 6;
        int b = 8;

        Assert.assertEquals(b - a, 2);
    }

    @BeforeClass
    public void tearDown() {
        System.out.println("This is After Class method");
    }

}
