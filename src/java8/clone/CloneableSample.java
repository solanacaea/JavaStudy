package java8.clone;

public class CloneableSample {

	public static void main(String[] args) throws CloneNotSupportedException {
		testA();
//		testB();
		
	}

	private static void testA() throws CloneNotSupportedException {
		A a1 = new A(001, "sb", "1998");
		A a2 = a1;
		A a3 = (A) a1.clone();
		
		System.out.println(a1 == a2);
		System.out.println(a1.equals(a2));
		
		System.out.println(a1 == a3);
		System.out.println(a1.equals(a3));
	}

	private static void testB() throws CloneNotSupportedException {
		A a1 = new A(001, "sb", "1998");
		B b1 = new B(a1, false, 123, 12.12);
		B b2 = b1;
		B b3 = (B) b1.clone();
		
		System.out.println("b1 == b2" 		+ "\t\t\t" + (b1 == b2));
		System.out.println("b1.equals(b2)" 	+ "\t\t\t" + b1.equals(b2));
		
		System.out.println("b1 == b3" 		+ "\t\t\t" + (b1 == b3));
		System.out.println("b1.equals(b3)" 	+ "\t\t\t" + b1.equals(b3));
		
		System.out.println("b1.a == b3.a" 			+ "\t\t\t" 	+ (b1.a == b3.a));
		System.out.println("b1.a.equals(b3.a)" 		+ "\t\t" 	+ b1.a.equals(b3.a));
		System.out.println("b1.cai == b3.cai" 		+ "\t\t" 	+ (b1.cai == b3.cai));
		System.out.println("b1.index == b3.index" 	+ "\t\t" 	+ (b1.index == b3.index));
		System.out.println("b1.d == b3.d" 			+ "\t\t\t" 	+ (b1.d == b3.d));
	}
}

class A {
	int id;
	String name;
	String birth;
	
	public A(int id, String name, String birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof A) {
			A another = (A)o; 
			if (another.id == this.id && 
				another.name.equals(this.name) && 
				another.birth.equals(this.birth)) 
				return true;
		}
		return false;
	}
}

class B implements Cloneable {
	A a; 
	Boolean cai; 
	Integer index;
	Double d;
	
	public B(A a, Boolean c, Integer i, Double d) {
		this.a = a;
		this.cai = c;
		this.index = i;
		this.d = d;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof B) {
			B another = (B)o; 
			if (another.a.equals(this.a) && 
				another.cai == this.cai)
				return true;
		}
		return false;
	}
	
	/*
	 	B b = (B) super.clone();
		b.index = new Integer(this.index);
		b.d = new Double(this.d);
		return b;
		
		B b = (B) super.clone();
		b.index = 123;
		b.d = 12.12;
		return b;
	 */
}