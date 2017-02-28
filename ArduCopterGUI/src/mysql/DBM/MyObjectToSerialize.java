/**
 SELECT * FROM sakila.java_objects;java_objectsCREATE TABLE `java_objects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `object_value` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
 */
package mysql.DBM;

/**
 * @author ColBl
 *
 */
import java.io.Serializable;

public class MyObjectToSerialize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 478647325221803269L;
	private String firstName;
	private String lastName;
	private int age;

	public MyObjectToSerialize(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String toString() {
		return firstName + " " + lastName + ", " + age;
	}
}