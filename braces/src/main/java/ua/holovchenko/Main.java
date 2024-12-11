package ua.holovchenko;


import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger input = scanner.nextBigInteger();
        BigInteger numerator = recursiveFactorial(input.multiply(BigInteger.TWO));
        BigInteger denominator = recursiveFactorial(input.add(BigInteger.ONE)).multiply(recursiveFactorial(input));
        BigInteger catalani = numerator.divide(denominator);
        System.out.println(catalani);
    }

    private static BigInteger recursiveFactorial(BigInteger num) {
        if (num.equals(BigInteger.ONE) || num.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return num.multiply(recursiveFactorial(num.subtract(BigInteger.ONE)));
    }
}