package java8.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectorSample {

	private static IntStream intStream = IntStream.rangeClosed(1, 100);
	
	public static void main(String[] args) {
//		collectSample();
		reduceSample();
	}

	private static void collectSample() {
		Predicate<Integer> predicate = t -> t % 2 == 0;
		List<Integer> list = intStream.parallel().collect(() -> new ArrayList<Integer>(),
		(array, s) -> {
			if (predicate.test(s)) 
				array.add(s); 
		},
		(array1, array2) -> array1.addAll(array2));
	}
	
	private static void reduceSample() {
		Employee sb = new Employee("sb");
		System.out.println(
			Arrays.asList(
				new Meeting.Builder("angular")
					.time(LocalDate.now())
					.attendee(new Employee("sb"))
					.build(),
				new Meeting.Builder("estimation")
					.time(LocalDate.of(2019, 8, 15))
					.attendee(new Employee("cj"))
					.build())
			.parallelStream().reduce(new ArrayList<>(), 
				(r, t) -> {
					t.attendee.stream().reduce((a, b) -> {
						System.out.println(a);
						a.setLastMeetingTime(t.meetingTime);
						r.add(a);
						return a;
					});
					return r;
				},
				(r1, r2) -> {
	//				r1.addAll(r2); 
					return r1; 
				}));
	}
	
}

class Employee {
	
	public Employee(String name) {
		this.name = name;
	}
	
	String name;
	LocalDate lastMeetingTime;
	
	public void setLastMeetingTime(LocalDate time) {
		this.lastMeetingTime = time;
	}
	
	@Override
	public String toString() {
		return name + "=" + lastMeetingTime;
	}
}

class Meeting {
	String subject;
	LocalDate meetingTime;
	List<Employee> attendee;
	
	public Meeting(String s, LocalDate d, List<Employee> a) {
		this.subject = s;
		this.meetingTime = d;
		this.attendee = a;
	}
	
	public static class Builder {
		
		private final String s;
		private LocalDate t;
		private final List<Employee> e = new ArrayList<>();
		
		public Builder(String s) {
			this.s = s;
		}
		
		public Builder time(LocalDate t) {
			this.t = t;
			return this;
		}
		
		public Builder attendee(Employee e) {
			this.e.add(e);
			return this;
		}
		
		public Meeting build() {
			return new Meeting(s, t, e);
		}
	}
	
}