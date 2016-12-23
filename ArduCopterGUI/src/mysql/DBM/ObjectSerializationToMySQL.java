/**
 SELECT * FROM sakila.java_objects;java_objectsCREATE TABLE `java_objects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `object_value` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
 */
package mysql.DBM;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;
/**
 * @author ColBl
 *
 */
public class ObjectSerializationToMySQL {
	static final String SQL_TO_WRITE_OBJECT = "INSERT INTO JAVA_OBJECTS(NAME, OBJECT_VALUE) VALUES (?, ?)";
	static final String SQL_TO_READ_OBJECT = "SELECT OBJECT_VALUE FROM JAVA_OBJECTS WHERE ID = ?";
	static final String SQL_TO_COUNT_OBJECT = "SELECT COUNT(*) FROM sakila.java_objects";

	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost/sakila";
	static String username = "blair";
	static String password = "Warcraft3@gmx.net";

	/**
	 * This class will create and return a database connection.
	 */
	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * This method will write a java object
	 * to the database
	 * Parameters: connection object and object to be serialized
	 */
	public static long writeJavaObject(Connection conn, Object object)
			throws Exception {
		String className = object.getClass().getName();
		PreparedStatement pstmt = conn.prepareStatement(SQL_TO_WRITE_OBJECT, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, className);
		pstmt.setObject(2, object);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		System.out.println("Serialization Successful."+
						   "Serialized Class: "+ className);
		return id;
	}

	/**
	 * This class will de-serialize a java object from the database
	 */
	public static Object readJavaObject(Connection conn, long id)
			throws Exception {
		PreparedStatement pstmt = conn.prepareStatement(SQL_TO_READ_OBJECT);
		pstmt.setLong(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		byte[] buf = rs.getBytes("object_value");
		ObjectInputStream objectIn = null;
		if (buf != null)
			objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
		Object object = objectIn.readObject();
		String className = object.getClass().getName();
		rs.close();
		pstmt.close();
		System.out.println("Deserialization Successful."+
							" Deserialized Class: "+ className);
		return object;
	}
	public static int countJavaObject(Connection conn)
			throws Exception {
		PreparedStatement pstmt = conn.prepareStatement(SQL_TO_COUNT_OBJECT);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int rowcount = rs.getInt(1);
		return rowcount;
	}
	public static void main(String args[]) throws Exception {
		Connection conn = null;
		try {
			/**Creating DB Connection**/
			conn = getConnection();
			conn.setAutoCommit(false);
			/**Creating Test object to Serialize**/
			MyObjectToSerialize obj = new MyObjectToSerialize("Roberto",
					"Armando", 35);
			/**
			 * Serializing the object and getting the database id,
			 * which is nothing but an autogenerated key
			 */
			long objectID = writeJavaObject(conn, obj);
			conn.commit();
			System.out.println("Serialized object ID " + objectID);
			/**
			 * Reading the object from database.
			 * This object is just serialized into database above.
			 */

			MyObjectToSerialize objFromDatabase = (MyObjectToSerialize) readJavaObject(
					conn, objectID);
			System.out.println("After Deserialization: ");
			System.out.println("Object Value: " + objFromDatabase);
			System.out.println("Class: " + objFromDatabase.getClass().getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**Closing the database connection**/
			conn.close();
		}
	}
}