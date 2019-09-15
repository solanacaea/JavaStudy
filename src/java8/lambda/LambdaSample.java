package java8.lambda;

import java.io.FileFilter;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class LambdaSample {

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("run it.");
			}
		}).start();
		
		new Thread(() -> System.out.println("lambda run it.")).start();// can i add parameters? 
		
		Callable c = () -> "run";
		PrivilegedAction p = () -> "run";
		
		
		Comparator<String> co = (s1, s2) -> s1.compareToIgnoreCase(s2);
		FileFilter a = f -> f.getName().endsWith(".properties");
		
		
		List<CitiEmployee> members = Arrays.asList(new ServerGuy("sb"), new ServerGuy("sa"), new ClientGuy("bb"), new ClientGuy("cj")); //lengh? 
		Collections.sort(members, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((CitiEmployee) o1).getName().compareTo(((CitiEmployee) o2).getName()); 
			}
		});
		System.out.println(members);
		
	}
}

interface CitiEmployee {
	String getName();
	String work();
	void eat();
}

abstract class DealTraxMember implements CitiEmployee {
	private String name; 
	
	public DealTraxMember(String name) {
		this.name = name;
	}
	
	public void eat() {
		System.out.println("shit!");
	}

	public String getName() {
		return name;
	}
	
	protected boolean isCai() {
		return name.contains("y") ? true : false;
	}
	
	@Override
	public String toString() {
		return getName() + " - " + work();
	}
}

class ClientGuy extends DealTraxMember {

	public ClientGuy(String name) {
		super(name);
	}

	@Override
	public String work() {
		return "write angular code";
	}
}

class ServerGuy extends DealTraxMember {

	public ServerGuy(String name) {
		super(name);
	}

	@Override
	public String work() {
		return "write java code";
	}
}
