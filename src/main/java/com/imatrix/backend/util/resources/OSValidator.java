package com.imatrix.backend.util.resources;

/**
 * <h1>FORKED</h1>
 * <p>
 * Checks which operating system is running this
 * application
 * </p>
 *
 * @author Domenico Monaco
 * @url   https://stackoverflow.com/questions/14288185
 *
 */
public class OSValidator {
	private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return OS.contains("sunos");
    }
    public static String getOS(){
        if (isWindows()) {
            return "win";
        } else if (isMac()) {
            return "osx";
        } else if (isUnix()) {
            return "uni";
        } else if (isSolaris()) {
            return "sol";
        } else {
            return "err";
        }
    }
}
