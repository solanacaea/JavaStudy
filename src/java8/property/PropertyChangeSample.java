package java8.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeSample {

	public static void main(String[] args) {
		Bean b = new Bean();  
		b.addPropertyChangeListener(new BeanPropertyChangeListener());  
		b.setName("sb");  
		b.setName("sb123");  
		System.out.println(b.getName()); 
	}
}

class PropertyChangeSupportSample {  
	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport( this );  

	public void addPropertyChangeListener(PropertyChangeListener listener) {  
		listeners.addPropertyChangeListener(listener);  
	}  

	protected void firePropertyChange(String prop, Object old, Object newValue) {  
		listeners.firePropertyChange(prop, old, newValue);  
	}  

	public void removePropertyChangeListener(PropertyChangeListener l) {  
		listeners.removePropertyChangeListener(l);  
	}
}

class Bean extends PropertyChangeSupportSample {
	public static final String TEST = "test";  

	private String name = "";  
	private long age = 20;
	
	public void setName(String name) {  
		if (this.name.equals(name)) {  
			System.out.println("name was not changed!");  
			return;  
		}  
		String oldName = this.name;  
		this.name = name;  
		firePropertyChange(TEST, oldName, name);  
	}  
	
	public String getName() {  
		return this.name;  
	} 
}

class BeanPropertyChangeListener implements PropertyChangeListener {
	public void propertyChange(PropertyChangeEvent evt) {  
		if(evt.getPropertyName().equals(Bean.TEST))  
			System.out.println("I need do something, what is the something");  
	}  
}