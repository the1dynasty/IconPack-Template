package gridview;

/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import fragments.MainFragment;
import your.icons.name.here.R;

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
		    	
	    /* 
	     * Installed dialog
	     * Check res/values/strings.xml to change text to whatever you want the Alert to say
	     */
	        AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        alert.setTitle(getResources().getString (R.string.alert_start_title));
	        alert.setMessage(getResources().getString (R.string.alert_start_desc));
	        alert.setIcon(R.drawable.alert_pass);
	        alert.setPositiveButton(getResources().getString (R.string.ok), null).show ();
	        
		    // Save the state so this dialog doesn't run again
		    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
		        .edit()
		        .putBoolean("firstrunOSS", false)
		        .commit();
	              }
	    }
		
	    /* 
	     * Not Installed dialog
	     * Check res/values/strings.xml to change text to whatever you want the Alert to say
	     */
		else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getResources().getString (R.string.error_start_title));
			builder.setMessage(getResources().getString (R.string.error_start_desc));
	        builder.setIcon(R.drawable.alert_fail);
			builder.setNeutralButton(getResources().getString (R.string.later), new OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
				}
			});
			
		 // Change line 88 with the URL to YOUR app
			builder.setPositiveButton(getResources().getString (R.string.get), new OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent uccw = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("market://details?id=app.the1dynasty.oss"));
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
