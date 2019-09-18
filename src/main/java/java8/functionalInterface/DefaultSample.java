package java8.functionalInterface;

public class DefaultSample {

	public static void main(String[] args) {
		Test t = new Test();
		t.method();
	}

}

interface I1 {
	default void method() {
		System.out.println("I1 default method.");
	}
}

interface I2 {
	default void method() {
		System.out.println("I2 default method.");
	}
}

interface I3 {
	abstract void method();
}

abstract class C1 {
	void method() {
		System.out.println("C1 default method.");
	}
}

class Test extends C1 implements I1 {
	public void method() {
		I1.super.method();
		System.out.println("Test default method.");
	} 
}