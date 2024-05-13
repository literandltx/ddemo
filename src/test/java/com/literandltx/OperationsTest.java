package com.literandltx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest extends Operations {

    @Test
    public void testNegate() {
        int[] input = {0, 1, 0, 1};
        int[] expectedOutput = {1, 0, 1, 0};
        int[] result = negate(input);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testAnd() {
        int[] x = {0, 1, 0, 1};
        int[] y = {1, 1, 0, 0};
        int[] expectedOutput = {0, 1, 0, 0};
        int[] result = and(x, y);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testOr() {
        int[] x = {0, 1, 0, 1};
        int[] y = {1, 1, 0, 0};
        int[] expectedOutput = {1, 1, 0, 1};
        int[] result = or(x, y);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testXor() {
        int[] x = {0, 1, 0, 1};
        int[] y = {1, 1, 0, 0};
        int[] expectedOutput = {1, 0, 0, 1};
        int[] result = xor(x, y);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testLeftShift1() {
        int[] array = {0, 1, 1, 0, 1, 0, 1, 1};
        int shiftAmount = 1;
        int[] expectedOutput = {1, 1, 0, 1, 0, 1, 1, 0};
        int[] result = shiftLeft(array, shiftAmount);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testLeftShift2() {
        int[] array = {0, 1, 1, 0, 1, 0, 1, 1};
        int shiftAmount = 2;
        int[] expectedOutput = {1, 0, 1, 0, 1, 1, 0, 0};
        int[] result = shiftLeft(array, shiftAmount);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testRightShift1() {
        int[] array = {0, 1, 1, 0, 1, 0, 1, 1};
        int shiftAmount = 1;
        int[] expectedOutput = {0, 0, 1, 1, 0, 1, 0, 1};
        int[] result = shiftRight(array, shiftAmount);
        assertArrayEquals(expectedOutput, result);
    }

    @Test
    public void testRightShift2() {
        int[] array = {0, 1, 1, 0, 1, 0, 1, 1};
        int shiftAmount = 2;
        int[] expectedOutput = {0, 0, 0, 1, 1, 0, 1, 0};
        int[] result = shiftRight(array, shiftAmount);
        assertArrayEquals(expectedOutput, result);
    }

}