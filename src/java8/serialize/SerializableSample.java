package java8.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableSample {

	public static void main(String[] args) {
//		serialize1();
//		serialize2();
	}

	private static void serialize1() {
		A a = new A();
		a.setName("sb");
		a.setIndex(007);
		System.out.println(a);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sb"));) {
			oos.writeObject(a);//line 1171
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("sb")));){
            A a1 = (A) ois.readObject();
            System.out.println(a1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

	private static void serialize2() {
		A a = new A();
		a.setName("sb");
		a.setIndex(007);
		a.value = "123";//ObjectStreamClass line 1726
		System.out.println(a);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sb"));) {
			oos.writeObject(a);//line 1171
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		A.key = "what";
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("sb")));){
            A a1 = (A) ois.readObject();
            System.out.println(a1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}

class A implements Serializable {
	String name;
	int index;
	transient String value;
	static String key = "test";
	private String privateField;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		privateField = name;
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "A [name=" + name + ", index=" + index + ", value=" + value + ", key=" + key + ", privateField=" + privateField + "]";
	}
	
}