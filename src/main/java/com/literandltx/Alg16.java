package com.literandltx;

import java.util.Arrays;

public class Alg16 extends Operations {

    public static void main(String[] args) {
        short a = 0x5254; // 0101001001010100
        short b = 0x1A45; // 0001101001000101

        // повино повертати (в статті 12ст):
        // с()    = 0x1244 -- 4676 -- 0001001001000100
        // alg3() = 0x7011 -- -- 0111000000010001

//        String c = c(a, b); // 0x1366 -- 4966 -- 0001001101100110
//
//        String alg3 = alg3(a, b); // (0100100000010001) число замале
//        System.out.println(alg3);

        String alg3 = alg3(a, b); // 0100100100110011
        String alg4 = alg4(a, b); // 0100100000010001
                                  // 0111000000010001 повинно

        System.out.println(alg3);
        System.out.println(alg4);
        System.out.println(alg3.equals(alg4)); // true... коли alg3 2п замоканий
    }

    static String alg4(short _a, short _b) {
        int[] alpha = fromShortToArray(_a);
        int[] beta = fromShortToArray(_b);
        int[] one = fromBinaryStringToArray("1111111111111111");

        int[] r = and(alpha, one);
        int[] e = and(negate(xor(alpha, beta)), negate(r));
        int[] a = and(and(e, shiftLeft(e, 1)), xor(alpha, shiftLeft(alpha, 1)));
        int[] p = aop(reverseArray(a)); // ?...  ?should mock?
        a = and(or(a, shiftRight(a, 1)), negate(r));
        int[] b = shiftLeft(or(a, e), 1);

        int[] t1 = and(a, xor(alpha, p));
        int[] t2 = and(and(xor(alpha, xor(beta, shiftLeft(alpha, 1))), negate(a)), b);
        int[] t3 = and(alpha, and(negate(a), negate(b)));

        int[] y = or(t1, or(t2, t3));
        y = or(and(y, negate(one)), and(xor(alpha, beta), one));

        return arrayToString(y);
    }

    static String c(short _a, short _b) {
        int[] x = fromShortToArray(_a);
        int[] y = fromShortToArray(_b);

        int[] t1 = negate(xor(x, y));
        int[] t2 = shiftRight(t1, 1);
        int[] t3 = xor(x, shiftRight(x, 1));

        int[] t4 = and(t1, t2);
        int[] t5 = and(t4, t3);

        int[] t6 = aop(t5);

        return arrayToString(t6);
    }

    static int[] aop(int[] _x) {
        int log2 = (int) log2(16);

        String[] x = new String[log2];
        String[] y = new String[log2];

        // 1.
        x[0] = arrayToString(and(_x, shiftRight(_x, 1)));

        // 2.
        for (int i = 1; i < log2 - 1; i++) {
            var tmpX = fromBinaryStringToArray(x[i-1]);

            int[] res = and(tmpX, shiftRight(tmpX, (int) Math.pow(2, i-1)));

            x[i] = arrayToString(res);
        }

        // 3.
        var tmp = fromBinaryStringToArray(x[0]);
        y[0] = arrayToString(and(_x, negate(tmp)));

        // 4.
        for (int i = 1; i < log2; i++) {
            int[] tmpX = fromBinaryStringToArray(x[i-1]);
            int[] tmpY = fromBinaryStringToArray(y[i-1]);

            int[] res = or(tmpY, and(shiftRight(tmpY, (int) Math.pow(2, i-1)), tmpX));

            y[i] = arrayToString(res);
        }

        System.out.println(Arrays.toString(y)); // по першому(y[0]) індексу число співдападає, а чому...

        // 5.
        return fromBinaryStringToArray(y[log2 - 1]);
    }

    static String alg3(short _a, short _b) {
        int[] a = fromShortToArray(_a);
        int[] b = fromShortToArray(_b);
        int[] y = new int[a.length];

        // 1.
        y[0] = a[0] ^ b[0];

        // 2. mock result: 0x1244 -> 0b0001001001000100 -> 4676
//        short t = 4676;
//        int[] p = fromShortToArray(t);
        int[] p = fromBinaryStringToArray(c(_a, _b));

        // 3.
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] == b[i-1] && b[i-1] == y[i-1]) {
                y[i] = a[i] ^ b[i] ^ a[i-1];
            } else if (i == a.length - 1 || a[i] != b[i] || p[i] == 1) {
                y[i] = 1; // y_i <- {0, 1} ??
            } else {
                y[i] = a[i];
            }
        }

        return arrayToString(y);
    }


}
