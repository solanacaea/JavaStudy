package java8.functionalInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerSample {

	public static void main(String[] args) {
//        testConsumer();
//        testAndThen();
//		testSupplier();
		testSupplier3();//Collectors.toList()
    }

    public static void testConsumer() {
        Consumer<Integer> square = x -> System.out.println("print square : " + x * x);
        square.accept(2);
    }

    public static void testAndThen() {
        Consumer<Integer> consumer1 = x -> System.out.println("first x : " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x : " + x);
//            throw new NullPointerException("throw exception test");
        };
        Consumer<Integer> consumer3 = x -> System.out.println("third x : " + x);

        consumer1.andThen(consumer2).andThen(consumer3).accept(1);
    }
    
    public static void testSupplier() {
    	Supplier<Test> s = Test::new;
    	
    	Test t1 = s.get();
    	Test t2 = s.get();
    	System.out.println(t1 == t2);
    }

    public static void testSupplier2() {
    	driveVehicle(()-> new Bus("bus 1"));
    	driveVehicle(()-> new Car("car 1"));
    }
    
    public static void driveVehicle(Supplier<? extends Vehicle> supplier){
    	Vehicle vehicle = supplier.get();
    	vehicle.drive();   
    }
    
    public static void testSupplier3() {
    	ConsumerVehicle cv = ConsumerVehicle.createVehicle(v -> {
    		v.create("nissan", () -> new Car("car 1")); 
    		v.create("toyota", () -> new Car("car 2"));
    		v.create("deawoo", () -> new Car("bus 1"));
    		v.create("jinlong", () -> new Car("bus 2")); 
    	});
    	Vehicle v = cv.getVehicle("nissan");
    	System.out.println(v);
    }
}

interface Vehicle {
	public String name = "Vehicle";
	void drive();
}

class Car implements Vehicle {
	private String name;
	public Car(String name) {
		this.name = name;
	}
	
	@Override
	public void drive(){
		System.out.println("Driving car...");
	}
	
	@Override
	public String toString() {
		return "Car name : " + name;
	}
}

class Bus implements Vehicle {
	private String name;
	public Bus(String name) {
		this.name = name;
	}
	
	@Override
	public void drive(){
		System.out.println("Driving bus...");
	}
	
	@Override
	public String toString() {
		return "Bus name : " + name;
	}
}

interface Factory {
	void create(String name, Supplier<? extends Vehicle> supplier);
}

interface ConsumerVehicle {
	static ConsumerVehicle createVehicle(Consumer<Factory> consumer) {
		Map<String, Supplier<? extends Vehicle>> map = new HashMap<>();
		consumer.accept(map::put);
		return v -> map.get(v).get();
	}
	
	Vehicle getVehicle(String name);
}
