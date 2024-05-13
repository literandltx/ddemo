package com.literandltx;

public class Operations {
    protected static int[] and(int[] x, int[] y) {
        validate(x, y);
        int[] c = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            c[i] = x[i] & y[i];
        }

        return c;
    }

    protected static int[] or(int[] x, int[] y) {
        validate(x, y);
        int[] c = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            c[i] = x[i] | y[i];
        }

        return c;
    }

    protected static int[] xor(int[] x, int[] y) {
        validate(x, y);
        int[] c = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            c[i] = x[i] ^ y[i];
        }

        return c;
    }

    protected static int[] negate(int[] x) {
        validate(x);
        int[] c = new int[x.length];

        for (int i = 0; i < x.length; i++) {
            c[i] = x[i] == 0 ? 1 : 0;
        }

        return c;
    }

    // >>
    protected static int[] shiftRight(int[] binaryArray, int r) {
        int[] shiftedArray = new int[binaryArray.length];

        for (int i = 0; i < binaryArray.length; i++) {
            int newIndex = i + r;
            if (newIndex < binaryArray.length) {
                shiftedArray[newIndex] = binaryArray[i];
            }
        }

        return shiftedArray;
    }

    // <<
    protected static int[] shiftLeft(int[] binaryArray, int r) {
        int[] shiftedArray = new int[binaryArray.length];

        for (int i = 0; i < binaryArray.length; i++) {
            int newIndex = i - r;
            if (newIndex >= 0) {
                shiftedArray[newIndex] = binaryArray[i];
            }
        }

        return shiftedArray;
    }

    protected static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();

        for (int i : array) {
            sb.append(i);
        }

        return sb.toString();
    }

    protected static int[] fromBinaryStringToArray(String input) {
        int[] array = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '0' || c == '1') {
                array[i] = Character.getNumericValue(c);
            } else {
                throw new IllegalArgumentException("Input string must contain only '0' and '1'");
            }
        }

        return array;
    }

    protected static int[] fromShortToArray(short input) {
        int[] array = new int[Short.SIZE];

        for (int i = 0; i < Short.SIZE; i++) {
            array[Short.SIZE - 1 - i] = (input >> i) & 1;
        }

        return array;
    }

    protected static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    protected static int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];
        int end = array.length - 1;

        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[end - i];
        }

        return reversedArray;
    }

    private static void validate(int[] x) {
        for (int i : x) {
            if (i != 0 && i != 1) {
                throw new RuntimeException("array must contain 0 and 1 only");
            }
        }
    }

    private static void validate(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("array must have same length");
        }

        for (int i : x) {
            if (i != 0 && i != 1) {
                throw new RuntimeException("array must contain 0 and 1 only");
            }
        }

        for (int i : y) {
            if (i != 0 && i != 1) {
                throw new RuntimeException("array must contain 0 and 1 only");
            }
        }
    }
}
