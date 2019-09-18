package java8.functionalInterface;

import java.util.Arrays;
import java.util.function.Function;

public class FunctionSample {

	public static void main(String[] args) {

		Function<Integer, String> converter = (i) -> Integer.toString(i);
		Function<String, Integer> reverse = (s) -> Integer.parseInt(s);
		test(converter, 5);
		System.out.println(test((a) -> a * 10, 5));
		System.out.println(decoratorExam(5, (a) -> a + 4, (a) -> a * 100));
	}

	static <T, R> R test(Function<T, R> f, T s) {
		return f.apply(s);
	}

	public static int decoratorExam(int value, Function<Integer, Integer>... decorators) {
		return Arrays.asList(decorators).stream().reduce((current, next) -> current.andThen(next))
				.orElseGet(Function::identity).apply(value);
	}
}
