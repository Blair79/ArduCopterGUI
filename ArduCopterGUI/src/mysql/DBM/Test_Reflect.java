/**
 * 
 */
package mysql.DBM;

/**
 * @author ColBl
 *
 */
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Test_Reflect {
	public static void main(String[] argv) throws Exception {
	    String driverName = "com.mysql.jdbc.Driver";
	    Class.forName(driverName);

	    String serverName = "localhost";
	    String portNumber = "3306";
	    String mydatabase = serverName + ":" + portNumber;
	    String url = "jdbc:mysql://" + mydatabase;
	    String username = "blair";
	    String password = "Warcraft3@gmx.net";

	    Connection connection = DriverManager.getConnection(url, username, password);
	    DatabaseMetaData dbmd = connection.getMetaData();
	    ResultSet resultSet = dbmd.getTypeInfo();

	    while (resultSet.next()) {
	      // Get the database-specific type name
	      String typeName = resultSet.getString("TYPE_NAME");

	      // Get the java.sql.Types type to which this database-specific type is
	      // mapped
	      short dataType = resultSet.getShort("DATA_TYPE");
	      getJdbcTypeName(dataType);
	    }
	  }

	  public static void getJdbcTypeName(int jdbcType) {
	    Map map = new HashMap();

	    // Get all field in java.sql.Types
	    Field[] fields = java.sql.Types.class.getFields();
	    for (int i = 0; i < fields.length; i++) {
	      try {
	        String name = fields[i].getName();
	        Integer value = (Integer) fields[i].get(null);
	        map.put(value, name);
	      } catch (IllegalAccessException e) {
	      }
	    }
	    System.out.println(map);
	  }
}
