package your.icons.name.here;

import your.icons.name.here.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.view.Window;

public class LauncherDialog extends Activity {
	
	static final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
    static final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
    static final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
    static final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";
	
	// Launches popup Dialog. Refer to Manifest to make this a fullscreen Activity instead of a popup
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  noTitle();
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

	  ImageButton nova = (ImageButton)this.findViewById(R.id.btn_nova);
	  nova.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
              Intent intent = new Intent(ACTION_APPLY_ICON_THEME);
              intent.setPackage(NOVA_PACKAGE);
              intent.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
              intent.putExtra(EXTRA_ICON_THEME_PACKAGE, "your.icons.name.here");
              try {
              startActivity(intent);
              }
              catch (ActivityNotFoundException e) {
		    	// Temporary toast message until its fixed
	    	    Toast failed = Toast.makeText(getBaseContext(), "Nova Launcher is not installed on your " +
	    	    		"device please download from the Play Store", 
	    	    		Toast.LENGTH_LONG);
	    	    failed.show();
              }
	    	    finish();
		    }

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
	    	Intent adw = new Intent("org.adw.launcher.SET_THEME");
	        adw.putExtra("org.adw.launcher.theme.NAME","your.icons.name.here");
	    	try {
	    		 startActivity(Intent.createChooser(adw,"activating theme..."));
	    		 Toast.makeText(getApplicationContext(),"Press apply theme to finalize!",Toast.LENGTH_LONG).show();{
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
	  
	  ImageButton actionL = (ImageButton) this.findViewById(R.id.btn_al);
	  actionL.setOnClickListener(new View.OnClickListener() {
	     @Override
	      public void onClick(View v) {
	          // Check Action Launcher Pro is installed
	          Intent al = getPackageManager().getLaunchIntentForPackage("com.chrislacy.actionlauncher.pro");
	          if (al != null) {
	              Intent yourPackageName = al;
	              al.putExtra("apply_icon_pack", yourPackageName);
	              startActivity(al);
	          } else {
	              // Directs users to get Action Launcher Pro if not installed
	              String playStoreUrl = "https://play.google.com/store/apps/details?id=com.chrislacy.actionlauncher.pro";
	              startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUrl)));
	          }
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

	// Hides the title bar
	public void noTitle() {
		requestWindowFeature((int) Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	// This will return the Activity to the Main Screen when the app is in a Paused (not used) state
	@Override
	  public void onPause(){
		  super.onPause();
		  finish();
	  }
}