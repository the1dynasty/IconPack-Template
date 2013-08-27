/*
 * Copyright 2013 the1dynasty
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gridview;

/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

import helper.GlassActionBarHelper;
import fragments.MainFragment;
import your.icons.name.here.AboutDev;
import your.icons.name.here.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Main extends SherlockFragmentActivity {
	
	private SharedPreferences prefs;
	private GlassActionBarHelper helper;
	
	// Starts the Activity for the gridview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		prefs = getSharedPreferences(getResources().getString(R.string.theme_name), 0);
		checkBuild();

		helper = new GlassActionBarHelper().contentLayout(R.layout.gridview_main);
		setContentView(helper.createView(this));
		
		getSupportActionBar().setDisplayShowHomeEnabled(true); // Set this to false to hide AB Icon
		getSupportActionBar().setDisplayShowTitleEnabled(true); // Set this to false to hide AB Title
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new MainFragment())
		.commit();
	}

	/************************************************************************
	 ******************** This is your Changelog Stuff **********************
	 ************************************************************************/
	public void checkBuild() {
	  int buildNum = prefs.getInt("Build Number", 1);
	  int currentVersion = 0;
	  
	  try {
	    currentVersion = getPackageManager()
	    		.getPackageInfo(getPackageName(), 0).versionCode;
	  }
	  catch (NameNotFoundException e) {
	    e.printStackTrace();
	  }
	    if(currentVersion > buildNum) {
	    	  getChangelog().show();
	    	  Editor editor = prefs.edit();
	    	  editor.putInt("Build Number", currentVersion);
	    	  editor.commit();
	    	}
	  }
	
	public Dialog getChangelog()
	 {
	 	final Dialog CDialog = new Dialog(Main.this);
	 	CDialog.setTitle(getResources().getString(R.string.changelog_title));
	 	CDialog.setContentView(R.layout.changelog);
	 	CDialog.setCanceledOnTouchOutside(true);
	 	CDialog.setCancelable(true);
	 	 
	 	Button Close = (Button) CDialog.findViewById(R.id.close);
	 	Close.setOnClickListener(new View.OnClickListener()
	 	{
	 	 @Override
	 	 public void onClick(View v)
	 	 {
	 	 CDialog.dismiss();
	 	 }
	 	});
	 	 
	 	return CDialog;
	 }

	/******************************************************************************
	 ** This code checks if MY OSS is installed on first run. If it is installed **
	 ** you get a dialog that says you're awesome and the user hits OK to remove **
	 ** that dialog. If it is NOT installed, the user is prompted to install it. **
	 ** You can remove this section if you're not checking for apps on first run **
	 ******************************************************************************/
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
		        .putBoolean("firstrunOSS", false) /* You can change this to another name */
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
					Intent share = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("market://details?id=app.the1dynasty.oss"));
		    		startActivity(share);
			}
			});
			builder.show();
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
        switch(item.getItemId())
        {
            case R.id.shareButton:
        		Intent shareIntent = new Intent(Intent.ACTION_SEND);
        	    shareIntent.setType("text/plain");
        	    shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_link));
        	    startActivity(Intent.createChooser(shareIntent, "Share Via"));
                break;
            case R.id.rateButton:
            	Intent rate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
            			("market://details?id=your.icons.name.here"));
            	startActivity(rate);
                break;
            case R.id.emailButton:
            	Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "the1dynasty.android@gmail.com" });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getText(R.string.email_subject));
				emailIntent.setType("plain/text");
				startActivity(Intent.createChooser(emailIntent, "Contact Developer"));
				
                break;
            case R.id.aboutButton:
				Intent about = new Intent(Main.this, AboutDev.class);
				startActivity(about);
                break;
            case R.id.donateButton:
				Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
						("http://bit.ly/YWwhWu"));
        		startActivity(donate);
                break;
        }
        
        return true;
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
