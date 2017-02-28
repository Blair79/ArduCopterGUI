package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import os.OSValidator;
import serialComm.SerialjsscCommunication;
import serialComm.SerialrxtxCommunication;
import webComm.WebValue;

import javax.swing.JSlider;

public final class ArduCopterGUI {

	final class ActionListener_clrLog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (txtArea_Output.getDocument().getLength() > 0) {
				msg.setText(Messages.getString("Status.LogDelete")); //$NON-NLS-1$
			}
			txtArea_Output.setText(null);
		}

	}

	final class ActionListener_connect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
				SerialrxtxCommunication.openSerialPort((String) serial_Device.getSelectedItem());
			}
			if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
				SerialjsscCommunication.openSerialPort((String) serial_Device.getSelectedItem());
			}
			msg.setText(Messages.getString("Status.Serial.Connect_begin").concat((String) serial_Device //$NON-NLS-1$
					.getSelectedItem()) + Messages.getString("Status.Serial.Connect_end")); //$NON-NLS-1$
		}
	}

	final class ActionListener_disconnect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
				SerialrxtxCommunication.sendSerialPort(Messages.getString("Sonderzeichen.IntNull"));// + //$NON-NLS-1$
				// "\n");
			}
			if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
				SerialjsscCommunication.sendSerialPort(Messages.getString("Sonderzeichen.IntNull"));// + //$NON-NLS-1$
				// "\n");
			}

			if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
				SerialrxtxCommunication.closeSerialPort();
			}
			if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
				SerialjsscCommunication.closeSerialPort();
			}
			acc_X.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			acc_Y.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			acc_Z.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			mag_X.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			mag_Y.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			mag_Z.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			gyr_X.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			gyr_Y.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			gyr_Z.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			hPa.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			cel.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			alt.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			acc_Roll.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			acc_Pitch.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			mag_Heading.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			fusion_Roll.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			fusion_Pitch.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			fusion_Heading.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblAcc.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblMag.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblGyro.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblBmp.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_1.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_4.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_3.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_6.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_8.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_12.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_19.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_16.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_5.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_7.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_9.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_13.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_11.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_17.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_15.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_10.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_2.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_14.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			lblNewLabel_18.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			compRoll.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			compPitch.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			calRoll.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			calPitch.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
			vario.setText(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$

			progressBar_Schub.setValue(0);
			progressBar_Motor1.setValue(1060);
			progressBar_Motor2.setValue(1060);
			progressBar_Motor3.setValue(1060);
			progressBar_Motor4.setValue(1060);
			pitchValue = 0;
			rollValue = 0;
			yawValue = 0;
			msg.setText(Messages.getString("Status.Serial.Disconnect")); //$NON-NLS-1$
		}
	}

	final class ActionListener_refresh implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
				SerialrxtxCommunication.refreshSerialPort();
			}
			if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
				SerialjsscCommunication.refreshSerialPort();
			}
			msg.setText(Messages.getString("Status.Serial.refresh")); //$NON-NLS-1$
		}
	}

	/**
	 * @author colblair Send Commands over SerialConnection
	 */
	final class ActionListener_send implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (chckbx_Echo.isSelected() == true) {
				txtArea_Output.append(txtFld_Input.getText() + Messages.getString("Sonderzeichen.EOL")); //$NON-NLS-1$
			}
			if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
				SerialrxtxCommunication.sendSerialPort(txtFld_Input.getText());// +
																				// "\n");
			}
			if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
				SerialjsscCommunication.sendSerialPort(txtFld_Input.getText());// +
																				// "\n");
			}
			txtFld_Input.setText(Messages.getString("Sonderzeichen.Space")); //$NON-NLS-1$
			msg.setText(Messages.getString("Status.Serial.send")); //$NON-NLS-1$
		}
	}

	/**
	 * @author colblair Set Baudrate
	 */
	final class ActionListener_setBaud implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// 300 1200 2400 4800 x9600 14400 19200 28800 38400 57600 x115200
			if (rdbtnmntm_Baud_10.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(300);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(300);
				}
				msg.setText(Messages.getString("Status.Serial.300")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_9.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(1200);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(1200);
				}
				msg.setText(Messages.getString("Status.Serial.1200")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_8.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(2400);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(2400);
				}
				msg.setText(Messages.getString("Status.Serial.2400")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_7.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(4800);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(4800);
				}
				msg.setText(Messages.getString("Status.Serial.4800")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_6.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(9600);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(9600);
				}
				msg.setText(Messages.getString("Status.Serial.9600")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_5.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(14400);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(14400);
				}
				msg.setText(Messages.getString("Status.Serial.14400")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_4.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(19200);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(19200);
				}
				msg.setText(Messages.getString("Status.Serial.19200")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_3.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(28800);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(28800);
				}
				msg.setText(Messages.getString("Status.Serial.28800")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_2.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(38400);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(38400);
				}
				msg.setText(Messages.getString("Status.Serial.38400")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud_1.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(57600);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(57600);
				}
				msg.setText(Messages.getString("Status.Serial.57600")); //$NON-NLS-1$
			}
			if (rdbtnmntm_Baud.isSelected()) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.setBaud(115200);
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.setBaud(115200);
				}
				msg.setText(Messages.getString("Status.Serial.115200")); //$NON-NLS-1$
			}
		}
	}

	private static JLabel acc_Pitch;
	private static JLabel acc_Roll;
	private static JLabel acc_X;
	private static JLabel acc_Y;
	private static JLabel acc_Z;
	private static JLabel alt;
	private static JLabel calPitch;
	private static JLabel calRoll;
	private static JLabel cel;
	private static JCheckBox chckbx_Autoscroll;
	private static JCheckBox chckbx_Echo;
	private static JCheckBox chckbx_Log;
	private static JLabel compPitch;
	private static JLabel compRoll;
	private static JLabel fusion_Heading;
	private static JLabel fusion_Pitch;
	private static JLabel fusion_Roll;
	private static JLabel gyr_X;
	private static JLabel gyr_Y;
	private static JLabel gyr_Z;
	private static HorizonGUI horizonPanel = new HorizonGUI();
	private static JLabel hPa;

	private static String input = Messages.getString("Sonderzeichen.Space"); //$NON-NLS-1$
	private static JLabel lblAcc;
	private static JLabel lblBmp;
	private static JLabel lblGyro;

	private static JLabel lblMag;
	private static JLabel lblNewLabel;
	private static JLabel lblNewLabel_1;
	private static JLabel lblNewLabel_10;
	private static JLabel lblNewLabel_11;
	private static JLabel lblNewLabel_12;
	private static JLabel lblNewLabel_13;
	private static JLabel lblNewLabel_14;
	private static JLabel lblNewLabel_15;
	private static JLabel lblNewLabel_16;
	private static JLabel lblNewLabel_17;
	private static JLabel lblNewLabel_18;
	private static JLabel lblNewLabel_19;
	private static JLabel lblNewLabel_2;
	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_4;
	private static JLabel lblNewLabel_5;
	private static JLabel lblNewLabel_6;
	private static JLabel lblNewLabel_7;
	private static JLabel lblNewLabel_8;
	private static JLabel lblNewLabel_9;
	private static JLabel mag_Heading;
	private static JLabel mag_X;
	private static JLabel mag_Y;
	private static JLabel mag_Z;
	static JLabel msg;
	private static OSValidator OS = new OSValidator();
	// private static Horizon horizon = new Horizon();
	static float pitchValue = 0;
	private static JProgressBar progressBar_Motor1;
	private static JProgressBar progressBar_Motor2;
	private static JProgressBar progressBar_Motor3;
	private static JProgressBar progressBar_Motor4;
	private static JProgressBar progressBar_Schub;
	private static JRadioButton rdbtnComplement;
	private static JRadioButton rdbtnKalman;
	private static JRadioButton rdbtnRoh;
	static float rollValue = 0;
	private static JComboBox<String> serial_Device;
	private static JDesktopPane tab_Control;
	static Timestamp tstamp = new Timestamp(System.currentTimeMillis());
	public static JTextArea txtArea_Output;
	private static JLabel vario;
	static float yawValue = 0;
	private static JComboBox<String> jComboBox_controllers;
	private JPanel Accelerometer;
	private JPanel AccMagFusion;
	private JPanel Horizonfilter;
	private JButton button;
	private JButton button_1;
	private final ButtonGroup buttonGroup_Baud = new ButtonGroup();
	private final ButtonGroup buttonGroup_horizonFilter = new ButtonGroup();
	private JPanel Chipinfo;
	private static JComboBox<String> comboBox = new javax.swing.JComboBox<String>();

	private JPanel Filter;
	private JFrame frame;
	private JPanel Gyroskop;
	private JPanel Horizont;
	private JLabel lblKalman;
	private JLabel lblKomplementr;
	private JLabel lblLuftdruckNnFr;
	private JLabel lblMotor1;
	private JLabel lblMotor2;
	private JLabel lblMotor3;
	private JLabel lblMotor4;
	private JLabel lblNN;
	private JLabel lblPitch_2;
	private JLabel lblRoll_2;

	private JLabel lblSchieberegler;
	private JLabel lblSchub;
	private JLabel lblVariometer;
	private JLabel lblXyAxes;
	private JLabel lblZrotation;
	private JPanel Luftdruckmesser;
	private JPanel Magnetometer;
	private JMenuItem mntmClose;
	private JMenuItem mntmOpen;
	private JMenuItem mntmProgramClose;
	private JMenuItem mntmSafe;
	private JMenuItem mntmSafe_under;
	private JPanel Motorstatus;
	private JProgressBar progressBar;
	private JProgressBar progressBar_1;
	private JRadioButtonMenuItem rdbtnmntm_Baud;
	private JRadioButtonMenuItem rdbtnmntm_Baud_1;
	private JRadioButtonMenuItem rdbtnmntm_Baud_10;
	private JRadioButtonMenuItem rdbtnmntm_Baud_2;
	private JRadioButtonMenuItem rdbtnmntm_Baud_3;
	private JRadioButtonMenuItem rdbtnmntm_Baud_4;
	private JRadioButtonMenuItem rdbtnmntm_Baud_5;
	private JRadioButtonMenuItem rdbtnmntm_Baud_6;
	private JRadioButtonMenuItem rdbtnmntm_Baud_7;
	private JRadioButtonMenuItem rdbtnmntm_Baud_8;
	private JRadioButtonMenuItem rdbtnmntm_Baud_9;
	private JSeparator separator;
	private JSeparator separator_1;
	private JDesktopPane tab_Joystick;
	private JToolBar toolBar;
	private JTextField txtFld_Input;
	private JPanel Weather;
	private static JPanel XY_Axes;
	public static JPanel Axes;
	public static JPanel hat_Switch = new javax.swing.JPanel();;
	public static JPanel buttons;
	public JPanel for_Axes;
	private JPanel PID_Regler;
	private JLabel lblPAnteil;
	private JProgressBar progressBar_2;
	private JLabel lblIAnteil;
	private JProgressBar progressBar_3;
	private JLabel lblDAnteil;
	private JProgressBar progressBar_4;
	private JSlider slider;
	private JSlider slider_1;
	private JSlider slider_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel label;
	private JSlider slider_6;
	private JProgressBar progressBar_8;
	private JLabel label_1;
	private JSlider slider_7;
	private JProgressBar progressBar_9;
	private JLabel label_2;
	private JSlider slider_8;
	private JProgressBar progressBar_10;
	private JCheckBox chckbxMotorenAn;
	private JMenu mnSprache;
	private JMenuItem mntmDeutsch;
	private JMenuItem mntmEnglisch;
	private JMenuItem mntmMysqlServer;
	private JSeparator separator_2;

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public synchronized static void clear_Device() {
		serial_Device.removeAllItems();
	}

	public synchronized static void set_Device(String serialPortId) {
		serial_Device.addItem(serialPortId);
	}

	public static int getSelectedControllerName() {
		System.out.println(jComboBox_controllers.getSelectedIndex());
		return jComboBox_controllers.getSelectedIndex();
	}

	public synchronized static void addControllerName(String controllerName) {
		try {
			jComboBox_controllers.addItem(controllerName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showControllerDisconnected() {
		jComboBox_controllers.removeAllItems();
	}

	/**
	 * Create Command Functions
	 */
	public synchronized static void set_Output(String receive) {

		input = input.concat(receive);

		// set_Output(string);
		if (input.contains(Messages.getString("Sonderzeichen.EOL")) && tab_Control.isShowing()) { //$NON-NLS-1$
			// MySQLConnection.insertAccX(input);
			String[] received = input
					.replaceAll(Messages.getString("Sonderzeichen.EOL"), Messages.getString("Sonderzeichen.Space")) //$NON-NLS-1$ //$NON-NLS-2$
					.split(Messages.getString("Sonderzeichen.Split")); //$NON-NLS-1$
			for (int i = 0; i < received.length; ++i) {
				switch (i + 1) {
				case 1:
					lblAcc.setText(received[i]);
					break;
				/* Ver */
				case 2:
					lblNewLabel_1.setText(received[i]);
					break;
				/* UID */
				case 3:
					lblNewLabel_4.setText(received[i]);
					break;
				/* Max */
				case 4:
					lblNewLabel_8.setText(received[i]);
					break;
				/* Min */
				case 5:
					lblNewLabel_12.setText(received[i]);
					break;
				/* Res */
				case 6:
					lblNewLabel_16.setText(received[i]);
					break;
				/* X */
				case 7:
					acc_X.setText(received[i]);
					break;
				/* Y */
				case 8:
					acc_Y.setText(received[i]);
					break;
				/* Z */
				case 9:
					acc_Z.setText(received[i]);
					break;
				/* Roll */
				case 10: {
					acc_Roll.setText(received[i]);
					// int dx = e.getX() - prevPos.x;
					// Float dx = Float.parseFloat(received[i]);
					// if (dx >= -80 && dx <= 80)
					// horizon.setRollAngleRad(dx * 0.02f);
					if (rdbtnRoh.isSelected())
						rollValue = Float.parseFloat(received[i]);
					break;
				}
				/* Pitch */
				case 11: {
					acc_Pitch.setText(received[i]);
					// int dy = e.getY() - prevPos.y;
					// Float dy = Float.parseFloat(received[i]);
					if (rdbtnRoh.isSelected())
						pitchValue = Float.parseFloat(received[i]);
					// horizon.setPitchAngleRad(horizon.getPitchAngleRad() + dy
					// * 0.01f);

					break;
				}
				/* TYP */
				case 12:
					lblMag.setText(received[i]);
					break;
				/* Ver */
				case 13:
					lblNewLabel.setText(received[i]);
					break;
				/* UID */
				case 14:
					lblNewLabel_5.setText(received[i]);
					break;
				/* Max */
				case 15:
					lblNewLabel_9.setText(received[i]);
					break;
				/* Min */
				case 16:
					lblNewLabel_15.setText(received[i]);
					break;
				/* Res */
				case 17:
					lblNewLabel_17.setText(received[i]);
					break;
				/* X */
				case 18:
					mag_X.setText(received[i]);
					break;
				/* Y */
				case 19:
					mag_Y.setText(received[i]);
					break;
				/* Z */
				case 20:
					mag_Z.setText(received[i]);
					break;
				/* Heading */
				case 21:
					mag_Heading.setText(received[i]);
					break;
				/* Roll */
				case 22:
					fusion_Roll.setText(received[i]);
					break;
				/* PITCH */
				case 23:
					fusion_Pitch.setText(received[i]);
					break;
				/* Heading */
				case 24:
					fusion_Heading.setText(received[i]);
					yawValue = Float.parseFloat(received[i]);
					break;
				/* TYP */
				case 25:
					lblGyro.setText(received[i]);
					break;
				/* Ver */
				case 26:
					lblNewLabel_2.setText(received[i]);
					break;
				/* UID */
				case 27:
					lblNewLabel_6.setText(received[i]);
					break;
				/* Max */
				case 28:
					lblNewLabel_10.setText(received[i]);
					break;
				/* Min */
				case 29:
					lblNewLabel_14.setText(received[i]);
					break;
				/* Res */
				case 30:
					lblNewLabel_18.setText(received[i]);
					break;
				/* X */
				case 31:
					gyr_X.setText(received[i]);
					break;
				/* Y */
				case 32:
					gyr_Y.setText(received[i]);
					break;
				/* Z */
				case 33:
					gyr_Z.setText(received[i]);
					break;
				/* TYP */
				case 34:
					lblBmp.setText(received[i]);
					break;
				/* Ver */
				case 35:
					lblNewLabel_3.setText(received[i]);
					break;
				/* UID */
				case 36:
					lblNewLabel_7.setText(received[i]);
					break;
				/* Max */
				case 37:
					lblNewLabel_11.setText(received[i]);
					break;
				/* Min */
				case 38:
					lblNewLabel_13.setText(received[i]);
					break;
				/* Res */
				case 39:
					lblNewLabel_19.setText(received[i]);
					break;
				/* hPa */
				case 40:
					hPa.setText(received[i]);
					break;
				/* Cel */
				case 41:
					cel.setText(received[i]);
					break;
				/* Alt */
				case 42:
					alt.setText(received[i]);
					break;
				case 43:
					compRoll.setText(received[i]);
					if (rdbtnComplement.isSelected())
						rollValue = Float.parseFloat(received[i]);
					break;
				case 44:
					compPitch.setText(received[i]);
					if (rdbtnComplement.isSelected())
						pitchValue = Float.parseFloat(received[i]);
					break;
				case 45:
					calRoll.setText(received[i]);
					if (rdbtnKalman.isSelected())
						rollValue = Float.parseFloat(received[i]);
					break;
				case 46:
					calPitch.setText(received[i]);
					if (rdbtnKalman.isSelected())
						pitchValue = Float.parseFloat(received[i]);
					break;
				case 47:
					vario.setText(received[i]);
					break;
				case 48:
					progressBar_Schub.setValue(Integer.parseInt(received[i]));
					break;
				case 49:
					progressBar_Motor1.setValue(Integer.parseInt(received[i]));
					break;
				case 50:
					progressBar_Motor2.setValue(Integer.parseInt(received[i]));
					break;
				case 51:
					progressBar_Motor3.setValue(Integer.parseInt(received[i]));
					break;
				case 52:
					progressBar_Motor4.setValue(Integer.parseInt(received[i]));
					break;
				}
				horizonPanel.repaint();
			}
		}
		if (input.contains(Messages.getString("Sonderzeichen.EOL"))) { //$NON-NLS-1$
			input = Messages.getString("Sonderzeichen.Space"); //$NON-NLS-1$
		}
		// System.out.println(receive);
		if (txtArea_Output.isShowing() && chckbx_Log.isSelected()) {
			txtArea_Output.append(receive);
		}
		// System.out.println(receive);
		if (chckbx_Autoscroll.isSelected()) {
			txtArea_Output.setCaretPosition(txtArea_Output.getDocument().getLength());
		}
	}

	/**
	 * Create the application.
	 */
	public ArduCopterGUI() {
		initialize();
		// initComponents();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private synchronized void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1209, 850);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu(Messages.getString("Menu.File")); //$NON-NLS-1$
		menuBar.add(mnDatei);

		mntmOpen = new JMenuItem(Messages.getString("Menu.Open")); //$NON-NLS-1$
		mntmOpen.addActionListener(new ActionListener() {
			private BufferedReader in;

			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileNameExtensionFilter(Messages.getString("FileChooser.Pictures"), //$NON-NLS-1$
						Messages.getString("Postfix.gif"), Messages.getString("Postfix.png"), //$NON-NLS-1$ //$NON-NLS-2$
						Messages.getString("Postfix.jpg"))); //$NON-NLS-1$

				chooser.addChoosableFileFilter(new FileNameExtensionFilter(Messages.getString("FileChooser.Text"), //$NON-NLS-1$
						Messages.getString("Postfix.txt"), Messages.getString("Postfix.png"), //$NON-NLS-1$ //$NON-NLS-2$
						Messages.getString("Postfix.jpg"))); //$NON-NLS-1$
				int rueckgabeWert = chooser.showOpenDialog(null);
				if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
					// Ausgabe der ausgewaehlten Datei
					System.out.println(Messages.getString("Debug.FileOpen") //$NON-NLS-1$
							+ chooser.getSelectedFile().getName());
					try {
						in = new BufferedReader(new FileReader(chooser.getSelectedFile().getPath()));
						String zeile = null;
						while ((zeile = in.readLine()) != null) {
							txtArea_Output.append(zeile + Messages.getString("Sonderzeichen.EOL")); //$NON-NLS-1$
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mnDatei.add(mntmOpen);

		mntmClose = new JMenuItem(Messages.getString("Menu.Close")); //$NON-NLS-1$
		mnDatei.add(mntmClose);

		mntmSafe = new JMenuItem(Messages.getString("Menu.Save")); //$NON-NLS-1$
		mnDatei.add(mntmSafe);

		mntmSafe_under = new JMenuItem(Messages.getString("Menu.Safe_as")); //$NON-NLS-1$
		mntmSafe_under.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				int rueckgabeWert = chooser.showSaveDialog(null);
				if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
					// Ausgabe der ausgewaehlten Datei
					System.out.println(Messages.getString("Debug.FileSave") //$NON-NLS-1$
							+ chooser.getSelectedFile().getName());

					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile().getPath()));

						String[] tab = txtArea_Output.getText().split(Messages.getString("Sonderzeichen.EOL")); //$NON-NLS-1$

						for (String i : tab) {
							bw.write(i);
							bw.newLine();
						}

						bw.close();
					} catch (IOException e1) {
						System.out.println(Messages.getString("Debug.FileException")); //$NON-NLS-1$
					}
				}
			}
		});
		mnDatei.add(mntmSafe_under);

		separator = new JSeparator();
		mnDatei.add(separator);

		JMenuItem mntmConnect = new JMenuItem(Messages.getString("Menu.Connect")); //$NON-NLS-1$
		mnDatei.add(mntmConnect);

		JMenuItem mntmDisconnect = new JMenuItem(Messages.getString("Menu.Disconnect")); //$NON-NLS-1$
		mnDatei.add(mntmDisconnect);

		JMenu mnBaud = new JMenu(Messages.getString("Menu.Serial.Baud")); //$NON-NLS-1$
		mnDatei.add(mnBaud);
		// 300 1200 2400 4800 x9600 14400 19200 28800 38400 57600 x115200

		rdbtnmntm_Baud_10 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.300")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_10);
		mnBaud.add(rdbtnmntm_Baud_10);

		rdbtnmntm_Baud_9 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.1200")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_9);
		mnBaud.add(rdbtnmntm_Baud_9);

		rdbtnmntm_Baud_8 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.2400")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_8);
		mnBaud.add(rdbtnmntm_Baud_8);

		rdbtnmntm_Baud_7 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.4800")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_7);
		mnBaud.add(rdbtnmntm_Baud_7);

		rdbtnmntm_Baud_6 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.9600")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_6);
		mnBaud.add(rdbtnmntm_Baud_6);

		rdbtnmntm_Baud_5 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.14400")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_5);
		mnBaud.add(rdbtnmntm_Baud_5);

		rdbtnmntm_Baud_4 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.19200")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_4);
		mnBaud.add(rdbtnmntm_Baud_4);

		rdbtnmntm_Baud_3 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.28800")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_3);
		mnBaud.add(rdbtnmntm_Baud_3);
		// rdbtnmntm_Baud_3.setSelected(true);

		rdbtnmntm_Baud_2 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.38400")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_2);
		mnBaud.add(rdbtnmntm_Baud_2);

		rdbtnmntm_Baud_1 = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.57600")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud_1);
		mnBaud.add(rdbtnmntm_Baud_1);

		rdbtnmntm_Baud = new JRadioButtonMenuItem(Messages.getString("Menu.Serial.115200")); //$NON-NLS-1$
		buttonGroup_Baud.add(rdbtnmntm_Baud);
		mnBaud.add(rdbtnmntm_Baud);
		rdbtnmntm_Baud.setSelected(true);

		separator_2 = new JSeparator();
		mnDatei.add(separator_2);

		mntmMysqlServer = new JMenuItem(Messages.getString("ArduCopterGUI.mntmMysqlServer.text")); //$NON-NLS-1$
		mnDatei.add(mntmMysqlServer);

		separator_1 = new JSeparator();
		mnDatei.add(separator_1);

		mntmProgramClose = new JMenuItem(Messages.getString("Menu.Quit")); //$NON-NLS-1$
		mntmProgramClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
					SerialrxtxCommunication.closeSerialPort();
				}
				if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
					SerialjsscCommunication.closeSerialPort();
				}
				System.exit(0);

			}
		});
		mnDatei.add(mntmProgramClose);

		mnSprache = new JMenu(Messages.getString("Menu.Language")); //$NON-NLS-1$
		menuBar.add(mnSprache);

		mntmDeutsch = new JMenuItem(Messages.getString("Menu.Deutsch")); //$NON-NLS-1$
		mntmDeutsch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(Locale.GERMAN);
			}
		});
		mnSprache.add(mntmDeutsch);

		mntmEnglisch = new JMenuItem(Messages.getString("Menu.English")); //$NON-NLS-1$
		mntmEnglisch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(Locale.UK);
			}
		});
		mnSprache.add(mntmEnglisch);

		toolBar = new JToolBar();
		toolBar.setBorder(null);
		toolBar.setBorderPainted(false);
		getFrame().getContentPane().add(toolBar, BorderLayout.NORTH);

		button = new JButton(new ImageIcon(ArduCopterGUI.class.getResource(Messages.getString("Icon.Connect")))); //$NON-NLS-1$
		button.setMinimumSize(new Dimension(66, 42));
		button.setMaximumSize(new Dimension(66, 42));
		button.setPreferredSize(new Dimension(66, 42));
		button.setToolTipText(Messages.getString("Infotext.ConnectButton")); //$NON-NLS-1$
		toolBar.add(button);

		button_1 = new JButton(new ImageIcon(ArduCopterGUI.class.getResource(Messages.getString("Icon.Disconnect")))); //$NON-NLS-1$
		button_1.setToolTipText(Messages.getString("Infotext.DisonnectButton")); //$NON-NLS-1$
		toolBar.add(button_1);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		getFrame().getContentPane().add(tabbedPane, BorderLayout.CENTER);

		tab_Control = new JDesktopPane();
		tab_Control.setToolTipText(Messages.getString("Infotext.TabControl")); //$NON-NLS-1$
		tabbedPane.addTab(Messages.getString("Tab.Title.Control"), null, tab_Control, null); //$NON-NLS-1$
		GridBagLayout gbl_tab_Control = new GridBagLayout();
		gbl_tab_Control.columnWidths = new int[] { 766, 0 };
		gbl_tab_Control.rowHeights = new int[] { 349, 0 };
		gbl_tab_Control.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_tab_Control.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		tab_Control.setLayout(gbl_tab_Control);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		tab_Control.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 193, 291, 187, 258, 0 };
		gbl_panel.rowHeights = new int[] { 0, 160, 51, 68, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		Accelerometer = new JPanel();
		Accelerometer.setBorder(new TitledBorder(null, Messages.getString("Title.Acc"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Accelerometer = new GridBagConstraints();
		gbc_Accelerometer.insets = new Insets(0, 0, 5, 5);
		gbc_Accelerometer.fill = GridBagConstraints.BOTH;
		gbc_Accelerometer.gridx = 0;
		gbc_Accelerometer.gridy = 0;
		panel.add(Accelerometer, gbc_Accelerometer);
		GridBagLayout gbl_Accelerometer = new GridBagLayout();
		gbl_Accelerometer.columnWidths = new int[] { 53, 53 };
		gbl_Accelerometer.rowHeights = new int[] { 24, 0, 0, 0, 0, 0 };
		gbl_Accelerometer.columnWeights = new double[] { 0.0, 0.0 };
		gbl_Accelerometer.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Accelerometer.setLayout(gbl_Accelerometer);

		JLabel lblX = new JLabel(Messages.getString("Dimension.X")); //$NON-NLS-1$
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.WEST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 0;
		gbc_lblX.gridy = 0;
		Accelerometer.add(lblX, gbc_lblX);
		lblX.setHorizontalAlignment(SwingConstants.LEFT);

		acc_X = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_acc_X = new GridBagConstraints();
		gbc_acc_X.insets = new Insets(0, 0, 5, 0);
		gbc_acc_X.fill = GridBagConstraints.HORIZONTAL;
		gbc_acc_X.gridx = 1;
		gbc_acc_X.gridy = 0;
		Accelerometer.add(acc_X, gbc_acc_X);
		acc_X.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblY = new JLabel(Messages.getString("Dimension.Y")); //$NON-NLS-1$
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.anchor = GridBagConstraints.WEST;
		gbc_lblY.insets = new Insets(0, 0, 5, 5);
		gbc_lblY.gridx = 0;
		gbc_lblY.gridy = 1;
		Accelerometer.add(lblY, gbc_lblY);
		lblY.setHorizontalAlignment(SwingConstants.LEFT);

		acc_Y = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_acc_Y = new GridBagConstraints();
		gbc_acc_Y.fill = GridBagConstraints.HORIZONTAL;
		gbc_acc_Y.insets = new Insets(0, 0, 5, 0);
		gbc_acc_Y.gridx = 1;
		gbc_acc_Y.gridy = 1;
		Accelerometer.add(acc_Y, gbc_acc_Y);
		acc_Y.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblZ = new JLabel(Messages.getString("Dimension.Z")); //$NON-NLS-1$
		GridBagConstraints gbc_lblZ = new GridBagConstraints();
		gbc_lblZ.anchor = GridBagConstraints.WEST;
		gbc_lblZ.insets = new Insets(0, 0, 5, 5);
		gbc_lblZ.gridx = 0;
		gbc_lblZ.gridy = 2;
		Accelerometer.add(lblZ, gbc_lblZ);
		lblZ.setHorizontalAlignment(SwingConstants.LEFT);

		acc_Z = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_acc_Z = new GridBagConstraints();
		gbc_acc_Z.fill = GridBagConstraints.HORIZONTAL;
		gbc_acc_Z.insets = new Insets(0, 0, 5, 0);
		gbc_acc_Z.gridx = 1;
		gbc_acc_Z.gridy = 2;
		Accelerometer.add(acc_Z, gbc_acc_Z);
		acc_Z.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblRoll = new JLabel(Messages.getString("Movement.Roll")); //$NON-NLS-1$
		GridBagConstraints gbc_lblRoll = new GridBagConstraints();
		gbc_lblRoll.anchor = GridBagConstraints.WEST;
		gbc_lblRoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll.gridx = 0;
		gbc_lblRoll.gridy = 3;
		Accelerometer.add(lblRoll, gbc_lblRoll);
		lblRoll.setHorizontalAlignment(SwingConstants.LEFT);

		acc_Roll = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_acc_Roll = new GridBagConstraints();
		gbc_acc_Roll.fill = GridBagConstraints.HORIZONTAL;
		gbc_acc_Roll.insets = new Insets(0, 0, 5, 0);
		gbc_acc_Roll.gridx = 1;
		gbc_acc_Roll.gridy = 3;
		Accelerometer.add(acc_Roll, gbc_acc_Roll);
		acc_Roll.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblPitch = new JLabel(Messages.getString("Movement.Pitch")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPitch = new GridBagConstraints();
		gbc_lblPitch.anchor = GridBagConstraints.WEST;
		gbc_lblPitch.insets = new Insets(0, 0, 0, 5);
		gbc_lblPitch.gridx = 0;
		gbc_lblPitch.gridy = 4;
		Accelerometer.add(lblPitch, gbc_lblPitch);
		lblPitch.setHorizontalAlignment(SwingConstants.LEFT);

		acc_Pitch = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_acc_Pitch = new GridBagConstraints();
		gbc_acc_Pitch.fill = GridBagConstraints.HORIZONTAL;
		gbc_acc_Pitch.gridx = 1;
		gbc_acc_Pitch.gridy = 4;
		Accelerometer.add(acc_Pitch, gbc_acc_Pitch);
		acc_Pitch.setHorizontalAlignment(SwingConstants.CENTER);

		Chipinfo = new JPanel();
		Chipinfo.setBorder(new TitledBorder(null, Messages.getString("Title.Chip"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Chipinfo = new GridBagConstraints();
		gbc_Chipinfo.gridwidth = 2;
		gbc_Chipinfo.insets = new Insets(0, 0, 5, 5);
		gbc_Chipinfo.fill = GridBagConstraints.BOTH;
		gbc_Chipinfo.gridx = 1;
		gbc_Chipinfo.gridy = 0;
		panel.add(Chipinfo, gbc_Chipinfo);
		GridBagLayout gbl_Chipinfo = new GridBagLayout();
		gbl_Chipinfo.columnWidths = new int[] { 53, 61, 0, 0, 0, 0, 0 };
		gbl_Chipinfo.rowHeights = new int[] { 24, 24, 0, 0, 0, 0 };
		gbl_Chipinfo.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_Chipinfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Chipinfo.setLayout(gbl_Chipinfo);

		JLabel lblChipname = new JLabel(Messages.getString("Label.Chipname")); //$NON-NLS-1$
		GridBagConstraints gbc_lblChipname = new GridBagConstraints();
		gbc_lblChipname.insets = new Insets(0, 0, 5, 5);
		gbc_lblChipname.gridx = 0;
		gbc_lblChipname.gridy = 0;
		Chipinfo.add(lblChipname, gbc_lblChipname);

		JLabel lblVersion = new JLabel(Messages.getString("Label.Version")); //$NON-NLS-1$
		GridBagConstraints gbc_lblVersion = new GridBagConstraints();
		gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
		gbc_lblVersion.gridx = 1;
		gbc_lblVersion.gridy = 0;
		Chipinfo.add(lblVersion, gbc_lblVersion);

		JLabel lblUID = new JLabel(Messages.getString("Label.UID")); //$NON-NLS-1$
		GridBagConstraints gbc_lblUID = new GridBagConstraints();
		gbc_lblUID.insets = new Insets(0, 0, 5, 5);
		gbc_lblUID.gridx = 2;
		gbc_lblUID.gridy = 0;
		Chipinfo.add(lblUID, gbc_lblUID);

		JLabel lblMax = new JLabel(Messages.getString("Label.Max")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 3;
		gbc_lblMax.gridy = 0;
		Chipinfo.add(lblMax, gbc_lblMax);

		JLabel lblMin = new JLabel(Messages.getString("Label.Min")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 4;
		gbc_lblMin.gridy = 0;
		Chipinfo.add(lblMin, gbc_lblMin);

		JLabel lblResolution = new JLabel(Messages.getString("Label.Resolution")); //$NON-NLS-1$
		GridBagConstraints gbc_lblResolution = new GridBagConstraints();
		gbc_lblResolution.insets = new Insets(0, 0, 5, 0);
		gbc_lblResolution.gridx = 5;
		gbc_lblResolution.gridy = 0;
		Chipinfo.add(lblResolution, gbc_lblResolution);

		lblAcc = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblAcc = new GridBagConstraints();
		gbc_lblAcc.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcc.gridx = 0;
		gbc_lblAcc.gridy = 1;
		Chipinfo.add(lblAcc, gbc_lblAcc);

		lblNewLabel_1 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		Chipinfo.add(lblNewLabel_1, gbc_lblNewLabel_1);

		lblNewLabel_4 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		Chipinfo.add(lblNewLabel_4, gbc_lblNewLabel_4);

		lblNewLabel_8 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 3;
		gbc_lblNewLabel_8.gridy = 1;
		Chipinfo.add(lblNewLabel_8, gbc_lblNewLabel_8);

		lblNewLabel_12 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 4;
		gbc_lblNewLabel_12.gridy = 1;
		Chipinfo.add(lblNewLabel_12, gbc_lblNewLabel_12);

		lblNewLabel_16 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_16.gridx = 5;
		gbc_lblNewLabel_16.gridy = 1;
		Chipinfo.add(lblNewLabel_16, gbc_lblNewLabel_16);

		lblMag = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMag = new GridBagConstraints();
		gbc_lblMag.insets = new Insets(0, 0, 5, 5);
		gbc_lblMag.gridx = 0;
		gbc_lblMag.gridy = 2;
		Chipinfo.add(lblMag, gbc_lblMag);

		lblNewLabel = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		Chipinfo.add(lblNewLabel, gbc_lblNewLabel);

		lblNewLabel_5 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 2;
		Chipinfo.add(lblNewLabel_5, gbc_lblNewLabel_5);

		lblNewLabel_9 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 3;
		gbc_lblNewLabel_9.gridy = 2;
		Chipinfo.add(lblNewLabel_9, gbc_lblNewLabel_9);

		lblNewLabel_15 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 4;
		gbc_lblNewLabel_15.gridy = 2;
		Chipinfo.add(lblNewLabel_15, gbc_lblNewLabel_15);

		lblNewLabel_17 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_17.gridx = 5;
		gbc_lblNewLabel_17.gridy = 2;
		Chipinfo.add(lblNewLabel_17, gbc_lblNewLabel_17);

		lblGyro = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblGyro = new GridBagConstraints();
		gbc_lblGyro.insets = new Insets(0, 0, 5, 5);
		gbc_lblGyro.gridx = 0;
		gbc_lblGyro.gridy = 3;
		Chipinfo.add(lblGyro, gbc_lblGyro);

		lblNewLabel_2 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		Chipinfo.add(lblNewLabel_2, gbc_lblNewLabel_2);

		lblNewLabel_6 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 3;
		Chipinfo.add(lblNewLabel_6, gbc_lblNewLabel_6);

		lblNewLabel_10 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 3;
		gbc_lblNewLabel_10.gridy = 3;
		Chipinfo.add(lblNewLabel_10, gbc_lblNewLabel_10);

		lblNewLabel_14 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.gridx = 4;
		gbc_lblNewLabel_14.gridy = 3;
		Chipinfo.add(lblNewLabel_14, gbc_lblNewLabel_14);

		lblNewLabel_18 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_18.gridx = 5;
		gbc_lblNewLabel_18.gridy = 3;
		Chipinfo.add(lblNewLabel_18, gbc_lblNewLabel_18);

		lblBmp = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblBmp = new GridBagConstraints();
		gbc_lblBmp.insets = new Insets(0, 0, 0, 5);
		gbc_lblBmp.gridx = 0;
		gbc_lblBmp.gridy = 4;
		Chipinfo.add(lblBmp, gbc_lblBmp);

		lblNewLabel_3 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		Chipinfo.add(lblNewLabel_3, gbc_lblNewLabel_3);

		lblNewLabel_7 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 4;
		Chipinfo.add(lblNewLabel_7, gbc_lblNewLabel_7);

		lblNewLabel_11 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_11.gridx = 3;
		gbc_lblNewLabel_11.gridy = 4;
		Chipinfo.add(lblNewLabel_11, gbc_lblNewLabel_11);

		lblNewLabel_13 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_13.gridx = 4;
		gbc_lblNewLabel_13.gridy = 4;
		Chipinfo.add(lblNewLabel_13, gbc_lblNewLabel_13);

		lblNewLabel_19 = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.gridx = 5;
		gbc_lblNewLabel_19.gridy = 4;
		Chipinfo.add(lblNewLabel_19, gbc_lblNewLabel_19);

		Horizont = new JPanel();
		Horizont.setBorder(new TitledBorder(null, Messages.getString("Title.Horizon"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Horizont = new GridBagConstraints();
		gbc_Horizont.gridheight = 2;
		gbc_Horizont.insets = new Insets(0, 0, 5, 0);
		gbc_Horizont.fill = GridBagConstraints.BOTH;
		gbc_Horizont.gridx = 3;
		gbc_Horizont.gridy = 0;
		panel.add(Horizont, gbc_Horizont);
		GridBagLayout gbl_Horizont = new GridBagLayout();
		gbl_Horizont.columnWidths = new int[] { 139, 0 };
		gbl_Horizont.rowHeights = new int[] { 303, 0 };
		gbl_Horizont.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_Horizont.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		Horizont.setLayout(gbl_Horizont);
		GridBagConstraints gbc_horizonPanel = new GridBagConstraints();
		gbc_horizonPanel.fill = GridBagConstraints.BOTH;
		gbc_horizonPanel.gridx = 0;
		gbc_horizonPanel.gridy = 0;
		Horizont.add(horizonPanel, gbc_horizonPanel);
		horizonPanel.setBorder(null);
		horizonPanel.setBackground(UIManager.getColor(Messages.getString("FileChooser.Background"))); //$NON-NLS-1$
		horizonPanel.setToolTipText(Messages.getString("Infotext.Horizon")); //$NON-NLS-1$
		GridBagLayout gbl_horizonPanel = new GridBagLayout();
		gbl_horizonPanel.columnWidths = new int[] { 0 };
		gbl_horizonPanel.rowHeights = new int[] { 0 };
		gbl_horizonPanel.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_horizonPanel.rowWeights = new double[] { Double.MIN_VALUE };
		horizonPanel.setLayout(gbl_horizonPanel);

		Magnetometer = new JPanel();
		Magnetometer.setBorder(new TitledBorder(null, Messages.getString("Title.Mag"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Magnetometer = new GridBagConstraints();
		gbc_Magnetometer.insets = new Insets(0, 0, 5, 5);
		gbc_Magnetometer.fill = GridBagConstraints.BOTH;
		gbc_Magnetometer.gridx = 0;
		gbc_Magnetometer.gridy = 1;
		panel.add(Magnetometer, gbc_Magnetometer);
		GridBagLayout gbl_Magnetometer = new GridBagLayout();
		gbl_Magnetometer.columnWidths = new int[] { 53, 53, 0 };
		gbl_Magnetometer.rowHeights = new int[] { 24, 0, 0, 0, 0 };
		gbl_Magnetometer.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Magnetometer.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Magnetometer.setLayout(gbl_Magnetometer);

		JLabel lblX_1 = new JLabel(Messages.getString("Dimension.X")); //$NON-NLS-1$
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.anchor = GridBagConstraints.WEST;
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 0;
		gbc_lblX_1.gridy = 0;
		Magnetometer.add(lblX_1, gbc_lblX_1);

		mag_X = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_mag_X = new GridBagConstraints();
		gbc_mag_X.fill = GridBagConstraints.HORIZONTAL;
		gbc_mag_X.insets = new Insets(0, 0, 5, 0);
		gbc_mag_X.gridx = 1;
		gbc_mag_X.gridy = 0;
		Magnetometer.add(mag_X, gbc_mag_X);
		mag_X.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblY_1 = new JLabel(Messages.getString("Dimension.Y")); //$NON-NLS-1$
		GridBagConstraints gbc_lblY_1 = new GridBagConstraints();
		gbc_lblY_1.anchor = GridBagConstraints.WEST;
		gbc_lblY_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblY_1.gridx = 0;
		gbc_lblY_1.gridy = 1;
		Magnetometer.add(lblY_1, gbc_lblY_1);

		mag_Y = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_mag_Y = new GridBagConstraints();
		gbc_mag_Y.fill = GridBagConstraints.HORIZONTAL;
		gbc_mag_Y.insets = new Insets(0, 0, 5, 0);
		gbc_mag_Y.gridx = 1;
		gbc_mag_Y.gridy = 1;
		Magnetometer.add(mag_Y, gbc_mag_Y);
		mag_Y.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblZ_1 = new JLabel(Messages.getString("Dimension.Z")); //$NON-NLS-1$
		GridBagConstraints gbc_lblZ_1 = new GridBagConstraints();
		gbc_lblZ_1.anchor = GridBagConstraints.WEST;
		gbc_lblZ_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblZ_1.gridx = 0;
		gbc_lblZ_1.gridy = 2;
		Magnetometer.add(lblZ_1, gbc_lblZ_1);

		mag_Z = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_mag_Z = new GridBagConstraints();
		gbc_mag_Z.fill = GridBagConstraints.HORIZONTAL;
		gbc_mag_Z.insets = new Insets(0, 0, 5, 0);
		gbc_mag_Z.gridx = 1;
		gbc_mag_Z.gridy = 2;
		Magnetometer.add(mag_Z, gbc_mag_Z);
		mag_Z.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHeading = new JLabel(Messages.getString("Label.Heading")); //$NON-NLS-1$
		GridBagConstraints gbc_lblHeading = new GridBagConstraints();
		gbc_lblHeading.anchor = GridBagConstraints.WEST;
		gbc_lblHeading.insets = new Insets(0, 0, 0, 5);
		gbc_lblHeading.gridx = 0;
		gbc_lblHeading.gridy = 3;
		Magnetometer.add(lblHeading, gbc_lblHeading);

		mag_Heading = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_mag_Heading = new GridBagConstraints();
		gbc_mag_Heading.fill = GridBagConstraints.HORIZONTAL;
		gbc_mag_Heading.gridx = 1;
		gbc_mag_Heading.gridy = 3;
		Magnetometer.add(mag_Heading, gbc_mag_Heading);
		mag_Heading.setHorizontalAlignment(SwingConstants.CENTER);

		AccMagFusion = new JPanel();
		AccMagFusion.setBorder(new TitledBorder(null, Messages.getString("Title.Acc_Mag_Fusion"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_AccMagFusion = new GridBagConstraints();
		gbc_AccMagFusion.insets = new Insets(0, 0, 5, 5);
		gbc_AccMagFusion.fill = GridBagConstraints.BOTH;
		gbc_AccMagFusion.gridx = 1;
		gbc_AccMagFusion.gridy = 1;
		panel.add(AccMagFusion, gbc_AccMagFusion);
		GridBagLayout gbl_AccMagFusion = new GridBagLayout();
		gbl_AccMagFusion.columnWidths = new int[] { 53, 53, 0 };
		gbl_AccMagFusion.rowHeights = new int[] { 24, 0, 0, 0 };
		gbl_AccMagFusion.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_AccMagFusion.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		AccMagFusion.setLayout(gbl_AccMagFusion);

		JLabel lblRoll_1 = new JLabel(Messages.getString("Movement.Roll")); //$NON-NLS-1$
		GridBagConstraints gbc_lblRoll_1 = new GridBagConstraints();
		gbc_lblRoll_1.anchor = GridBagConstraints.WEST;
		gbc_lblRoll_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll_1.gridx = 0;
		gbc_lblRoll_1.gridy = 0;
		AccMagFusion.add(lblRoll_1, gbc_lblRoll_1);
		lblRoll_1.setHorizontalAlignment(SwingConstants.LEFT);

		fusion_Roll = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_fusion_Roll = new GridBagConstraints();
		gbc_fusion_Roll.anchor = GridBagConstraints.EAST;
		gbc_fusion_Roll.insets = new Insets(0, 0, 5, 0);
		gbc_fusion_Roll.gridx = 1;
		gbc_fusion_Roll.gridy = 0;
		AccMagFusion.add(fusion_Roll, gbc_fusion_Roll);
		fusion_Roll.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblPitch_1 = new JLabel(Messages.getString("Movement.Pitch")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPitch_1 = new GridBagConstraints();
		gbc_lblPitch_1.anchor = GridBagConstraints.WEST;
		gbc_lblPitch_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPitch_1.gridx = 0;
		gbc_lblPitch_1.gridy = 1;
		AccMagFusion.add(lblPitch_1, gbc_lblPitch_1);
		lblPitch_1.setHorizontalAlignment(SwingConstants.LEFT);

		fusion_Pitch = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_fusion_Pitch = new GridBagConstraints();
		gbc_fusion_Pitch.anchor = GridBagConstraints.EAST;
		gbc_fusion_Pitch.insets = new Insets(0, 0, 5, 0);
		gbc_fusion_Pitch.gridx = 1;
		gbc_fusion_Pitch.gridy = 1;
		AccMagFusion.add(fusion_Pitch, gbc_fusion_Pitch);
		fusion_Pitch.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHeading_1 = new JLabel(Messages.getString("Label.Heading")); //$NON-NLS-1$
		GridBagConstraints gbc_lblHeading_1 = new GridBagConstraints();
		gbc_lblHeading_1.anchor = GridBagConstraints.WEST;
		gbc_lblHeading_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblHeading_1.gridx = 0;
		gbc_lblHeading_1.gridy = 2;
		AccMagFusion.add(lblHeading_1, gbc_lblHeading_1);
		lblHeading_1.setHorizontalAlignment(SwingConstants.LEFT);

		fusion_Heading = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_fusion_Heading = new GridBagConstraints();
		gbc_fusion_Heading.anchor = GridBagConstraints.EAST;
		gbc_fusion_Heading.gridx = 1;
		gbc_fusion_Heading.gridy = 2;
		AccMagFusion.add(fusion_Heading, gbc_fusion_Heading);
		fusion_Heading.setHorizontalAlignment(SwingConstants.CENTER);

		Luftdruckmesser = new JPanel();
		Luftdruckmesser.setBorder(new TitledBorder(null, Messages.getString("Title.Baro"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Luftdruckmesser = new GridBagConstraints();
		gbc_Luftdruckmesser.insets = new Insets(0, 0, 5, 5);
		gbc_Luftdruckmesser.fill = GridBagConstraints.BOTH;
		gbc_Luftdruckmesser.gridx = 2;
		gbc_Luftdruckmesser.gridy = 1;
		panel.add(Luftdruckmesser, gbc_Luftdruckmesser);
		GridBagLayout gbl_Luftdruckmesser = new GridBagLayout();
		gbl_Luftdruckmesser.columnWidths = new int[] { 53, 53, 0 };
		gbl_Luftdruckmesser.rowHeights = new int[] { 24, 0, 0, 0, 0 };
		gbl_Luftdruckmesser.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_Luftdruckmesser.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Luftdruckmesser.setLayout(gbl_Luftdruckmesser);

		JLabel lblTemperatur = new JLabel(Messages.getString("Label.Temperature")); //$NON-NLS-1$
		GridBagConstraints gbc_lblTemperatur = new GridBagConstraints();
		gbc_lblTemperatur.anchor = GridBagConstraints.WEST;
		gbc_lblTemperatur.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemperatur.gridx = 0;
		gbc_lblTemperatur.gridy = 0;
		Luftdruckmesser.add(lblTemperatur, gbc_lblTemperatur);

		cel = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_cel = new GridBagConstraints();
		gbc_cel.anchor = GridBagConstraints.EAST;
		gbc_cel.insets = new Insets(0, 0, 5, 0);
		gbc_cel.gridx = 1;
		gbc_cel.gridy = 0;
		Luftdruckmesser.add(cel, gbc_cel);
		cel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHpa = new JLabel(Messages.getString("Label.AirPressure")); //$NON-NLS-1$
		GridBagConstraints gbc_lblHpa = new GridBagConstraints();
		gbc_lblHpa.anchor = GridBagConstraints.WEST;
		gbc_lblHpa.insets = new Insets(0, 0, 5, 5);
		gbc_lblHpa.gridx = 0;
		gbc_lblHpa.gridy = 1;
		Luftdruckmesser.add(lblHpa, gbc_lblHpa);
		lblHpa.setHorizontalAlignment(SwingConstants.LEFT);

		hPa = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_hPa = new GridBagConstraints();
		gbc_hPa.anchor = GridBagConstraints.EAST;
		gbc_hPa.insets = new Insets(0, 0, 5, 0);
		gbc_hPa.gridx = 1;
		gbc_hPa.gridy = 1;
		Luftdruckmesser.add(hPa, gbc_hPa);
		hPa.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHhe = new JLabel(Messages.getString("Label.Hight")); //$NON-NLS-1$
		GridBagConstraints gbc_lblHhe = new GridBagConstraints();
		gbc_lblHhe.anchor = GridBagConstraints.WEST;
		gbc_lblHhe.insets = new Insets(0, 0, 5, 5);
		gbc_lblHhe.gridx = 0;
		gbc_lblHhe.gridy = 2;
		Luftdruckmesser.add(lblHhe, gbc_lblHhe);

		alt = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_alt = new GridBagConstraints();
		gbc_alt.anchor = GridBagConstraints.EAST;
		gbc_alt.insets = new Insets(0, 0, 5, 0);
		gbc_alt.gridx = 1;
		gbc_alt.gridy = 2;
		Luftdruckmesser.add(alt, gbc_alt);
		alt.setHorizontalAlignment(SwingConstants.CENTER);

		lblVariometer = new JLabel(Messages.getString("Label.Variometer")); //$NON-NLS-1$
		GridBagConstraints gbc_lblVariometer = new GridBagConstraints();
		gbc_lblVariometer.anchor = GridBagConstraints.WEST;
		gbc_lblVariometer.insets = new Insets(0, 0, 0, 5);
		gbc_lblVariometer.gridx = 0;
		gbc_lblVariometer.gridy = 3;
		Luftdruckmesser.add(lblVariometer, gbc_lblVariometer);

		vario = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_vario = new GridBagConstraints();
		gbc_vario.anchor = GridBagConstraints.EAST;
		gbc_vario.gridx = 1;
		gbc_vario.gridy = 3;
		Luftdruckmesser.add(vario, gbc_vario);

		Gyroskop = new JPanel();
		Gyroskop.setBorder(new TitledBorder(null, Messages.getString("Title.Gyr"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Gyroskop = new GridBagConstraints();
		gbc_Gyroskop.gridheight = 2;
		gbc_Gyroskop.insets = new Insets(0, 0, 5, 5);
		gbc_Gyroskop.fill = GridBagConstraints.BOTH;
		gbc_Gyroskop.gridx = 0;
		gbc_Gyroskop.gridy = 2;
		panel.add(Gyroskop, gbc_Gyroskop);
		GridBagLayout gbl_Gyroskop = new GridBagLayout();
		gbl_Gyroskop.columnWidths = new int[] { 53, 61, 0 };
		gbl_Gyroskop.rowHeights = new int[] { 24, 0, 0, 0 };
		gbl_Gyroskop.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Gyroskop.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Gyroskop.setLayout(gbl_Gyroskop);

		JLabel lblX_2 = new JLabel(Messages.getString("Dimension.X")); //$NON-NLS-1$
		GridBagConstraints gbc_lblX_2 = new GridBagConstraints();
		gbc_lblX_2.anchor = GridBagConstraints.WEST;
		gbc_lblX_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_2.gridx = 0;
		gbc_lblX_2.gridy = 0;
		Gyroskop.add(lblX_2, gbc_lblX_2);

		gyr_X = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_gyr_X = new GridBagConstraints();
		gbc_gyr_X.anchor = GridBagConstraints.EAST;
		gbc_gyr_X.insets = new Insets(0, 0, 5, 0);
		gbc_gyr_X.gridx = 1;
		gbc_gyr_X.gridy = 0;
		Gyroskop.add(gyr_X, gbc_gyr_X);
		gyr_X.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblY_2 = new JLabel(Messages.getString("Dimension.Y")); //$NON-NLS-1$
		GridBagConstraints gbc_lblY_2 = new GridBagConstraints();
		gbc_lblY_2.anchor = GridBagConstraints.WEST;
		gbc_lblY_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblY_2.gridx = 0;
		gbc_lblY_2.gridy = 1;
		Gyroskop.add(lblY_2, gbc_lblY_2);

		gyr_Y = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_gyr_Y = new GridBagConstraints();
		gbc_gyr_Y.anchor = GridBagConstraints.EAST;
		gbc_gyr_Y.insets = new Insets(0, 0, 5, 0);
		gbc_gyr_Y.gridx = 1;
		gbc_gyr_Y.gridy = 1;
		Gyroskop.add(gyr_Y, gbc_gyr_Y);
		gyr_Y.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblZ_2 = new JLabel(Messages.getString("Dimension.Z")); //$NON-NLS-1$
		GridBagConstraints gbc_lblZ_2 = new GridBagConstraints();
		gbc_lblZ_2.anchor = GridBagConstraints.WEST;
		gbc_lblZ_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblZ_2.gridx = 0;
		gbc_lblZ_2.gridy = 2;
		Gyroskop.add(lblZ_2, gbc_lblZ_2);

		gyr_Z = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_gyr_Z = new GridBagConstraints();
		gbc_gyr_Z.anchor = GridBagConstraints.EAST;
		gbc_gyr_Z.gridx = 1;
		gbc_gyr_Z.gridy = 2;
		Gyroskop.add(gyr_Z, gbc_gyr_Z);
		gyr_Z.setHorizontalAlignment(SwingConstants.CENTER);

		/*
		 * GridBagConstraints gbc_horizon = new GridBagConstraints();
		 * gbc_horizon.fill = GridBagConstraints.BOTH; gbc_horizon.gridheight =
		 * 11; gbc_horizon.gridwidth = 6; gbc_horizon.insets = new Insets(0, 0,
		 * 5, 5); gbc_horizon.gridx = 7; gbc_horizon.gridy = 9;
		 * horizon.setToolTipText("Künstlicher Horizont");
		 * horizon.setBorder(null); panel.add(horizon, gbc_horizon);
		 */

		String QFE = new WebValue().getWebValue(Messages.getString("Weather.URL"), //$NON-NLS-1$
				Messages.getString("Weather.Selector")); //$NON-NLS-1$

		Filter = new JPanel();
		Filter.setBorder(new TitledBorder(null, Messages.getString("Title.Filter"), TitledBorder.LEADING, //$NON-NLS-1$
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Filter = new GridBagConstraints();
		gbc_Filter.gridheight = 2;
		gbc_Filter.insets = new Insets(0, 0, 5, 5);
		gbc_Filter.fill = GridBagConstraints.BOTH;
		gbc_Filter.gridx = 1;
		gbc_Filter.gridy = 2;
		panel.add(Filter, gbc_Filter);
		GridBagLayout gbl_Filter = new GridBagLayout();
		gbl_Filter.columnWidths = new int[] { 53, 0, 53, 0 };
		gbl_Filter.rowHeights = new int[] { 24, 24, 0, 0 };
		gbl_Filter.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Filter.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Filter.setLayout(gbl_Filter);

		lblKomplementr = new JLabel(Messages.getString("Title.Complement")); //$NON-NLS-1$
		GridBagConstraints gbc_lblKomplementr = new GridBagConstraints();
		gbc_lblKomplementr.insets = new Insets(0, 0, 5, 5);
		gbc_lblKomplementr.gridx = 1;
		gbc_lblKomplementr.gridy = 0;
		Filter.add(lblKomplementr, gbc_lblKomplementr);

		lblKalman = new JLabel(Messages.getString("Filter.Kalman")); //$NON-NLS-1$
		GridBagConstraints gbc_lblKalman = new GridBagConstraints();
		gbc_lblKalman.insets = new Insets(0, 0, 5, 0);
		gbc_lblKalman.gridx = 2;
		gbc_lblKalman.gridy = 0;
		Filter.add(lblKalman, gbc_lblKalman);

		lblRoll_2 = new JLabel(Messages.getString("Movement.Roll")); //$NON-NLS-1$
		GridBagConstraints gbc_lblRoll_2 = new GridBagConstraints();
		gbc_lblRoll_2.anchor = GridBagConstraints.WEST;
		gbc_lblRoll_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoll_2.gridx = 0;
		gbc_lblRoll_2.gridy = 1;
		Filter.add(lblRoll_2, gbc_lblRoll_2);
		lblRoll_2.setHorizontalAlignment(SwingConstants.LEFT);

		compRoll = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_compRoll = new GridBagConstraints();
		gbc_compRoll.insets = new Insets(0, 0, 5, 5);
		gbc_compRoll.gridx = 1;
		gbc_compRoll.gridy = 1;
		Filter.add(compRoll, gbc_compRoll);

		calRoll = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_calRoll = new GridBagConstraints();
		gbc_calRoll.insets = new Insets(0, 0, 5, 0);
		gbc_calRoll.gridx = 2;
		gbc_calRoll.gridy = 1;
		Filter.add(calRoll, gbc_calRoll);

		lblPitch_2 = new JLabel(Messages.getString("Movement.Pitch")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPitch_2 = new GridBagConstraints();
		gbc_lblPitch_2.anchor = GridBagConstraints.WEST;
		gbc_lblPitch_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblPitch_2.gridx = 0;
		gbc_lblPitch_2.gridy = 2;
		Filter.add(lblPitch_2, gbc_lblPitch_2);
		lblPitch_2.setHorizontalAlignment(SwingConstants.LEFT);

		compPitch = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_compPitch = new GridBagConstraints();
		gbc_compPitch.insets = new Insets(0, 0, 0, 5);
		gbc_compPitch.gridx = 1;
		gbc_compPitch.gridy = 2;
		Filter.add(compPitch, gbc_compPitch);

		calPitch = new JLabel(Messages.getString("Sonderzeichen.NA")); //$NON-NLS-1$
		GridBagConstraints gbc_calPitch = new GridBagConstraints();
		gbc_calPitch.gridx = 2;
		gbc_calPitch.gridy = 2;
		Filter.add(calPitch, gbc_calPitch);

		Motorstatus = new JPanel();
		Motorstatus.setBorder(new TitledBorder(null, Messages.getString("Title.Motors"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Motorstatus = new GridBagConstraints();
		gbc_Motorstatus.gridheight = 2;
		gbc_Motorstatus.fill = GridBagConstraints.BOTH;
		gbc_Motorstatus.insets = new Insets(0, 0, 5, 5);
		gbc_Motorstatus.gridx = 2;
		gbc_Motorstatus.gridy = 2;
		panel.add(Motorstatus, gbc_Motorstatus);
		GridBagLayout gbl_Motorstatus = new GridBagLayout();
		gbl_Motorstatus.columnWidths = new int[] { 86, 114, 0 };
		gbl_Motorstatus.rowHeights = new int[] { 0, 14, 14, 14, 14, 17, 0 };
		gbl_Motorstatus.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Motorstatus.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Motorstatus.setLayout(gbl_Motorstatus);

		chckbxMotorenAn = new JCheckBox(Messages.getString("MotorSwitchOn")); //$NON-NLS-1$
		GridBagConstraints gbc_chckbxMotorenAn = new GridBagConstraints();
		gbc_chckbxMotorenAn.gridwidth = 2;
		gbc_chckbxMotorenAn.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxMotorenAn.gridx = 0;
		gbc_chckbxMotorenAn.gridy = 0;
		Motorstatus.add(chckbxMotorenAn, gbc_chckbxMotorenAn);

		lblSchub = new JLabel(Messages.getString("Label.Gas")); //$NON-NLS-1$
		GridBagConstraints gbc_lblSchub = new GridBagConstraints();
		gbc_lblSchub.fill = GridBagConstraints.BOTH;
		gbc_lblSchub.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchub.gridx = 0;
		gbc_lblSchub.gridy = 1;
		Motorstatus.add(lblSchub, gbc_lblSchub);

		progressBar_Schub = new JProgressBar();
		GridBagConstraints gbc_progressBar_Schub = new GridBagConstraints();
		gbc_progressBar_Schub.fill = GridBagConstraints.BOTH;
		gbc_progressBar_Schub.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_Schub.gridx = 1;
		gbc_progressBar_Schub.gridy = 1;
		Motorstatus.add(progressBar_Schub, gbc_progressBar_Schub);

		lblMotor1 = new JLabel(Messages.getString("Label.Motor1")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMotor1 = new GridBagConstraints();
		gbc_lblMotor1.fill = GridBagConstraints.BOTH;
		gbc_lblMotor1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotor1.gridx = 0;
		gbc_lblMotor1.gridy = 2;
		Motorstatus.add(lblMotor1, gbc_lblMotor1);

		progressBar_Motor1 = new JProgressBar();
		progressBar_Motor1.setMaximum(1880);
		progressBar_Motor1.setMinimum(1060);
		GridBagConstraints gbc_progressBar_Motor1 = new GridBagConstraints();
		gbc_progressBar_Motor1.fill = GridBagConstraints.BOTH;
		gbc_progressBar_Motor1.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_Motor1.gridx = 1;
		gbc_progressBar_Motor1.gridy = 2;
		Motorstatus.add(progressBar_Motor1, gbc_progressBar_Motor1);

		lblMotor2 = new JLabel(Messages.getString("Label.Motor2")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMotor2 = new GridBagConstraints();
		gbc_lblMotor2.fill = GridBagConstraints.BOTH;
		gbc_lblMotor2.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotor2.gridx = 0;
		gbc_lblMotor2.gridy = 3;
		Motorstatus.add(lblMotor2, gbc_lblMotor2);

		progressBar_Motor2 = new JProgressBar();
		progressBar_Motor2.setMaximum(1880);
		progressBar_Motor2.setMinimum(1060);
		GridBagConstraints gbc_progressBar_Motor2 = new GridBagConstraints();
		gbc_progressBar_Motor2.fill = GridBagConstraints.BOTH;
		gbc_progressBar_Motor2.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_Motor2.gridx = 1;
		gbc_progressBar_Motor2.gridy = 3;
		Motorstatus.add(progressBar_Motor2, gbc_progressBar_Motor2);

		lblMotor3 = new JLabel(Messages.getString("Label.Motor3")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMotor3 = new GridBagConstraints();
		gbc_lblMotor3.fill = GridBagConstraints.BOTH;
		gbc_lblMotor3.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotor3.gridx = 0;
		gbc_lblMotor3.gridy = 4;
		Motorstatus.add(lblMotor3, gbc_lblMotor3);

		progressBar_Motor3 = new JProgressBar();
		progressBar_Motor3.setMaximum(1880);
		progressBar_Motor3.setMinimum(1060);
		GridBagConstraints gbc_progressBar_Motor3 = new GridBagConstraints();
		gbc_progressBar_Motor3.fill = GridBagConstraints.BOTH;
		gbc_progressBar_Motor3.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_Motor3.gridx = 1;
		gbc_progressBar_Motor3.gridy = 4;
		Motorstatus.add(progressBar_Motor3, gbc_progressBar_Motor3);

		lblMotor4 = new JLabel(Messages.getString("Label.Motor4")); //$NON-NLS-1$
		GridBagConstraints gbc_lblMotor4 = new GridBagConstraints();
		gbc_lblMotor4.fill = GridBagConstraints.BOTH;
		gbc_lblMotor4.insets = new Insets(0, 0, 0, 5);
		gbc_lblMotor4.gridx = 0;
		gbc_lblMotor4.gridy = 5;
		Motorstatus.add(lblMotor4, gbc_lblMotor4);

		progressBar_Motor4 = new JProgressBar();
		progressBar_Motor4.setMaximum(1880);
		progressBar_Motor4.setMinimum(1060);
		GridBagConstraints gbc_progressBar_Motor4 = new GridBagConstraints();
		gbc_progressBar_Motor4.fill = GridBagConstraints.BOTH;
		gbc_progressBar_Motor4.gridx = 1;
		gbc_progressBar_Motor4.gridy = 5;
		Motorstatus.add(progressBar_Motor4, gbc_progressBar_Motor4);

		Horizonfilter = new JPanel();
		Horizonfilter.setBorder(new TitledBorder(null, Messages.getString("Title.Horizonfilter"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Horizonfilter = new GridBagConstraints();
		gbc_Horizonfilter.insets = new Insets(0, 0, 5, 0);
		gbc_Horizonfilter.fill = GridBagConstraints.VERTICAL;
		gbc_Horizonfilter.gridx = 3;
		gbc_Horizonfilter.gridy = 2;
		panel.add(Horizonfilter, gbc_Horizonfilter);
		GridBagLayout gbl_Horizonfilter = new GridBagLayout();
		gbl_Horizonfilter.columnWidths = new int[] { 49, 53, 108, 0 };
		gbl_Horizonfilter.rowHeights = new int[] { 24, 0 };
		gbl_Horizonfilter.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Horizonfilter.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		Horizonfilter.setLayout(gbl_Horizonfilter);

		rdbtnRoh = new JRadioButton(Messages.getString("Filter.Raw")); //$NON-NLS-1$
		GridBagConstraints gbc_rdbtnRoh = new GridBagConstraints();
		gbc_rdbtnRoh.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnRoh.gridx = 0;
		gbc_rdbtnRoh.gridy = 0;
		Horizonfilter.add(rdbtnRoh, gbc_rdbtnRoh);
		buttonGroup_horizonFilter.add(rdbtnRoh);

		rdbtnComplement = new JRadioButton(Messages.getString("Title.Complement")); //$NON-NLS-1$
		GridBagConstraints gbc_rdbtnComplement = new GridBagConstraints();
		gbc_rdbtnComplement.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnComplement.gridx = 1;
		gbc_rdbtnComplement.gridy = 0;
		Horizonfilter.add(rdbtnComplement, gbc_rdbtnComplement);

		buttonGroup_horizonFilter.add(rdbtnComplement);

		rdbtnKalman = new JRadioButton(Messages.getString("Filter.Kalman")); //$NON-NLS-1$
		GridBagConstraints gbc_rdbtnKalman = new GridBagConstraints();
		gbc_rdbtnKalman.gridx = 2;
		gbc_rdbtnKalman.gridy = 0;
		Horizonfilter.add(rdbtnKalman, gbc_rdbtnKalman);
		rdbtnKalman.setSelected(true);
		buttonGroup_horizonFilter.add(rdbtnKalman);

		Weather = new JPanel();
		Weather.setBorder(new TitledBorder(null, Messages.getString("Title.Weather"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Weather = new GridBagConstraints();
		gbc_Weather.insets = new Insets(0, 0, 5, 0);
		gbc_Weather.fill = GridBagConstraints.BOTH;
		gbc_Weather.gridx = 3;
		gbc_Weather.gridy = 3;
		panel.add(Weather, gbc_Weather);
		GridBagLayout gbl_Weather = new GridBagLayout();
		gbl_Weather.columnWidths = new int[] { 49, 53, 0 };
		gbl_Weather.rowHeights = new int[] { 24, 0 };
		gbl_Weather.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Weather.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		Weather.setLayout(gbl_Weather);

		lblLuftdruckNnFr = new JLabel(Messages.getString("Weather.Baro")); //$NON-NLS-1$
		GridBagConstraints gbc_lblLuftdruckNnFr = new GridBagConstraints();
		gbc_lblLuftdruckNnFr.anchor = GridBagConstraints.WEST;
		gbc_lblLuftdruckNnFr.insets = new Insets(0, 0, 0, 5);
		gbc_lblLuftdruckNnFr.gridx = 0;
		gbc_lblLuftdruckNnFr.gridy = 0;
		Weather.add(lblLuftdruckNnFr, gbc_lblLuftdruckNnFr);
		lblNN = new JLabel(QFE.substring(1, QFE.length() - 1));
		GridBagConstraints gbc_lblNN = new GridBagConstraints();
		gbc_lblNN.gridx = 1;
		gbc_lblNN.gridy = 0;
		Weather.add(lblNN, gbc_lblNN);

		PID_Regler = new JPanel();
		PID_Regler.setBorder(new TitledBorder(null, Messages.getString("Title.PID"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_PID_Regler = new GridBagConstraints();
		gbc_PID_Regler.gridwidth = 2;
		gbc_PID_Regler.insets = new Insets(0, 0, 0, 5);
		gbc_PID_Regler.fill = GridBagConstraints.BOTH;
		gbc_PID_Regler.gridx = 0;
		gbc_PID_Regler.gridy = 4;
		panel.add(PID_Regler, gbc_PID_Regler);
		GridBagLayout gbl_PID_Regler = new GridBagLayout();
		gbl_PID_Regler.columnWidths = new int[] { 515, 0 };
		gbl_PID_Regler.rowHeights = new int[] { 14, 14, 0 };
		gbl_PID_Regler.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_PID_Regler.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		PID_Regler.setLayout(gbl_PID_Regler);

		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		PID_Regler.add(panel_1, gbc_panel_1);
		panel_1.setBorder(new TitledBorder(null, Messages.getString("PID.Fine"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 49, 53, 233, 0 };
		gbl_panel_1.rowHeights = new int[] { 24, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		lblPAnteil = new JLabel(Messages.getString("Label.P")); //$NON-NLS-1$
		GridBagConstraints gbc_lblPAnteil = new GridBagConstraints();
		gbc_lblPAnteil.insets = new Insets(0, 0, 5, 5);
		gbc_lblPAnteil.gridx = 0;
		gbc_lblPAnteil.gridy = 0;
		panel_1.add(lblPAnteil, gbc_lblPAnteil);
		lblPAnteil.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$

		slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		panel_1.add(slider, gbc_slider);
		slider.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$

		progressBar_2 = new JProgressBar();
		GridBagConstraints gbc_progressBar_2 = new GridBagConstraints();
		gbc_progressBar_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_2.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_2.gridx = 2;
		gbc_progressBar_2.gridy = 0;
		panel_1.add(progressBar_2, gbc_progressBar_2);
		progressBar_2.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$

		lblIAnteil = new JLabel(Messages.getString("Label.I")); //$NON-NLS-1$
		GridBagConstraints gbc_lblIAnteil = new GridBagConstraints();
		gbc_lblIAnteil.insets = new Insets(0, 0, 5, 5);
		gbc_lblIAnteil.gridx = 0;
		gbc_lblIAnteil.gridy = 1;
		panel_1.add(lblIAnteil, gbc_lblIAnteil);
		lblIAnteil.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$

		slider_1 = new JSlider();
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.insets = new Insets(0, 0, 5, 5);
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 1;
		panel_1.add(slider_1, gbc_slider_1);
		slider_1.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$

		progressBar_3 = new JProgressBar();
		GridBagConstraints gbc_progressBar_3 = new GridBagConstraints();
		gbc_progressBar_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_3.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_3.gridx = 2;
		gbc_progressBar_3.gridy = 1;
		panel_1.add(progressBar_3, gbc_progressBar_3);
		progressBar_3.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$
		progressBar_3.setMinimum(1060);
		progressBar_3.setMaximum(1880);

		lblDAnteil = new JLabel(Messages.getString("Label.D")); //$NON-NLS-1$
		GridBagConstraints gbc_lblDAnteil = new GridBagConstraints();
		gbc_lblDAnteil.insets = new Insets(0, 0, 0, 5);
		gbc_lblDAnteil.gridx = 0;
		gbc_lblDAnteil.gridy = 2;
		panel_1.add(lblDAnteil, gbc_lblDAnteil);
		lblDAnteil.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$

		slider_2 = new JSlider();
		GridBagConstraints gbc_slider_2 = new GridBagConstraints();
		gbc_slider_2.insets = new Insets(0, 0, 0, 5);
		gbc_slider_2.gridx = 1;
		gbc_slider_2.gridy = 2;
		panel_1.add(slider_2, gbc_slider_2);
		slider_2.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$

		progressBar_4 = new JProgressBar();
		GridBagConstraints gbc_progressBar_4 = new GridBagConstraints();
		gbc_progressBar_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_4.gridx = 2;
		gbc_progressBar_4.gridy = 2;
		panel_1.add(progressBar_4, gbc_progressBar_4);
		progressBar_4.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$
		progressBar_4.setMinimum(1060);
		progressBar_4.setMaximum(1880);

		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		PID_Regler.add(panel_2, gbc_panel_2);
		panel_2.setBorder(new TitledBorder(null, Messages.getString("PID.Aggressive"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 49, 53, 233, 0 };
		gbl_panel_2.rowHeights = new int[] { 24, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		label = new JLabel(Messages.getString("Label.P")); //$NON-NLS-1$
		label.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);

		slider_6 = new JSlider();
		slider_6.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_slider_6 = new GridBagConstraints();
		gbc_slider_6.insets = new Insets(0, 0, 5, 5);
		gbc_slider_6.gridx = 1;
		gbc_slider_6.gridy = 0;
		panel_2.add(slider_6, gbc_slider_6);

		progressBar_8 = new JProgressBar();
		progressBar_8.setToolTipText(Messages.getString("Infotext.P-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_progressBar_8 = new GridBagConstraints();
		gbc_progressBar_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_8.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_8.gridx = 2;
		gbc_progressBar_8.gridy = 0;
		panel_2.add(progressBar_8, gbc_progressBar_8);

		label_1 = new JLabel(Messages.getString("Label.I")); //$NON-NLS-1$
		label_1.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel_2.add(label_1, gbc_label_1);

		slider_7 = new JSlider();
		slider_7.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_slider_7 = new GridBagConstraints();
		gbc_slider_7.insets = new Insets(0, 0, 5, 5);
		gbc_slider_7.gridx = 1;
		gbc_slider_7.gridy = 1;
		panel_2.add(slider_7, gbc_slider_7);

		progressBar_9 = new JProgressBar();
		progressBar_9.setToolTipText(Messages.getString("Infotext.I-Regler")); //$NON-NLS-1$
		progressBar_9.setMinimum(1060);
		progressBar_9.setMaximum(1880);
		GridBagConstraints gbc_progressBar_9 = new GridBagConstraints();
		gbc_progressBar_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_9.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_9.gridx = 2;
		gbc_progressBar_9.gridy = 1;
		panel_2.add(progressBar_9, gbc_progressBar_9);

		label_2 = new JLabel(Messages.getString("Label.D")); //$NON-NLS-1$
		label_2.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel_2.add(label_2, gbc_label_2);

		slider_8 = new JSlider();
		slider_8.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$
		GridBagConstraints gbc_slider_8 = new GridBagConstraints();
		gbc_slider_8.insets = new Insets(0, 0, 0, 5);
		gbc_slider_8.gridx = 1;
		gbc_slider_8.gridy = 2;
		panel_2.add(slider_8, gbc_slider_8);

		progressBar_10 = new JProgressBar();
		progressBar_10.setToolTipText(Messages.getString("Infotext.D-Regler")); //$NON-NLS-1$
		progressBar_10.setMinimum(1060);
		progressBar_10.setMaximum(1880);
		GridBagConstraints gbc_progressBar_10 = new GridBagConstraints();
		gbc_progressBar_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_10.gridx = 2;
		gbc_progressBar_10.gridy = 2;
		panel_2.add(progressBar_10, gbc_progressBar_10);

		JDesktopPane tab_SerialMon = new JDesktopPane();
		tabbedPane.addTab(Messages.getString("Tab.Title.SerialMonitor"), null, tab_SerialMon, null); //$NON-NLS-1$
		tab_SerialMon.setLayout(new BorderLayout(0, 0));

		JPanel serial_Controls = new JPanel();
		serial_Controls.setToolTipText(Messages.getString("Infotext.Controls")); //$NON-NLS-1$
		tab_SerialMon.add(serial_Controls, BorderLayout.NORTH);
		GridBagLayout gbl_serial_Controls = new GridBagLayout();
		gbl_serial_Controls.columnWidths = new int[] { 115, 0, 110, 0, 0, 0, 0, 0 };
		gbl_serial_Controls.rowHeights = new int[] { 25, 0, 0 };
		gbl_serial_Controls.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_serial_Controls.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		serial_Controls.setLayout(gbl_serial_Controls);

		serial_Device = new JComboBox<>();
		serial_Device.setToolTipText(Messages.getString("Infotext.PortSelection")); //$NON-NLS-1$
		GridBagConstraints gbc_serial_Device = new GridBagConstraints();
		gbc_serial_Device.gridwidth = 2;
		gbc_serial_Device.fill = GridBagConstraints.HORIZONTAL;
		gbc_serial_Device.insets = new Insets(0, 0, 5, 5);
		gbc_serial_Device.gridx = 0;
		gbc_serial_Device.gridy = 0;
		serial_Controls.add(serial_Device, gbc_serial_Device);

		JButton btn_Disconnect = new JButton(Messages.getString("Menu.Disconnect")); //$NON-NLS-1$
		btn_Disconnect.setToolTipText(Messages.getString("Infotext.Disconnect")); //$NON-NLS-1$

		JButton btn_Connect = new JButton(Messages.getString("Menu.Connect")); //$NON-NLS-1$
		btn_Connect.setToolTipText(Messages.getString("Infotext.Connect")); //$NON-NLS-1$

		GridBagConstraints gbc_btn_Connect = new GridBagConstraints();
		gbc_btn_Connect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_Connect.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Connect.gridx = 2;
		gbc_btn_Connect.gridy = 0;
		serial_Controls.add(btn_Connect, gbc_btn_Connect);
		GridBagConstraints gbc_btn_Disconnect = new GridBagConstraints();
		gbc_btn_Disconnect.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_Disconnect.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Disconnect.gridx = 3;
		gbc_btn_Disconnect.gridy = 0;
		serial_Controls.add(btn_Disconnect, gbc_btn_Disconnect);

		JButton btn_Refresh = new JButton(Messages.getString("Button.Refresh")); //$NON-NLS-1$
		btn_Refresh.setToolTipText(Messages.getString("Infotext.Refresh")); //$NON-NLS-1$

		GridBagConstraints gbc_btn_Refresh = new GridBagConstraints();
		gbc_btn_Refresh.gridwidth = 3;
		gbc_btn_Refresh.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_Refresh.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Refresh.gridx = 4;
		gbc_btn_Refresh.gridy = 0;
		serial_Controls.add(btn_Refresh, gbc_btn_Refresh);

		JButton btn_Send_Message = new JButton(Messages.getString("Button.Send")); //$NON-NLS-1$
		btn_Send_Message.setToolTipText(Messages.getString("Infotext.Send")); //$NON-NLS-1$

		GridBagConstraints gbc_btn_Send_Message = new GridBagConstraints();
		gbc_btn_Send_Message.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_Send_Message.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Send_Message.gridx = 0;
		gbc_btn_Send_Message.gridy = 1;
		serial_Controls.add(btn_Send_Message, gbc_btn_Send_Message);

		txtFld_Input = new JTextField();
		txtFld_Input.setToolTipText(Messages.getString("Infotext.Input")); //$NON-NLS-1$
		GridBagConstraints gbc_txtFld_Input = new GridBagConstraints();
		gbc_txtFld_Input.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_Input.insets = new Insets(0, 0, 0, 5);
		gbc_txtFld_Input.gridwidth = 3;
		gbc_txtFld_Input.gridx = 1;
		gbc_txtFld_Input.gridy = 1;
		serial_Controls.add(txtFld_Input, gbc_txtFld_Input);
		txtFld_Input.setColumns(10);
		txtFld_Input.addActionListener(new ActionListener_send());

		chckbx_Echo = new JCheckBox(Messages.getString("Radio.Echo")); //$NON-NLS-1$
		chckbx_Echo.setToolTipText(Messages.getString("Infotext.Echo")); //$NON-NLS-1$
		GridBagConstraints gbc_chckbxEcho = new GridBagConstraints();
		gbc_chckbxEcho.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxEcho.gridx = 4;
		gbc_chckbxEcho.gridy = 1;
		serial_Controls.add(chckbx_Echo, gbc_chckbxEcho);

		chckbx_Log = new JCheckBox(Messages.getString("Radio.Log")); //$NON-NLS-1$
		chckbx_Log.setToolTipText(Messages.getString("Infotext.Log")); //$NON-NLS-1$
		GridBagConstraints gbc_chckBx_Log = new GridBagConstraints();
		gbc_chckBx_Log.insets = new Insets(0, 0, 0, 5);
		gbc_chckBx_Log.gridx = 5;
		gbc_chckBx_Log.gridy = 1;
		serial_Controls.add(chckbx_Log, gbc_chckBx_Log);

		chckbx_Autoscroll = new JCheckBox(Messages.getString("Radio.Autoscroll")); //$NON-NLS-1$
		chckbx_Autoscroll.setToolTipText(Messages.getString("Infotext.Autoscroll")); //$NON-NLS-1$
		chckbx_Autoscroll.setSelected(true);
		GridBagConstraints gbc_chckbxAutoscroll = new GridBagConstraints();
		gbc_chckbxAutoscroll.gridx = 6;
		gbc_chckbxAutoscroll.gridy = 1;
		serial_Controls.add(chckbx_Autoscroll, gbc_chckbxAutoscroll);

		JScrollPane serial_Mon = new JScrollPane();
		tab_SerialMon.add(serial_Mon, BorderLayout.CENTER);

		txtArea_Output = new JTextArea();
		txtArea_Output.setToolTipText(Messages.getString("Infotext.Editor")); //$NON-NLS-1$
		txtArea_Output.setEditable(false);
		serial_Mon.setViewportView(txtArea_Output);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(txtArea_Output, popupMenu);

		JMenuItem mntmMarkieren = new JMenuItem(Messages.getString("Mouse.Mark")); //$NON-NLS-1$
		popupMenu.add(mntmMarkieren);

		JMenuItem mntmKopieren = new JMenuItem(Messages.getString("Mouse.Copy")); //$NON-NLS-1$
		popupMenu.add(mntmKopieren);

		JMenuItem mntmLoeschen = new JMenuItem(Messages.getString("Mouse.Delete")); //$NON-NLS-1$
		popupMenu.add(mntmLoeschen);

		tab_Joystick = new JDesktopPane();
		tabbedPane.addTab(Messages.getString("Tab.Title.Joystick"), null, tab_Joystick, null); //$NON-NLS-1$
		GridBagLayout gbl_tab_Joystick = new GridBagLayout();
		gbl_tab_Joystick.columnWidths = new int[] { 0, 0 };
		gbl_tab_Joystick.rowHeights = new int[] { 0, 0 };
		gbl_tab_Joystick.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_tab_Joystick.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		tab_Joystick.setLayout(gbl_tab_Joystick);

		JPanel Joystick = new JPanel();
		GridBagConstraints gbc_Joystick = new GridBagConstraints();
		gbc_Joystick.fill = GridBagConstraints.BOTH;
		gbc_Joystick.gridx = 0;
		gbc_Joystick.gridy = 0;
		tab_Joystick.add(Joystick, gbc_Joystick);
		GridBagLayout gbl_Joystick = new GridBagLayout();
		gbl_Joystick.columnWidths = new int[] { 0, 0, 0 };
		gbl_Joystick.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_Joystick.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_Joystick.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		Joystick.setLayout(gbl_Joystick);

		JPanel Controller = new JPanel();
		Controller.setBorder(new TitledBorder(null, Messages.getString("Title.Joystick"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Controller = new GridBagConstraints();
		gbc_Controller.gridwidth = 2;
		gbc_Controller.insets = new Insets(0, 0, 5, 0);
		gbc_Controller.fill = GridBagConstraints.HORIZONTAL;
		gbc_Controller.gridx = 0;
		gbc_Controller.gridy = 0;
		Joystick.add(Controller, gbc_Controller);
		GridBagLayout gbl_Controller = new GridBagLayout();
		gbl_Controller.columnWidths = new int[] { 459, 484, 0 };
		gbl_Controller.rowHeights = new int[] { 20, 0 };
		gbl_Controller.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Controller.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		Controller.setLayout(gbl_Controller);

		jComboBox_controllers = new JComboBox<String>();
		GridBagConstraints gbc_jComboBox_controllers = new GridBagConstraints();
		gbc_jComboBox_controllers.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBox_controllers.gridwidth = 2;
		gbc_jComboBox_controllers.gridx = 0;
		gbc_jComboBox_controllers.gridy = 0;
		Controller.add(jComboBox_controllers, gbc_jComboBox_controllers);

		Axes = new JPanel();
		Axes.setBorder(new TitledBorder(null, Messages.getString("Title.Axes"), TitledBorder.LEADING, //$NON-NLS-1$
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Axes = new GridBagConstraints();
		gbc_Axes.gridwidth = 2;
		gbc_Axes.insets = new Insets(0, 0, 5, 0);
		gbc_Axes.fill = GridBagConstraints.BOTH;
		gbc_Axes.gridx = 0;
		gbc_Axes.gridy = 1;
		Joystick.add(Axes, gbc_Axes);
		GridBagLayout gbl_Axes = new GridBagLayout();
		gbl_Axes.columnWidths = new int[] { 100, 146, 146, 0 };
		gbl_Axes.rowHeights = new int[] { 0, 100, 0, 0, 0, 0 };
		gbl_Axes.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Axes.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		Axes.setLayout(gbl_Axes);

		lblXyAxes = new JLabel(Messages.getString("Joystick.XY")); //$NON-NLS-1$
		GridBagConstraints gbc_lblXyAxes = new GridBagConstraints();
		gbc_lblXyAxes.insets = new Insets(0, 0, 5, 5);
		gbc_lblXyAxes.gridx = 0;
		gbc_lblXyAxes.gridy = 0;
		Axes.add(lblXyAxes, gbc_lblXyAxes);

		XY_Axes = new JPanel();
		XY_Axes.setBorder(new LineBorder(new Color(0, 0, 0)));
		XY_Axes.setLayout(null);
		GridBagConstraints gbc_XY_Axes = new GridBagConstraints();
		gbc_XY_Axes.insets = new Insets(0, 0, 5, 5);
		gbc_XY_Axes.gridx = 0;
		gbc_XY_Axes.gridy = 1;
		Axes.add(XY_Axes, gbc_XY_Axes);

		for_Axes = new JPanel();
		for_Axes.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_for_Axes = new GridBagConstraints();
		gbc_for_Axes.insets = new Insets(0, 0, 5, 5);
		gbc_for_Axes.fill = GridBagConstraints.BOTH;
		gbc_for_Axes.gridx = 1;
		gbc_for_Axes.gridy = 1;
		Axes.add(for_Axes, gbc_for_Axes);
		GridBagLayout gbl_for_Axes = new GridBagLayout();
		gbl_for_Axes.columnWidths = new int[] { 0, 0 };
		gbl_for_Axes.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_for_Axes.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_for_Axes.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		for_Axes.setLayout(gbl_for_Axes);

		lblZrotation = new JLabel(Messages.getString("Joystick.Z")); //$NON-NLS-1$
		GridBagConstraints gbc_lblZrotation = new GridBagConstraints();
		gbc_lblZrotation.anchor = GridBagConstraints.WEST;
		gbc_lblZrotation.insets = new Insets(0, 0, 5, 0);
		gbc_lblZrotation.gridx = 0;
		gbc_lblZrotation.gridy = 0;
		for_Axes.add(lblZrotation, gbc_lblZrotation);

		progressBar_1 = new JProgressBar();
		GridBagConstraints gbc_progressBar_1 = new GridBagConstraints();
		gbc_progressBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar_1.gridx = 0;
		gbc_progressBar_1.gridy = 1;
		for_Axes.add(progressBar_1, gbc_progressBar_1);

		lblSchieberegler = new JLabel(Messages.getString("Joystick.Slider")); //$NON-NLS-1$
		GridBagConstraints gbc_lblSchieberegler = new GridBagConstraints();
		gbc_lblSchieberegler.anchor = GridBagConstraints.WEST;
		gbc_lblSchieberegler.insets = new Insets(0, 0, 5, 0);
		gbc_lblSchieberegler.gridx = 0;
		gbc_lblSchieberegler.gridy = 2;
		for_Axes.add(lblSchieberegler, gbc_lblSchieberegler);

		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 3;
		for_Axes.add(progressBar, gbc_progressBar);

		buttons = new JPanel();
		buttons.setBorder(new TitledBorder(null, Messages.getString("Title.Buttons"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Buttons = new GridBagConstraints();
		gbc_Buttons.insets = new Insets(0, 0, 0, 5);
		gbc_Buttons.fill = GridBagConstraints.BOTH;
		gbc_Buttons.gridx = 0;
		gbc_Buttons.gridy = 2;
		Joystick.add(buttons, gbc_Buttons);

		hat_Switch = new JPanel();
		hat_Switch.setBorder(new TitledBorder(null, Messages.getString("Title.Hat"), //$NON-NLS-1$
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_Hat_Switch = new GridBagConstraints();
		gbc_Hat_Switch.fill = GridBagConstraints.BOTH;
		gbc_Hat_Switch.gridx = 1;
		gbc_Hat_Switch.gridy = 2;
		Joystick.add(hat_Switch, gbc_Hat_Switch);
		mntmLoeschen.addActionListener(new ActionListener_clrLog());
		btn_Connect.addActionListener(new ActionListener_connect());
		btn_Disconnect.addActionListener(new ActionListener_disconnect());
		btn_Refresh.addActionListener(new ActionListener_refresh());
		btn_Send_Message.addActionListener(new ActionListener_send());

		JPanel Controls = new JPanel();
		GridBagLayout gbl_Magnetometer_11 = new GridBagLayout();
		gbl_Magnetometer_11.columnWidths = new int[] { 82, 82, 82, 0 };
		gbl_Magnetometer_11.rowHeights = new int[] { 25, 0, 0, 0 };
		gbl_Magnetometer_11.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_Magnetometer_11.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Controls.setLayout(gbl_Magnetometer_11);

		JButton btnNewButton_2 = new JButton(Messages.getString("Joystick.Up")); //$NON-NLS-1$
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		Controls.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton = new JButton(Messages.getString("Joystick.Left")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		Controls.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_3 = new JButton(Messages.getString("Joystick.Right")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 1;
		Controls.add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnNewButton_1 = new JButton(Messages.getString("Joystick.Down")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		Controls.add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel statusBar = new JPanel();

		getFrame().getContentPane().add(statusBar, BorderLayout.SOUTH);

		msg = new JLabel(Messages.getString("Status.Begin") + OS.getUser() //$NON-NLS-1$
				+ Messages.getString("Status.End") + OS.getOS(), SwingConstants.LEFT); //$NON-NLS-1$
		msg.setVerticalAlignment(SwingConstants.TOP);
		msg.setForeground(Color.black);
		msg.setToolTipText(Messages.getString("Infotext.Status")); //$NON-NLS-1$

		final JLabel welcomedate = new JLabel();
		welcomedate.setVerticalAlignment(SwingConstants.TOP);
		welcomedate.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomedate.setOpaque(true);// to set the color for jlabel
		welcomedate.setBackground(Color.black);
		welcomedate.setForeground(Color.WHITE);

		statusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		statusBar.setBackground(Color.LIGHT_GRAY);
		statusBar.setLayout(new BorderLayout(0, 0));
		statusBar.add(msg, BorderLayout.WEST);
		statusBar.add(welcomedate, BorderLayout.EAST);
		statusBar.add(msg, BorderLayout.WEST);
		statusBar.add(welcomedate, BorderLayout.EAST);
		// add("South", statusBar);

		mntmConnect.addActionListener(new ActionListener_connect());
		mntmDisconnect.addActionListener(new ActionListener_disconnect());
		button.addActionListener(new ActionListener_connect());
		button_1.addActionListener(new ActionListener_disconnect());
		rdbtnmntm_Baud_10.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_9.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_8.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_7.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_6.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_5.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_4.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_3.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_2.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud_1.addActionListener(new ActionListener_setBaud());
		rdbtnmntm_Baud.addActionListener(new ActionListener_setBaud());

		javax.swing.Timer timee = new javax.swing.Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date now = new java.util.Date();
				String ss = DateFormat.getDateTimeInstance().format(now);
				welcomedate.setText(ss);
				welcomedate.setToolTipText(
						Messages.getString("Infotext.Time.Begin") + ss + Messages.getString("Infotext.Time.End")); //$NON-NLS-1$ //$NON-NLS-2$
				txtArea_Output.append("test;" + Messages.getString("Infotext.Time.Begin") + ss
						+ Messages.getString("Infotext.Time.End") + "\n");
			}
		});

		timee.start();

		if (OS.isOS().contains(Messages.getString("OS.Linux"))) { //$NON-NLS-1$
			SerialrxtxCommunication.refreshSerialPort();
		}
		if (OS.isOS().equals(Messages.getString("OS.Windows"))) { //$NON-NLS-1$
			SerialjsscCommunication.refreshSerialPort();
		}
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static void setXYAxis(int xPercentage, int yPercentage) {
		// Graphics2D g2d = (Graphics2D) XY_Axes.getGraphics();
		// g2d.clearRect(1, 1, XY_Axes.getWidth() - 2, XY_Axes.getHeight() - 2);
		// g2d.fillOval(xPercentage, yPercentage, 10, 10);
	}

	public static void setControllerButtons(JPanel buttonsPanel) {
		buttons.removeAll();
		buttons.add(buttonsPanel);
		buttons.validate();
	}

	public static void setHatSwitch(float hatSwitchPosition) {
		int circleSize = 100;

		Graphics2D g2d = (Graphics2D) hat_Switch.getGraphics();
		g2d.clearRect(5, 15, hat_Switch.getWidth() - 10, hat_Switch.getHeight() - 22);
		g2d.drawOval(20, 22, circleSize, circleSize);

		if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.OFF) == 0)
			return;

		int smallCircleSize = 10;
		int upCircleX = 65;
		int upCircleY = 17;
		int leftCircleX = 15;
		int leftCircleY = 68;
		int betweenX = 37;
		int betweenY = 17;

		int x = 0;
		int y = 0;

		g2d.setColor(Color.blue);

		if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.UP) == 0) {
			x = upCircleX;
			y = upCircleY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.DOWN) == 0) {
			x = upCircleX;
			y = upCircleY + circleSize;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.LEFT) == 0) {
			x = leftCircleX;
			y = leftCircleY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.RIGHT) == 0) {
			x = leftCircleX + circleSize;
			y = leftCircleY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.UP_LEFT) == 0) {
			x = upCircleX - betweenX;
			y = upCircleY + betweenY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.UP_RIGHT) == 0) {
			x = upCircleX + betweenX;
			y = upCircleY + betweenY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.DOWN_LEFT) == 0) {
			x = upCircleX - betweenX;
			y = upCircleY + circleSize - betweenY;
		} else if (Float.compare(hatSwitchPosition, net.java.games.input.Component.POV.DOWN_RIGHT) == 0) {
			x = upCircleX + betweenX;
			y = upCircleY + circleSize - betweenY;
		}

		g2d.fillOval(x, y, smallCircleSize, smallCircleSize);
	}

	public static void addAxisPanel(javax.swing.JPanel axesPanel) {
		Axes.removeAll();
		Axes.add(axesPanel);
		Axes.validate();
	}

	@SuppressWarnings("unused")
	private void initComponents() {

		XY_Axes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("Title.Axes"), //$NON-NLS-1$
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				null, new java.awt.Color(0, 51, 204)));

		XY_Axes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		XY_Axes.setPreferredSize(new java.awt.Dimension(111, 111));

		javax.swing.GroupLayout jPanelXYAxisLayout = new javax.swing.GroupLayout(XY_Axes);
		XY_Axes.setLayout(jPanelXYAxisLayout);
		jPanelXYAxisLayout.setHorizontalGroup(jPanelXYAxisLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 109, Short.MAX_VALUE));
		jPanelXYAxisLayout.setVerticalGroup(jPanelXYAxisLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 109, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel_forAxisLayout = new javax.swing.GroupLayout(for_Axes);
		for_Axes.setLayout(jPanel_forAxisLayout);
		jPanel_forAxisLayout.setHorizontalGroup(jPanel_forAxisLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 202, Short.MAX_VALUE));
		jPanel_forAxisLayout.setVerticalGroup(jPanel_forAxisLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanelAxesLayout = new javax.swing.GroupLayout(XY_Axes);
		XY_Axes.setLayout(jPanelAxesLayout);
		jPanelAxesLayout.setHorizontalGroup(jPanelAxesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelAxesLayout.createSequentialGroup().addGroup(jPanelAxesLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelAxesLayout.createSequentialGroup().addGap(58, 58, 58).addComponent(XY_Axes))
						.addGroup(jPanelAxesLayout.createSequentialGroup().addGap(37, 37, 37).addComponent(XY_Axes,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(for_Axes, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanelAxesLayout.setVerticalGroup(jPanelAxesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelAxesLayout.createSequentialGroup().addComponent(for_Axes)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(XY_Axes, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 16, Short.MAX_VALUE))
				.addComponent(for_Axes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));

		buttons.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("Title.Buttons"), //$NON-NLS-1$
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				null, new java.awt.Color(0, 51, 204)));

		javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(buttons);
		buttons.setLayout(jPanelButtonsLayout);
		jPanelButtonsLayout.setHorizontalGroup(jPanelButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 248, Short.MAX_VALUE));
		jPanelButtonsLayout.setVerticalGroup(jPanelButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 112, Short.MAX_VALUE));

		hat_Switch.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("Title.Hat"), //$NON-NLS-1$
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				null, new java.awt.Color(0, 51, 204)));

		javax.swing.GroupLayout jPanelHatSwitchLayout = new javax.swing.GroupLayout(hat_Switch);
		hat_Switch.setLayout(jPanelHatSwitchLayout);
		jPanelHatSwitchLayout.setHorizontalGroup(jPanelHatSwitchLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 121, Short.MAX_VALUE));
		jPanelHatSwitchLayout.setVerticalGroup(jPanelHatSwitchLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		jComboBox_controllers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboBox.actionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getFrame().getContentPane());
		getFrame().getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout
								.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
												.createSequentialGroup().addComponent(
														buttons, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(hat_Switch, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(XY_Axes, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap())
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(jComboBox_controllers, javax.swing.GroupLayout.PREFERRED_SIZE,
												237, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(88, 88, 88)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jComboBox_controllers, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(XY_Axes, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(buttons, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(hat_Switch, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		// pack();
	}// </editor-fold>//GEN-END:initComponents
}
