package os;

public class OSValidator {
	private final String OS = System.getProperty("os.name");
	private final String User = System.getProperty("user.name");

	/**
	 * @return the oS
	 */
	public String getOS() {
		System.getProperties().list(System.out);
		return OS;
	}

	public String getUser() {
		return User;
	}

	private boolean isMac() {
		return OS.toLowerCase().indexOf("mac") >= 0;
	}

	public String isOS() {
		if (isWindows())
			return "Windows";
		else if (isMac())
			return "Macintosh";
		else if (isUnix())
			return "Unix oder Linux";
		else if (isSolaris())
			return "Solaris";
		else
			return "Unbekannt und nicht unterstützt";
	}

	private boolean isSolaris() {
		return OS.toLowerCase().indexOf("sunos") >= 0;
	}

	private boolean isUnix() {
		return OS.toLowerCase().indexOf("nix") >= 0
				|| OS.toLowerCase().indexOf("nux") >= 0
				|| OS.toLowerCase().indexOf("aix") > 0;
	}

	private boolean isWindows() {
		return OS.toLowerCase().indexOf("win") >= 0;
	}
}