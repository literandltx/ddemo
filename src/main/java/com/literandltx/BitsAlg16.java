package com.literandltx;

import java.util.Arrays;

public class BitsAlg16 {
    static int N = 16;

    public static void main(String[] args) {
        short a = 0x5254;
        short b = 0x1A45;

        String number1 = "0101001001010100";
        String number2 = "0001101001000101";

//        short c = c(a, b); // 0001001001000100
//        System.out.println(c);
//        System.out.println(shortToString(c));
//        System.out.println("0001001001000100");

        alg3(a, b);
        System.out.println();
    }

    // or - |
    // and - &
    // xor - ^

    private static String shortToString(short input) {
        return Arrays.stream(fromShortToBinaryArray(input))
                .mapToObj(String::valueOf)
                .reduce("", (s1, s2) -> s1 + s2);
    }

    private static short c(short _a, short _b) {
        int[] x = fromShortToBinaryArray(_a);
        int[] y = fromShortToBinaryArray(_b);

        int[] t1 = negate(xor(x, y));
        int[] t2 = rotr(t1, 1);
        int[] t3 = xor(x, rotr(x, 1));

        return aop(toShort(and(t1, and(t2, t3))));
    }

    private static short aop(short _x) {
        int log2 = (int) log2(N);
        short[] x = new short[log2];
        short[] y = new short[log2];

        // 1.
        x[0] = toShort(and(fromShortToBinaryArray(_x), rotr(fromShortToBinaryArray(_x), 1)));

        // 2.
        for (int i = 1; i < log2 - 1; i++) {
            int[] tmp = fromShortToBinaryArray(x[i - 1]);
            x[i] = toShort(and(tmp, rotr(tmp, (int) Math.pow(2, i - 1))));
        }

        // 3.
        y[0] = toShort(and(fromShortToBinaryArray(_x), negate(fromShortToBinaryArray(x[0]))));

        // 4.
        for (int i = 1; i < log2; i++) {
            int[] tmpX = fromShortToBinaryArray(x[i - 1]);
            int[] tmpY = fromShortToBinaryArray(y[i - 1]);

            int[] rotr = rotr(tmpY, (int) Math.pow(2, i - 1));
            y[i] = toShort(or(tmpY, and(rotr, tmpX)));
        }

        System.out.println(Arrays.toString(y));
        return y[log2 - 1];
    }

    private static short toShort(int[] binaryArray) {
        short result = 0;
        for (int j : binaryArray) {
            result = (short) (result << 1 | j);
        }
        return result;
    }

    static void alg3(short a, short b) {
        int[] alpha = fromShortToBinaryArray(a);
        int[] beta = fromShortToBinaryArray(b);
        int[] gamma = new int[16];

        int[] p = new int[]{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0}; // mock c

        gamma[0] = alpha[0] ^ beta[0];
        for (int i = 1; i < 16; i++) {
            if (alpha[i - 1] == beta[i - 1] && beta[i - 1] == gamma[i - 1]) {
                gamma[i] = alpha[i] ^ beta[i] ^ alpha[i-1];
            } else if (i == 15 || alpha[i] != beta[i] || p[i] == 1) {
                gamma[i] = 1; // gamma[i] = {0, 1} // ??
            } else {
                gamma[i] = alpha[i];
            }
        }

        System.out.println(Arrays.toString(gamma));
        System.out.println(Arrays.toString(fromShortToBinaryArray((short) 28689)));
        System.out.println(toShort(gamma));
    }

    static void alg4() {

    }

    static int[] xor(int[] a, int[] b) {
        int[] res = new int[16];

        for (int i = 0; i < res.length; i++) {
            res[i] = a[i] ^ b[i];
        }

        return res;
    }

    static int[] and(int[] a, int[] b) {
        int[] res = new int[16];

        for (int i = 0; i < res.length; i++) {
            res[i] = a[i] & b[i];
        }

        return res;
    }

    static int[] or(int[] a, int[] b) {
        int[] res = new int[16];

        for (int i = 0; i < res.length; i++) {
            res[i] = a[i] | b[i];
        }

        return res;
    }

    static int[] negate(int[] a) {
        int[] res = new int[16];

        for (int i = 0; i < res.length; i++) {
            res[i] = a[i] == 1 ? 0 : 1;
        }

        return res;
    }

    // ">>" right shift
    private static int[] rotr(int[] binaryArray, int shiftAmount) {
        int[] shiftedArray = new int[binaryArray.length];

        for (int i = 0; i < binaryArray.length; i++) {
            int newIndex = i + shiftAmount;
            if (newIndex < binaryArray.length) {
                shiftedArray[newIndex] = binaryArray[i];
            }
        }

        return shiftedArray;
    }

    // "<<"
    private static int[] rotl(int[] binaryArray, int shiftAmount) {
        int[] shiftedArray = new int[binaryArray.length];

        for (int i = 0; i < binaryArray.length; i++) {
            int newIndex = i - shiftAmount;
            if (newIndex >= 0) {
                shiftedArray[newIndex] = binaryArray[i];
            }
        }

        return shiftedArray;
    }

    private static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    private static int[] fromShortToBinaryArray(short input) {
        String binaryString = Integer.toBinaryString(input & 0xffff);
        binaryString = String.format("%16s", binaryString).replace(' ', '0');

        int[] binaryArray = new int[16];
        for (int i = 0; i < binaryString.length(); i++) {
            binaryArray[i] = binaryString.charAt(i) - '0';
        }

        return binaryArray;
    }

}
