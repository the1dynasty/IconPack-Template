package activities;

import your.icons.name.here.R;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class LauncherDialog extends SherlockActivity {
	
	// Launches popup Dialog. Refer to Manifest to make this a fullscreen Activity instead of a popup
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.launcher_dialog);

	  /* Button press for applying Apex theme
	   * This checks if Apex is installed, if it is, it opens for you to apply theme
	   * if NOT installed, it shows a not installed warning message 
	   */
	  ImageButton apex = (ImageButton)this.findViewById(R.id.btn_apex);
	  apex.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	final String ACTION_SET_THEME = "com.anddoes.launcher.SET_THEME";
	    	final String EXTRA_PACKAGE_NAME = "com.anddoes.launcher.THEME_PACKAGE_NAME";

	    	Intent intent = new Intent(ACTION_SET_THEME);
	    	intent.putExtra(EXTRA_PACKAGE_NAME, getPackageName());
	    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	try {
	    	    startActivity(intent);

	    	    Toast applied = Toast.makeText(getBaseContext(), "Select your options and press " +
	    	    		"Apply to finalize!", 
	    	    		Toast.LENGTH_LONG);
	    	    applied.show();
	    	} 
	    	catch (ActivityNotFoundException e) {
	    	    Toast toast = Toast.makeText(getBaseContext(), "Apex Launcher is not installed! " +
	    	    		" Please install to use this theme with this launcher.", 
	    	    		Toast.LENGTH_SHORT);
	    	    toast.show();
	    	}
	    	finish();
	    }
	  });

	  // This is the Nova Intent to Apply within the pack that I'm working on fixing
	  /* Anyone is willing to help submit a fix for this part, 
	   * please email me the1dynasty.android@gmail.com
	   */
	  ImageButton nova = (ImageButton)this.findViewById(R.id.btn_nova);
	  nova.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	// Temporary toast message until its fixed
	    	    Toast failed = Toast.makeText(getBaseContext(), "Currently Not Supported... " +
	    	    		" Please Apply through Nova Settings.", 
	    	    		Toast.LENGTH_LONG);
	    	    failed.show();
	    	    finish();
		    }
		    /*
	    	final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
	        final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
	        final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
	        final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";
	    @Override
	    public void onClick(View v) {
	            Intent intent = new Intent(ACTION_APPLY_ICON_THEME);
	            intent.setPackage(NOVA_PACKAGE);
	            intent.putExtra(EXTRA_ICON_THEME_TYPE, "Icon Pack");
	            intent.putExtra(EXTRA_ICON_THEME_PACKAGE, "activities");	           
	    	
	    	    startActivity(intent);

	    	    Toast failed = Toast.makeText(getBaseContext(), "Currently Not Supported... " +
	    	    		" Please Apply through Nova Settings.", 
	    	    		Toast.LENGTH_LONG);
	    	    failed.show();
	    	    finish();
	    	} 
	    	*/
	    });
	  
	  // Holo Apply button, not currently working
	  /* Anyone is willing to help submit a fix for this part, 
	   * please email me the1dynasty.android@gmail.com
	   */
	  ImageButton holo = (ImageButton)this.findViewById(R.id.btn_holo);
	  holo.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {

    	    Toast failed = Toast.makeText(getBaseContext(), "Currently Not Supported... " +
    	    		" Please Apply through Holo Settings.", 
    	    		Toast.LENGTH_LONG);
    	    failed.show();
    	    finish();
	    }
	  });

	  /* Button press for applying ADW theme
	   * This checks if ADW is installed, if it is, it opens for you to apply theme
	   * if NOT installed, it shows a not installed warning message 
	   */
	  ImageButton adw = (ImageButton)this.findViewById(R.id.btn_adw);
	  adw.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
	    	Intent adw=new Intent("org.adw.launcher.SET_THEME");
	    	adw.putExtra("org.adw.launcher.theme.NAME","activities");
	    	try {
	    		startActivity(Intent.createChooser(adw,"Uh Oh...")); {
	    		}
	    	} 
	    	catch (ActivityNotFoundException e) {
	    	    Toast toast = Toast.makeText(getBaseContext(), "ADW Launcher is not installed!", 
	    	    		Toast.LENGTH_SHORT);
	    	    toast.show();
	    	}
	    	finish();
	      
	    }
	  });
	  
	  // Bottom cancel button of the dialog
	  Button cancelButton = (Button)this.findViewById(R.id.cancelbtn1);
	  cancelButton.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
	      finish();
	    }
	  });
}
	// This will return the Activity to the Main Screen when the app is in a Paused (not used) state
	@Override
	  public void onPause(){
		  super.onPause();
		  finish();
	  }
}