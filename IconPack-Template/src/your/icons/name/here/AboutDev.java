package your.icons.name.here;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;

public class AboutDev extends SherlockActivity {

	// This creates your About Dev Activity
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  noTitle();
	  setContentView(R.layout.about_dev);  
      
      Typeface font1 = Typeface.createFromAsset(getAssets(), "RobotoSlab-Regular.ttf");
      TextView txt1 = (TextView) findViewById(R.id.devFont);
      txt1.setTypeface(font1); 
      TextView title1 = (TextView) findViewById(R.id.title1);
      title1.setTypeface(font1); 
      TextView desc1 = (TextView) findViewById(R.id.description1);
      desc1.setTypeface(font1); 
      TextView title2 = (TextView) findViewById(R.id.title2);
      title2.setTypeface(font1); 
      TextView desc2 = (TextView) findViewById(R.id.description2);
      desc2.setTypeface(font1); 
}

	// Hides the title bar
	public void noTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
