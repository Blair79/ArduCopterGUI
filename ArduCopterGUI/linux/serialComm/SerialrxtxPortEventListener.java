package serialComm;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialrxtxPortEventListener implements SerialPortEventListener {
	@Override
	public void serialEvent(final SerialPortEvent event) {
		// System.out.println("serialPortEventlistener");
		switch (event.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			SerialrxtxCommunication.serialPortDataAccessable();
			break;
		case SerialPortEvent.BI:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.FE:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		case SerialPortEvent.PE:
		case SerialPortEvent.RI:
		default:
		}
	}
}