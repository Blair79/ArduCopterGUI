package serialComm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import gui.ArduCopterGUI;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialjsscCommunication {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static SerialPort serialPort;
	static int baudrate = SerialPort.BAUDRATE_115200;
	static int dataBits = SerialPort.DATABITS_8;
	static InputStream inputStream;
	static OutputStream outputStream;

	static int parity = SerialPort.PARITY_NONE;
	static Boolean serialPortOpened = false;
	static int stopBits = SerialPort.STOPBITS_1;
	{

		serialPort = new SerialPort("COM28");
		try {
			System.out.println("port open :" + serialPort.openPort());// Open
																		// port
			serialPort.setParams(baudrate, dataBits, stopBits, parity);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
					| SerialPort.FLOWCONTROL_RTSCTS_OUT);

			serialPort.addEventListener(new SerialjsscPortEventListener(),
					SerialPort.MASK_RXCHAR);

		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}

	public synchronized static void serialPortDataAccessable(String receivedData) {

		ArduCopterGUI.set_Output(receivedData);

	}

	public static synchronized boolean openSerialPort(final String portName) {
		Boolean foundPort = false;
		if (SerialjsscCommunication.serialPortOpened != false)
			// System.out.println("Serialport bereits geöffnet");
			return false;
		// System.out.println("Öffne Serialport");
		final String[] portNames = SerialPortList.getPortNames();
		for (final String portName2 : portNames) {
			if (portName.contentEquals(portName2)) {
				foundPort = true;
				break;
			}
		}
		if (foundPort != true)
			// System.out.println("Serialport nicht gefunden: " + portName);
			return false;
		SerialjsscCommunication.serialPort = new SerialPort(portName);
		try {
			SerialjsscCommunication.serialPort.openPort();// Open serial port
			SerialjsscCommunication.serialPort.setParams(
					SerialjsscCommunication.baudrate,
					SerialjsscCommunication.dataBits,
					SerialjsscCommunication.stopBits,
					SerialjsscCommunication.parity);// Set
			SerialjsscCommunication.serialPort
					.addEventListener(new SerialjsscPortEventListener());
			// System.out.println(serialPort.readString());
		} catch (final SerialPortException ex) {
			System.out.println(ex);
		}
		;
		try {
			SerialjsscCommunication.inputStream = new ByteArrayInputStream(
					SerialjsscCommunication.serialPort.readString().getBytes());
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SerialjsscCommunication.serialPortOpened = true;
		return true;
	}

	public static synchronized void closeSerialPort() {
		if (SerialjsscCommunication.serialPortOpened == true) {
			// System.out.println("Schließe Serialport");
			try {
				SerialjsscCommunication.serialPort.closePort();
			} catch (final SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SerialjsscCommunication.serialPortOpened = false;
		} else {
			// System.out.println("Serialport bereits geschlossen");
		}
	}

	public synchronized static void setBaud(final int baud) {
		SerialjsscCommunication.baudrate = baud;

		if (SerialjsscCommunication.serialPortOpened != false) {
			SerialjsscCommunication.closeSerialPort();
			SerialjsscCommunication
					.openSerialPort(SerialjsscCommunication.serialPort
							.getPortName());
		}
	}

	public synchronized static void refreshSerialPort() {
		ArduCopterGUI.clear_Device();

		final String[] portNames = SerialPortList.getPortNames();
		for (final String portName : portNames) {
			ArduCopterGUI.set_Device(portName);
		}
	}

	public static synchronized void sendSerialPort(final String nachricht) {
		// System.out.println("Sende: " + nachricht);
		if (SerialjsscCommunication.serialPortOpened != true)
			return;
		try {
			SerialjsscCommunication.serialPort.writeBytes(nachricht.getBytes());
		} catch (final SerialPortException ex) {
			System.out.println(ex);
		}

	}

}