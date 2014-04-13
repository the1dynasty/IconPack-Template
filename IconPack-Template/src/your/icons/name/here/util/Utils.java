package your.icons.name.here.util;

import android.content.Context;
import android.content.pm.PackageManager;


/**
 * This class miscellaneous convenience methods.
 * They may be random utils you use throughout your app multiple times.
 */
public class Utils
{
	/**
	 * Checks to see if the specified package is installed.<br>
	 * Return true if it is or false if it's not installed.
	 * 
	 * @param packageName
	 * @param context
	 * @return
	 */
	public static boolean isPackageInstalled(String packageName, Context context)
	{
		PackageManager pm = context.getPackageManager();
		
		try {
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			return true;
		}
		catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
}