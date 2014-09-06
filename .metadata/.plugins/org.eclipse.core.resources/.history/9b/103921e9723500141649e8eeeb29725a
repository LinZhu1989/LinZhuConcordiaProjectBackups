package comp6721_Tournament;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * class MyPair
 * 
 * COMP 472/6721: Artificial Intelligence Project Deliverable IV
 * 
 * @author Yulong Song 6516599 Lin Zhu 6555659
 * 
 */

@SuppressWarnings("serial")
public class MyPair implements Cloneable, Serializable {
	private String name;
	private int value;

	public MyPair() {

	}

	public MyPair(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void clear() {
		this.value = 0;
		this.name = "II";
	}

	public String toString() {
		return ("(" + value + "," + name + ")");
	}

	public MyPair deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (MyPair) ois.readObject();
		} catch (IOException e) {
			System.out.println("IO wrong!");
		} catch (ClassNotFoundException e) {
			System.out.println("deep clone wrong!");
		}
		return null;
	}
}
