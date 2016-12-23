/**
 * 
 */
package data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Col.Blair
 *
 */
public abstract class Telemetry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8427939395201446784L;
	private String timestemp = new Timestamp(System.currentTimeMillis())+" "+System.nanoTime();

	/**
	 * @return the now
	 */
	public String getTimestemp() {
		return timestemp;
	}
}
