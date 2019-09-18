package java8.functionalInterface;

public class FunctionalInterfaceExample {
	public static <T> void handle(Handler<T> handler, T t) {
        System.out.println("before");
        handler.handle(t);
        handler.test1();
        System.out.println("after");
    }

    public static void main(String[] args) {
        String hello = "Hello world!";
        handle(t -> System.out.println(t), hello);
//        handle(System.out::println, hello);
    }
}

@FunctionalInterface
interface Handler<T> {
    abstract void handle(T t);
    static void what() {} //scope
    default void test1() {
    	 System.out.println("test1");
    }
    default void test2() {
    	System.out.println("test2");
    }
}