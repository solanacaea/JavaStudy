package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReduceSample {

	private static IntStream intStream = IntStream.range(1, 100);
	
	public static void main(String[] args) {
//		System.out.println(intStream.reduce((v1, v2) -> v1 + v2).getAsInt());
//		System.out.println(intStream.summaryStatistics().getSum());
//		System.out.println(intStream.reduce(0, (v1, v2) -> v1 + v2));
		
//		reduceSample();
//		reduceSample2();
//		intStream.parallel().forEach(a -> reduceSample2());
//		combinerSample();
//		reduceSample3();
		reduceSample4();
	}

	private static void reduceSample() {
		Optional accResult = Stream.of(1, 2, 3, 4)
		        .reduce((acc, item) -> { //why does it accept 2 parameters?
		            System.out.println("acc : "  + acc);
		            acc += item;
		            System.out.println("item: " + item);
		            System.out.println("acc+ : "  + acc);
		            System.out.println("--------");
		            return acc;
		        });
		System.out.println("accResult: " + accResult.get());
		System.out.println("--------");
	}
	
	private static void reduceSample2() {
		ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)//.parallel()
		        .reduce(new ArrayList<Integer>(), //Collections
		                new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
		                    @Override
		                    public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

		                        acc.add(item);
		                        System.out.println("item: " + item);
		                        System.out.println("acc+ : " + acc);
		                        System.out.println("BiFunction");
		                        System.out.println("=========");
		                        return acc;
		                    }
		                }, new BinaryOperator<ArrayList<Integer>>() {
		                    @Override
		                    public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
		                        System.out.println("BinaryOperator");
		                        acc.addAll(item);
		                        System.out.println("item: " + item);
		                        System.out.println("acc+ : " + acc);
		                        System.out.println("--------");
		                        return acc;
		                    }
		                });
		System.out.println("accResult_: " + accResult_);
	}

	private static void combinerSample() {
		System.out.println(intStream.boxed()
	            .parallel()
	            .reduce(new ImmutableAverager(), 
	                    ImmutableAverager::accept,
	                    ImmutableAverager::combine)
	            .average());
	}

	static class ImmutableAverager {
	    private final int total;
	    private final int count;
	
	    public ImmutableAverager() {
	        this.total = 0;
	        this.count = 0;
	    }
	
	    public ImmutableAverager(int total, int count) {
	        this.total = total;
	        this.count = count;
	    }
	
	    public double average() {
	        return count > 0 ? ((double) total) / count : 0;
	    }
	
	    public ImmutableAverager accept(int i) {
	        return new ImmutableAverager(total + i, count + 1);
	    }
	
	    public ImmutableAverager combine(ImmutableAverager other) {
	        return new ImmutableAverager(total + other.total, count + other.count);
	    }
	}

	public static void reduceSample3() {
		System.out.println(IntStream.range(1, 100).boxed().parallel().reduce(0, (x, y) -> (x + y), (x, y) -> x * y));
	}
	
	public static void reduceSample4() {
		List<Score> developers = Arrays.asList(
	            new Score("sb", "ipc", "server", 100),
	            new Score("cj", "linux", "server", 99),
	            new Score("yy", "ipc", "client", 88),
	            new Score("aa", "apim", "client", 77),
	            new Score("aa", "atom", "client", 66),
	            new Score("bb", "jira", "client", 66),
	            new Score("cc", "atom", "client", 30),
	            new Score("dd", "atom", "client", 40)
	        );
		
		developers.parallelStream()
			.collect(Collectors.groupingBy(Score::getType, Collectors.toList()))
			.forEach((type, list) -> {
				Dealtrax dt = new Dealtrax();
				dt = list.stream().parallel().reduce(dt, (u, t) -> u.sum(t), (u, t) -> u);
				System.out.println(dt);
			});
		
	}
}

class Score {
	String name;
	String category;
	String type;
	int point;
	public Score(String n, String c, String t, int p) {
		this.name = n;
		this.category = c;
		this.type = t;
		this.point = p;
	}
	
	public String getType() {
		return type;
	}
}

class Dealtrax {
	String type;
	int count = 0;
	int total = 0;
	List<Developer> devs;
	
	public Dealtrax() {
		devs = new ArrayList<>();
	}
	
	public Dealtrax sum(Score dev) {
		if (this.type == null)
			this.type = dev.type;
		this.count++;
		this.total += dev.point;
		devs.add(new Developer(dev.name, dev.point));
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("type=").append(type).append(System.lineSeparator())
			.append("count=").append(count).append(System.lineSeparator())
			.append("total=").append(total).append(System.lineSeparator())
			.append("devs=").append(System.lineSeparator());
		devs.forEach(d -> {
			sb.append("\t").append("name=").append(d.name).append(System.lineSeparator())
			.append("\t").append("value=").append(d.value).append(System.lineSeparator());
		});
		return sb.toString();
	}
}

class Developer {
	String name;
	int value;
	public Developer(String n, int v) {
		this.name = n;
		this.value = v;
	}
}


