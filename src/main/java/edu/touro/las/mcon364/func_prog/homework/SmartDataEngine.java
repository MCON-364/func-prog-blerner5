package edu.touro.las.mcon364.func_prog.homework;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.*;

/**
 * ============================================================
 * Smart Data Processing Engine
 * ============================================================
 * <p>
 * Goal:
 * - Build a reusable functional pipeline engine
 * - Use Consumer, Supplier, Predicate, Function
 * - Use Optional
 * - Use chaining
 * - Use switch expression
 * <p>
 * Estimated time: ~2 hours
 * <p>
 * Rules:
 * - Use lambdas (no anonymous classes)
 * - Use Optional where requested
 * - Use functional interface chaining where appropriate
 */
public class SmartDataEngine {

    // ============================================================
    // PART 1 — GENERIC PIPELINE ENGINE
    // ============================================================

    /**
     * TODO:
     * Implement a generic pipeline.
     * <p>
     * Behavior:
     * 1. Filter using Predicate
     * 2. Transform using Function
     * 3. Pass result to Consumer
     */
    public static <T, R> void pipeline(
            List<T> input,
            Predicate<T> filter,
            Function<T, R> mapper,
            Consumer<R> consumer
    ) {
        input.stream().filter(filter).map(mapper).forEach(consumer);
    }

    // ============================================================
    // PART 2 — OPTIONAL PROCESSING
    // ============================================================

    /**
     * TODO:
     * Implement a safe divide method.
     * <p>
     * - If denominator is 0 → return Optional.empty()
     * - Otherwise return Optional.of(result)
     */
    public static Optional<Double> safeDivide(double a, double b) {
        // TODO
        if(b == 0.0) {
            return Optional.empty();
        }

       return Optional.of(a/b);
    }

    /**
     * TODO:
     * Use Optional chaining:
     * <p>
     * - Divide two numbers using safeDivide(...)
     * - If a value is present, multiply the result by 10
     * - If empty, return -1.0
     * <p>
     * Reminder:
     * - Optional.map(...) transforms the value ONLY if it is present.
     * - If the Optional is empty, map() does nothing and the empty
     * Optional continues down the chain.
     * - Use orElse(...) to provide a default value when empty.
     */
    public static double processDivision(double a, double b) {
        // TODO
        return safeDivide(a, b).map(x -> x * 10).orElse(-1.0);
    }

    // ============================================================
    // PART 3 — DATA TYPE ROUTER (Pattern Matching Switch)
    // ============================================================

    /**
     * TODO:
     * Use switch expression with pattern matching.
     * <p>
     * Behavior:
     * - If Integer → return square
     * - If String → return uppercase
     * - If Double → return rounded value
     * - Otherwise → return "Unsupported"
     * <p>
     * Must use switch expression (arrow syntax).
     */
    public static Object transformObject(Object input) {

        // Example structure (not solution):

        // return switch (input) {
        //     case Integer i -> ...
        //     case String s  -> ...
        //     case Double d  -> ...
        //     default -> ...
        // };

        return switch (input) {
            case Integer i -> i*i;
            case String s -> s.toUpperCase();
            case Double d -> Math.round(d);
            default -> "Unsupported";
        };
    }

    // ============================================================
    // PART 4 — FUNCTION CHAINING
    // ============================================================

    /**
     * TODO:
     * Create and return a Function<String, Integer>
     * that performs the following transformations in order:
     * <p>
     * 1. Trim leading and trailing whitespace
     * 2. Convert the string to lowercase
     * 3. Return the length of the final string
     * <p>
     * Guidance:
     * - You should NOT write one large lambda block.
     * - Instead, create smaller Function variables
     * and combine them using function chaining.
     * <p>
     * - Use:
     * andThen(...)  → left function runs first
     * compose(...)  → right function runs first
     * <p>
     * - Think carefully about types:
     * trim:        String → String
     * toLowerCase: String → String
     * length:      String → Integer
     * <p>
     * - The final returned function must be:
     * Function<String, Integer>
     * <p>
     * This exercise reinforces how data flows through a functional pipeline.
     */

    public static Function<String, Integer> buildStringLengthPipeline() {
        // TODO
        Function <String, String> trim = s -> s.trim();
        Function <String, String> lower =  s -> s.toLowerCase();
        Function <String, Integer> stringLengthFunction = s -> s.length();

        return trim.andThen(lower.andThen(trim)).andThen(stringLengthFunction);
    }

    // ============================================================
    // PART 5 — MINI APPLICATION
    // ============================================================

    /**
     * TODO:
     * Implement this method using ALL four functional interfaces:
     * <p>
     * - Supplier  → generate random integers
     * - Predicate → filter numbers > 50
     * - Function  → convert Integer → "Score: X"
     * - Consumer  → print the final result
     * <p>
     * Required Behavior:
     * 1. Generate 10 random integers between 1 and 100.
     * (Call supplier.get() multiple times.)
     * 2. Keep only numbers greater than 50.
     * 3. Convert each remaining number into a formatted string.
     * 4. Print each formatted string.
     * <p>
     * Important Guidance:
     * - Do NOT use Streams.
     * - Do NOT hardcode logic directly inside the loop.
     * - First define the functional interfaces.
     * - Then generate a List<Integer>.
     * - Then pass everything into your pipeline() method.
     * <p>
     * Think in terms of behavior injection:
     * - The pipeline should NOT know how random numbers are created.
     * - The pipeline should NOT know how formatting works.
     * <p>
     * It should only orchestrate the behavior passed to it.
     * <p>
     * This method integrates everything you learned in this project.
     */

    public static void runScoreProcessor() {
        // TODO
        Supplier<Integer> supply = () -> new Random().nextInt(100) + 1;
        Predicate<Integer> filter = x -> x > 50;
        Function<Integer, String> map = x -> "Score: " + x;
        Consumer<String> print = s -> System.out.println(s);
    }

}
