package serialComm;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import gui.ArduCopterGUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class SerialrxtxCommunication {

	static int baudrate = 115200;
	static int dataBits = SerialPort.DATABITS_8;
	static Enumeration<?> enumComm;
	static InputStream inputStream;
	static OutputStream outputStream;
	static int parity = SerialPort.PARITY_NONE;

	static SerialPort serialPort;
	static CommPortIdentifier serialPortId;
	static Boolean serialPortOpened = false;
	static int stopBits = SerialPort.STOPBITS_1;

	public static synchronized void closeSerialPort() {
		if (SerialrxtxCommunication.serialPortOpened == true) {
			// System.out.println("Schließe Serialport");
			SerialrxtxCommunication.serialPort.close();
			SerialrxtxCommunication.serialPortOpened = false;
		} else {
			// System.out.println("Serialport bereits geschlossen");
		}
	}

	public static synchronized boolean openSerialPort(final String portName) {
		Boolean foundPort = false;
		if (SerialrxtxCommunication.serialPortOpened != false)
			// System.out.println("Serialport bereits geöffnet");
			return false;
		// System.out.println("Öffne Serialport");
		SerialrxtxCommunication.enumComm = CommPortIdentifier
				.getPortIdentifiers();
		while (SerialrxtxCommunication.enumComm.hasMoreElements()) {
			SerialrxtxCommunication.serialPortId = (CommPortIdentifier) SerialrxtxCommunication.enumComm
					.nextElement();
			if (portName.contentEquals(SerialrxtxCommunication.serialPortId
					.getName())) {
				foundPort = true;
				break;
			}
		}
		if (foundPort != true)
			// System.out.println("Serialport nicht gefunden: " + portName);
			return false;
		try {
			SerialrxtxCommunication.serialPort = (SerialPort) SerialrxtxCommunication.serialPortId
					.open("Öffnen und Senden", 500);
		} catch (final PortInUseException e) {
			// System.out.println("Port belegt");
		}
		try {
			SerialrxtxCommunication.outputStream = SerialrxtxCommunication.serialPort
					.getOutputStream();
		} catch (final IOException e) {
			// System.out.println("Keinen Zugriff auf OutputStream");
		}
		try {
			SerialrxtxCommunication.inputStream = SerialrxtxCommunication.serialPort
					.getInputStream();
		} catch (final IOException e) {
			// System.out.println("Keinen Zugriff auf InputStream");
		}
		try {
			SerialrxtxCommunication.serialPort
					.addEventListener(new SerialrxtxPortEventListener());
		} catch (final TooManyListenersException e) {
			// System.out.println("TooManyListenersException für Serialport");
		}
		SerialrxtxCommunication.serialPort.notifyOnDataAvailable(true);
		try {
			SerialrxtxCommunication.serialPort.setSerialPortParams(
					SerialrxtxCommunication.baudrate,
					SerialrxtxCommunication.dataBits,
					SerialrxtxCommunication.stopBits,
					SerialrxtxCommunication.parity);
		} catch (final UnsupportedCommOperationException e) {
			// System.out.println("Konnte Schnittstellen-Paramter nicht setzen");
		}

		SerialrxtxCommunication.serialPortOpened = true;
		return true;
	}

	public synchronized static void refreshSerialPort() {
		// System.out.println("Akutalisiere Serialport-Liste");
		if (SerialrxtxCommunication.serialPortOpened != false)
			// System.out.println("Serialport ist geöffnet");
			return;
		ArduCopterGUI.clear_Device();
		SerialrxtxCommunication.enumComm = CommPortIdentifier
				.getPortIdentifiers();
		while (SerialrxtxCommunication.enumComm.hasMoreElements()) {
			SerialrxtxCommunication.serialPortId = (CommPortIdentifier) SerialrxtxCommunication.enumComm
					.nextElement();
			if (SerialrxtxCommunication.serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				// System.out.println("Found:" + serialPortId.getName());
				ArduCopterGUI.set_Device(SerialrxtxCommunication.serialPortId
						.getName());
			}
		}
	}

	public static synchronized void sendSerialPort(final String nachricht) {
		// System.out.println("Sende: " + nachricht);
		if (SerialrxtxCommunication.serialPortOpened != true)
			return;
		try {
			SerialrxtxCommunication.outputStream.write(nachricht.getBytes());
		} catch (final IOException e) {
			// System.out.println("Fehler beim Senden");
		}
	}

	public synchronized static void serialPortDataAccessable() {
		try {
			final byte[] data = new byte[150];
			int num;
			while (SerialrxtxCommunication.inputStream.available() > 0) {
				num = SerialrxtxCommunication.inputStream.read(data, 0,
						data.length);
				ArduCopterGUI.set_Output(new String(data, 0, num));
			}
		} catch (final IOException e) {
			// System.out.println("Fehler beim Lesen empfangener Daten");
		}
	}

	public synchronized static void setBaud(final int baud) {
		SerialrxtxCommunication.baudrate = baud;

		if (SerialrxtxCommunication.serialPortOpened != false) {
			SerialrxtxCommunication.closeSerialPort();
			SerialrxtxCommunication
					.openSerialPort(SerialrxtxCommunication.serialPortId
							.getName());
		}
	}
}
