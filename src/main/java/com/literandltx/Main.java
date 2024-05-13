package com.literandltx;

import java.util.function.IntBinaryOperator;

public class Main {
    private static final int r1 = 16;
    private static final int r2 = 12;
    private static final int r3 = 18;
    private static final int r4 = 7;
    private static final int s = 32;
    private static final int n = 32;

    public static void main(String[] args) {
         int max = 0b11111111111111111111111111111111;
        int top = 0b11111111111111110000000000000000;
        int low = 0b00000000000000001111111111111111;
        int re1 = 0b01010101010101010101010101010101;
        int re2 = 0b10101010101010101010101010101010;
        int minPlus4 = 0b00000000000000000000000000001111;
        int minPlus3 = 0b00000000000000000000000000000111;
        int minPlus2 = 0b00000000000000000000000000000011;
        int minPlus1 = 0b00000000000000000000000000000001;
        int min = 0b00000000000000000000000000000000;

        short a = 0x5254;
        short b = 0x1A45;
        int c = c16(a, b);
        System.out.println(Integer.toHexString(c));
    }

    public static int reverseBits(int n) {
        int reversed = 0;
        for (int i = 0; i < 32; i++) {
            reversed = (reversed << 1) | (n & 1);
            n >>= 1;
        }
        return reversed;
    }

    public static int[] findAllDifferences(int x, int y) {
        int n = 32;
        int[] gamma = new int[n];
        gamma[0] = x ^ y;

        for (int i = 1; i < n; i++) {
            if ((x & (1 << (i - 1))) != 0 && (y & (1 << (i - 1))) == gamma[i - 1]) {
                gamma[i] = x ^ y ^ (x & (1 << (i - 1)));
            } else if (i == n - 1 || x != y) {
                gamma[i] = (x == 0 && y == 0) ? 0 : 1;
            } else {
                gamma[i] = x;
            }
        }

        return gamma;
    }

    private static int alg4(int alfa, int beta) {
        int r = alfa & 1; // 1
        int e = ~(alfa ^ beta) & ~r; // 2
        int a = e & (e << 1) & (alfa ^ (alfa << 1)); // 3
        int p = aop32(reverseBits(a), 32); // 4
        a = (a | (a >> 1)) & ~r; // 5
        int b = (a | e) << 1; // 6
        int gamma = ((alfa ^ p) & a) | (alfa ^ beta ^ (alfa << 1) & ~a & b) | (alfa & ~a & ~b); // 7
        gamma = (gamma & ~0) | ((alfa ^ beta) & 1); // 8

        return gamma;
    }

    private static int c(int x, int y) {
        int t1 = ~(x ^ y);
        int t2 = t1 >> 1;
        int t3 = x ^ (x >> 1);

        return aop32(t1 & t2 & t3, 16);
    }

    private static short c16(short x, short y) {
        short t1 = (short)~(x ^ y);
        short t2 = (short)(t1 >> 1);
        short t3 = (short)(x ^ (x >> 1));

        return aop16((short)(t1 & t2 & t3), 16);
    }


    public static int aop32(int x, int n) {
        if ((n & (n - 1)) != 0) throw new IllegalArgumentException("n must be a power of 2");

        double log = Math.log(n) / Math.log(2);
        int[] xArray = new int[n];
        xArray[0] = x & ((x >> 1) ^ Integer.MAX_VALUE);

        for (int i = n - 2; i >= log; i--) {
            xArray[i] = (xArray[i + 1] ^ ((xArray[i + 1] >> (int) Math.pow(2, n - i - 2)) ^ Integer.MAX_VALUE)) &
                    (x >> (int) Math.pow(2, n - i - 2));
        }

        int[] yArray = new int[n];
        yArray[0] = x ^ xArray[0];

        for (int i = n - 2; i >= log; i--) {
            yArray[i] = yArray[i + 1] |
                    (((yArray[i + 1] >> (int) Math.pow(2, n - i - 2)) ^ Integer.MAX_VALUE) &
                            x >> (int) Math.pow(2, n - i - 3));
        }

        return yArray[(int) (log)];
    }

    public static short aop16(short x, int n) {
        if ((n & (n - 1)) != 0) throw new IllegalArgumentException("n must be a power of 2");

        double log = Math.log(n) / Math.log(2);
        short[] xArray = new short[n];
        xArray[0] = (short)(x & ((x >> 1) ^ Short.MAX_VALUE));

        for (int i = n - 2; i >= log; i--) {
            xArray[i] = (short)((xArray[i + 1] ^ ((xArray[i + 1] >> (int)Math.pow(2, n - i - 2)) ^ Short.MAX_VALUE)) &
                    (x >> (int)Math.pow(2, n - i - 2)));
        }

        short[] yArray = new short[n];
        yArray[0] = (short)(x ^ xArray[0]);

        for (int i = n - 2; i >= log; i--) {
            yArray[i] = (short)(yArray[i + 1] |
                    (((yArray[i + 1] >> (int)Math.pow(2, n - i - 2)) ^ Short.MAX_VALUE) &
                            x >> (int)Math.pow(2, n - i - 3)));
        }

        return yArray[(int)(log)];
    }


    public static void showResult(int x0, int x1, int x2, int x3) {
        int[] h256 = h256(x0, x1, x2, x3);
//        h256 = h256(h256[0], h256[1], h256[2], h256[3]);

        System.out.println(printBinary(x0) + "." + printBinary(x1) + "." + printBinary(x2) + "." + printBinary(x3));
        System.out.println(printBinary(h256[0]) + "." + printBinary(h256[1]) + "." + printBinary(h256[2]) + "." + printBinary(h256[3]));
        System.out.println();
    }

    public static String printBinary(int number) {
        return String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0');
    }

    static IntBinaryOperator xor = (w1, w2) -> w1 ^ w2;
    static IntBinaryOperator add = Integer::sum;
    static IntBinaryOperator rotl = (w, shift) -> (w << shift) | (w >>> (32 - shift));

    private static int[] h256(int x0, int x1, int x2, int x3) {
        x0 = add.applyAsInt(x0, x1);
        x3 = xor.applyAsInt(x0, x3);
        x3 = rotl.applyAsInt(x3, r1);
        x2 = add.applyAsInt(x2, x3);
        x1 = xor.applyAsInt(x1, x2);
        x1 = rotl.applyAsInt(x1, r2);

        x0 = add.applyAsInt(x0, x1);
        x3 = xor.applyAsInt(x0, x3);
        x3 = rotl.applyAsInt(x3, r3);
        x2 = add.applyAsInt(x2, x3);
        x1 = xor.applyAsInt(x1, x2);
        x1 = rotl.applyAsInt(x1, r4);

        return new int[] {x0, x1, x2, x3};
    }
}