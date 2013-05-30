package gridview;

/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

import your.icons.name.here.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import fragments.MainFragment;

public class Main extends SherlockFragmentActivity {

	// Starts the Activity for the list view
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_main);
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new MainFragment())
		.commit();
	}
	
	/** 
	 ** This code checks if MY OSS is installed on first run. If it is installed
	 ** you get a dialog that says you're awesome and the user hits OK to remove 
	 ** that dialog. If it is NOT installed, the user is prompted to install it.
	 **
	 ** You can remove this section if you're not checking for anything on first run
	 **/
	public void onStart() {
		super.onStart();
		boolean installed = isAppInstalled("app.the1dynasty.oss");
		
		// Checking if installed and if its the first run
	    if (installed) {
	    	boolean firstrunOSS = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
	    			.getBoolean("firstrunOSS", true);
		    if (firstrunOSS){
		    	
	    /* Installed dialog
	     * Change lines 54, 55-56 to match your own needs
	     * 55-56 is really one line just split up for organization of code in here
	     */
	        AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        alert.setTitle("You Are AWESOME!!!");
	        alert.setMessage("You also have my One-Stop-Shop App installed. " +
	        		" Be sure to visit that app for more themes and other downloads!");
	        alert.setIcon(R.drawable.alert_pass);
	        alert.setPositiveButton("OK", null).show ();
	        
		    // Save the state so this dialog doesn't run again
		    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
		        .edit()
		        .putBoolean("firstrunOSS", false)
		        .commit();
	              }
	    }
		
	    /* Not Installed dialog
	     * Change lines 74, 75-77 to match your own needs
	     * 75-77 is really one line just split up for organization of code in here
	     */
		else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Ohnoes...");
			builder.setMessage("We have detected that you do not have my " +
					"One-Stop-Shop App installed... " +
					" Would you like to download it for FREE now?");
	        builder.setIcon(R.drawable.alert_fail);
			builder.setNeutralButton("Later", new OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
				}
			});
			
		 // Also change line 90 with the URL to YOUR app
			builder.setPositiveButton("Get App", new OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent uccw = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://bit.ly/ZI34gC"));
		    		startActivity(uccw);
			}
			});
			builder.show();
	    }
	}
	
	private boolean isAppInstalled(String packageName){
		// Tool we need to parse other packages
		PackageManager pm = getPackageManager();
		// True/False variable set to false by default, show "Not Installed"
		boolean app_installed = false;
		// If the app isn't installed, we would get a force close without a try/catch clause
		// If installed, try returns true, if not, we catch the exception and set it to false
		try {
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e){
			app_installed = false;
		}
		// Must return a value (not overridden method)
		return app_installed;
	}
}
