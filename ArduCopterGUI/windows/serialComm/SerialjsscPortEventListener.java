package serialComm;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

class SerialjsscPortEventListener implements SerialPortEventListener {

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR() && event.getEventValue() > 0) {
			try {
				String receivedData = SerialjsscCommunication.serialPort
						.readString(event.getEventValue());
				SerialjsscCommunication.serialPortDataAccessable(receivedData);
			} catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: "
						+ ex);
			}
		}
	}
}