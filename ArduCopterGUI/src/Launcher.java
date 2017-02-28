import java.awt.EventQueue;
import java.util.Locale;

import gui.ArduCopterGUI;
import net.java.games.input.Controller;
import jinputjoystick.JInputJoystick;
import jinputjoystick.Joystick;

public class Launcher {

	/**
	 * Launch the application.
	 */
	public synchronized static void main(String[] args) {
		Locale.setDefault(Locale.getDefault());
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ArduCopterGUI window = new ArduCopterGUI();
				window.getFrame().setVisible(true);
			}
		});
		new JInputJoystick().pollControllerAndItsComponents(Controller.Type.STICK);
		new Joystick();
	}

}
