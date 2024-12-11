package ua.holovchenko;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger factorial = recursiveFactorial(BigInteger.valueOf(100));
        System.out.println(recursiveSum(factorial));
    }

    private static BigInteger recursiveFactorial(BigInteger num) {
        if (num.equals(BigInteger.ONE) || num.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return num.multiply(recursiveFactorial(num.subtract(BigInteger.ONE)));
    }

    private static BigInteger recursiveSum(BigInteger num) {
        if (num.compareTo(BigInteger.TEN) < 0) {
            return num;
        }
        BigInteger[] divAndRemainder = num.divideAndRemainder(BigInteger.TEN);
        return divAndRemainder[1].add(recursiveSum(divAndRemainder[0]));
    }
}
