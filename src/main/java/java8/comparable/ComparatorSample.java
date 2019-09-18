package java8.comparable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorSample {
	private int a;
	private int b;

	public ComparatorSample(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public ComparatorSample setA(int a) {
		this.a = a;
		return this;
	}

	public int getB() {
		return b;
	}

	public ComparatorSample setB(int b) {
		this.b = b;
		return this;
	}

	@Override
	public String toString() {
		return "\n" + a + " : " + b;
	}

	public static void main(String[] args) {
		List<ComparatorSample> list = new ArrayList() {{
			add(new ComparatorSample(1, 1));
			add(new ComparatorSample(1, 2));
			add(new ComparatorSample(2, 3));
			add(null);
			add(new ComparatorSample(2, 1));
			add(new ComparatorSample(3, 4));
			add(new ComparatorSample(3, 1));
		}};

		list.sort(Comparator.nullsFirst(Comparator
				.comparing(ComparatorSample::getB)
				.reversed()
				.thenComparing(Comparator
						.comparing(ComparatorSample::getA)
						.reversed())));
		System.out.println(list);
	}
}
