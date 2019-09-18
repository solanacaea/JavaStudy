package java8.serialize;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ExternalizableSample {

	public static void main(String[] args) {
		B b = new B();
		b.setName("sb");
		b.setIndex(007);
		System.out.println(b);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sb"));) {
			oos.writeObject(b);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("sb")));){
            B b1 = (B) ois.readObject();
            System.out.println(b1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}

class B implements Externalizable {
	
	public B() {
		
	}
	
	String name;
	int index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
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
		return "A [name=" + name + ", index=" + index + "]";
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);
		out.writeInt(index);
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = (String)in.readObject();
		index = in.readInt();
	}
	
}