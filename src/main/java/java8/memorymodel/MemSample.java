package java8.memorymodel;

public class MemSample {

	public static void main(String[] args) {
//		mem1();
		stringSample();
//		mem2();
	}

	private static void mem1() {
		int i = 40;
        int i0 = 40;
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        Double d1=1.0;
        Double d2=1.0;
        
        System.out.println("i=i0		" + (i == i0));				//stack
        System.out.println("i1=i2		" + (i1 == i2));			//constant pool
        System.out.println("i1=i2+i3	" + (i1 == i2 + i3));		//math computation
        System.out.println("i4=i5		" + (i4 == i5));			//heap
        System.out.println("i4=i5+i6	" + (i4 == i5 + i6));    	//same with above above
        System.out.println("d1=d2		" + (d1==d2));				//no constant pool
	}
	
	private static void stringSample() {
		String a = "abc";
		String b = "wtf";
		final String a2 = "abc";
		final String a3 = getA();
		final String b2 = "wtf";
		
		final String a4, b4;
		b4 = "wtf"; a4 = "abc"; 
		
		String s1 = "wtf" + "abc"; //how does it work?
		String s2 = "wtf" + a;
		String s3 = "wtfabc";
		String s4 = b + a;
		String s5 = "wtf" + a;
		String s6 = "wtf" + a2;
		String s7 = "wtf" + a3;
		String s8 = b2 + a2;
		String s9 = b4 + a4;
		String s10 = new StringBuffer("wtf").append("abc").toString();
		
		System.out.println("s1 == s2	" + (s1 == s2));
		System.out.println("s1 == s3	" + (s1 == s3));
		System.out.println("s3 == s4	" + (s3 == s4));
		System.out.println("s1 == s4	" + (s1 == s4));
		System.out.println("s2 == s5	" + (s2 == s5));
		System.out.println("s1 == s5	" + (s1 == s5));
		System.out.println("s1 == s5.intern	" + (s1 == s5.intern()));
		System.out.println("s1 == s6	" + (s1 == s6));
		System.out.println("s1 == s7	" + (s1 == s7));
		System.out.println("s1 == s8	" + (s1 == s8));
		System.out.println("s1 == s9	" + (s1 == s9));
		System.out.println("s1 == s10	" + (s1 == s10));
		
		final StringBuffer sb1 = new StringBuffer("sb");
		final StringBuilder sb2 = new StringBuilder("sb");
		
		String s = new String("string");//how many objects does it created?
	}

	private static String getA() {
		return "abc";
	}
	
	private static void mem2() {
		int date = 9;    
		MemSample m = new MemSample();          
        m.change(date);     
        BirthDay d1= new BirthDay(1, 1, 1970);  
	}
	
	private void change(int i) {
		i = 123;
	}
}

class BirthDay {
	private int day;    
    private int month;    
    private int year;        
    public BirthDay(int d, int m, int y) {    
        day = d;     
        month = m;     
        year = y;    
    }  
}
