package your.icons.name.here;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;

/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area 
 **/

public class AppActivity extends SherlockActivity {
     	
    private ImageButton 
    themeinfo, 
    sharetheme, 
    wallpaper,
    aboutdev,
    marketinfo, 
    email, 
    gplus,
    donate;
    
    // Launches the Activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((int) Window.FEATURE_NO_TITLE);
        setContentView(R.layout.flatview_main);
        
    /* Donate button. Change line 47 to match your own Donate URL. 
     * Unless you want me to get your donations :)
     */
        donate = (ImageButton) findViewById(R.id.donate);
        donate.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	try {
                		final Intent intent = new Intent(Intent.ACTION_VIEW)
                		.setData(Uri.parse("http://bit.ly/YWwhWu"));
                		startActivity(intent);
                	} 
                	catch (RuntimeException market) {
                		market.printStackTrace();
                	}	
                }
        });
        
    // About Theme button
        themeinfo = (ImageButton) findViewById(R.id.themeinfo);
        themeinfo.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                	Intent themeinfo1 = new Intent(Intent.ACTION_MAIN);
                	themeinfo1.setComponent(new ComponentName
                			("your.icons.name.here", 
                					"your.icons.name.here.AboutTheme"));
                	startActivity(themeinfo1);
                }
        });
        
     // About Developer button
    	aboutdev = (ImageButton) findViewById(R.id.aboutdev);
        aboutdev.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                	Intent aboutdev1 = new Intent();
                	aboutdev1.setComponent(new ComponentName
                			("your.icons.name.here", 
                					"your.icons.name.here.AboutDev"));
                	startActivity(aboutdev1);
                }
        });
        
     // Wallpaper button   	
        wallpaper = (ImageButton) findViewById(R.id.wallpaper);
        wallpaper.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	try {
                		Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName
                        		("your.icons.name.here",
                        				"your.icons.name.here.Wallpaper"));
                        startActivity(intent);
                	} 
                	catch (RuntimeException wall) {
                		wall.printStackTrace();
                	}
                }
        });
        
     // Rate Theme button      
        marketinfo = (ImageButton) findViewById(R.id.marketinfo);
        marketinfo.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	try {
                		final Intent intent = new Intent(Intent.ACTION_VIEW)
                		.setData(Uri.parse("market://details?id=activities"));
                		startActivity(intent);
                	} 
                	catch (RuntimeException market) {
                		market.printStackTrace();
                	}	
                }
        });
        
     /* Add your email on lines 123 & 124
      * Do not forget to change line 127 with your own pack name
      * This is the name shown as the subject of the email they send you
      */
        email = (ImageButton) findViewById(R.id.email);
        email.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	try {
                		Intent emailIntent = new Intent
                				(android.content.Intent.ACTION_SEND);  
                		String aEmailList[] = { 
                				"the1dynasty.android@gmail.com",
                				"the1dynasty.android@gmail.com" };    
                		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, 
                				aEmailList);  
                		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
                				"YOUR PACK NAME HERE");  
                		emailIntent.setType("plain/text");  
                		startActivity(emailIntent);
                	} 
                	catch (RuntimeException email) {
                		email.printStackTrace();
                	}	
                }
        });
        
     // Google+ button
        gplus = (ImageButton) findViewById(R.id.gplus);
        gplus.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	try {
                		final Intent intent = new Intent(Intent.ACTION_VIEW)
                		.setData(Uri.parse("https://plus.google.com/110748421773388678236/posts"));
                		startActivity(intent);
                	} 
                	catch (RuntimeException gp) {
                		gp.printStackTrace();
                	}	
                }
        });
        
     /* Share Theme button
      * Change line 163 to say whatever text you want people to share with
      * Line 164 is the URL shown with the shared text
      */
        sharetheme = (ImageButton) findViewById(R.id.sharetheme);
        sharetheme.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	Intent sendIntent = new Intent();
                	sendIntent.setAction(Intent.ACTION_SEND);
                	sendIntent.putExtra(Intent.EXTRA_TEXT, 
                			"ENTER YOUR TEXT HERE. " +
                			"https://play.google.com/store/apps/details?id=activities");
                	sendIntent.setType("text/plain");
                	startActivity(Intent.createChooser(sendIntent, getResources()
                			.getText(R.string.app_name)));
                	}
        		});
    	}

}
    
 