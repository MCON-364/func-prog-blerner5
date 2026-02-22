package edu.touro.las.mcon364.func_prog.exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

/**
 * Functional Interface Practice
 *
 * In this assignment you will:
 *  - Create and return different functional interfaces
 *  - Apply them
 *  - Practice chaining where appropriate
 *
 * IMPORTANT:
 *  - Use lambdas
 *  - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================


    /**
     * 1) Create a Supplier that returns the current year.
     * <p>
     * Hint:
     * You can get the current date using:
     * LocalDate.now()
     * <p>
     * Then extract the year using:
     * getYear()
     * <p>
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {
        Supplier<Integer> year = () -> LocalDate.now().getYear();
        return year;
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {
        return () -> new Random().nextInt(100) + 1;
    }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {
        return str -> str.equals(str.toUpperCase());
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     * <p>
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {
        Predicate<Integer> divisibleByFive = x -> x % 5 == 0;
        Predicate<Integer> positive = x -> x > 0;
        Predicate<Integer> positiveAndDivisibleByFive = divisibleByFive.and(positive);
        return positiveAndDivisibleByFive;
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     * <p>
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {
        return conversion -> conversion * 9 / 5 + 32;
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     * <p>
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels() {
        return str -> {
            int sum = 0;
            String strLowerCase = str.toLowerCase();
            for (int ctr = 0; ctr < strLowerCase.length(); ctr++) {
                if (strLowerCase.charAt(ctr) == 'a' || strLowerCase.charAt(ctr) == 'e' || strLowerCase.charAt(ctr) == 'i' || strLowerCase.charAt(ctr) == 'o' || strLowerCase.charAt(ctr) == 'u') {
                    sum++;
                }
            }

            return sum;
        };
    }

    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     * <p>
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter() {
        Consumer<String> printStr = str -> System.out.println("*** " + str + " ***");
        return printStr;
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare() {
        return square -> System.out.println(square * square);
    }

    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     * - A Predicate
     * - A Function
     * - A Consumer
     * <p>
     * Process the list as follows:
     * - Keep only strings longer than 3 characters
     * - Convert them to lowercase
     * - Print them
     */
    public static void processStrings(List<String> values) {
        Predicate<String> longerThanThree = str -> str.length() > 3;
        Function<String, String> converter = str -> str.toLowerCase();
        Consumer<String> print = str -> System.out.println(str);

        for (int ctr = 0; ctr < values.size(); ctr++) {
            if (longerThanThree.test(values.get(ctr))) {
                String lowerCase = converter.apply(values.get(ctr));
                print.accept(lowerCase);
            }
        }

    }

    /**
     * 10) Apply:
     * - A Supplier
     * - A Predicate
     * - A Consumer
     * <p>
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
        Supplier<Integer> generatedScore = () -> new Random().nextInt(100) + 1;
        Predicate<Integer> above = score -> score > 70;
        Consumer<Integer> printer = score -> System.out.println(score);

        for (int ctr = 0; ctr < 5; ctr++) {
            int score = generatedScore.get();
            if (above.test(score)) {
                printer.accept(score);
            }
        }
    }

}


