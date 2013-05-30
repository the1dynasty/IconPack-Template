package fragments;

import java.util.ArrayList;
import java.util.List;

import your.icons.name.here.AboutDev;
import your.icons.name.here.AboutThemeActivity;
import your.icons.name.here.LauncherDialog;
import your.icons.name.here.R;
import your.icons.name.here.Wallpaper;
import adapters.MainAdapter;
import adapters.MainAdapter.AdapterItem;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;


/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class MainFragment extends SherlockFragment{
	
	GridView gridView;
	final List<AdapterItem> listOfStuff = new ArrayList<AdapterItem>();

	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.gridview_behind, null);
	}
	
	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	/* This part does two things
	 * First - It counts the number of items and displays them
	 * Second - It displays the text in the "" which is a brief description of that item
	 * Removing any of these, will remove that list item but be sure to edit ALL the cases below or you list
	 * won't line up properly
	 */
		
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			gridView = (GridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem("One-Stop-Shop", 
					"All of my stuff in one place!", 0));
			listOfStuff.remove(new AdapterItem("Theme Info", 
					"Details and a preview of this theme", 1));
			listOfStuff.add(new AdapterItem("Apply Theme", 
					"Choose from the launchers to apply the theme!", 2));
			listOfStuff.add(new AdapterItem("Wallpaper Chooser", 
					"Choose from the included wallpapers", 3));
			listOfStuff.add(new AdapterItem("Rate Theme", 
					"Rate this theme on the Play Store", 4));
			listOfStuff.add(new AdapterItem("Community", 
					"Join our Google+ Community to find and share your themes!", 5));
			listOfStuff.add(new AdapterItem("My Google+", 
					"Circle me on Google+", 6));
			listOfStuff.add(new AdapterItem("Share Theme", 
					"Share this theme with others", 7));
			listOfStuff.add(new AdapterItem("Email Developer", 
					"Send your requests or just give feedback", 8));
			listOfStuff.add(new AdapterItem("About Developer", 
					"Find out more about the developer", 9));
			listOfStuff.add(new AdapterItem("Donate", 
					"Consider donating to help contribute to future releases!", 10));
			
		} else {
			gridView = (GridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem("One-Stop-Shop", 
					"All of my stuff in one place!", 0));
			listOfStuff.add(new AdapterItem("Theme Info", 
					"Details and a preview of this theme", 1));
			listOfStuff.add(new AdapterItem("Apply Theme", 
					"Choose from the launchers to apply the theme!", 2));
			listOfStuff.add(new AdapterItem("Wallpaper Chooser", 
					"Choose from the included wallpapers", 3));
			listOfStuff.add(new AdapterItem("Rate Theme", 
					"Rate this theme on the Play Store", 4));
			listOfStuff.add(new AdapterItem("Community", 
					"Join our Google+ Community to find and share your themes!", 5));
			listOfStuff.add(new AdapterItem("My Google+", 
					"Circle me on Google+", 6));
			listOfStuff.add(new AdapterItem("Share Theme", 
					"Share this theme with others", 7));
			listOfStuff.add(new AdapterItem("Email Developer", 
					"Send your requests or just give feedback", 8));
			listOfStuff.add(new AdapterItem("About Developer", 
					"Find out more about the developer", 9));
			listOfStuff.add(new AdapterItem("Donate", 
					"Consider donating to help contribute to future releases!", 10));
		}
		 

			MainAdapter adapter = new MainAdapter(getActivity(), listOfStuff);
	
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					@SuppressWarnings("unused")
					MainFragment gridContentT = null;
					switch (position) {
					case 0:
						/** 
						 ** This checks if MY OSS app is installed. You can remove this
						 ** section completely or Add your own app to check against or leave
						 ** it and let it check for MY app :D
						 ** If it is installed, the app will open when you press the list item
						 ** If it is NOT installed, it will open up the play store to download it
						 ** Change line 92 with the play store link for your own app if you're 
						 ** using this feature!
						 **/
						if(isPackageExists("app.the1dynasty.oss")){
							Intent oss = new Intent("android.intent.action.MAIN");
							oss.setComponent(ComponentName.unflattenFromString
									("app.the1dynasty.oss/app.activities.MainActivity"));
							oss.addCategory("android.intent.category.LAUNCHER");
							startActivity(oss);
						}
						else{
							Intent oss = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
									("http://bit.ly/ZI34gC"));
							startActivity(oss);
					}
		    			break;
					case 1:
						Intent aboutTheme = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutTheme);
		        		break;
					case 2:
						Intent launcher = new Intent(getSherlockActivity(), LauncherDialog.class);
						startActivity(launcher);
		        		break;
					case 3:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
		        		break;
					case 4:
		            	Intent rate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
		            			("market://details?id=your.icons.name.here"));
		            	startActivity(rate);
		        		break;
					case 5:
						/** 
						 ** This launches my community on G+
						 ** Please leave this link in here for others to join. Thank You!
						 **/
						Intent gpCommunity = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://bit.ly/14F6Eez"));
		          		startActivity(gpCommunity);
		        		break;
					case 6:
						// Change line 125 with the link for YOUR own G+ Account
						Intent gplus = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("https://plus.google.com/110748421773388678236/posts"));
		        		startActivity(gplus);
		        		break;
					case 7:
						/* Change line 136 to say whatever text you want people to share with
						 * Line 137 is the URL shown with the shared text
						 * Line 138 is where I promote and link my app. You can remove this if
						 * you're not using it
						 */
						Intent shareIntent = new Intent();
		            	shareIntent.setAction(Intent.ACTION_SEND);
		            	shareIntent.putExtra(Intent.EXTRA_TEXT, "ENTER YOUR TEXT HERE. " +
		            			"https://play.google.com/store/apps/details?id=your.icons.name.here " +
		            			"I PROMOTE MY APP HERE http://bit.ly/ZI34gC");
		            	shareIntent.setType("text/plain");
		            	startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.app_name)));
		        		break;
					case 8:
					     /* Add your email on lines 148 & 149
					      * Do not forget to change line 152 with your own pack name
					      * This is the name shown as the subject of the email they send you
					      */
						Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
		        		String aEmailList[] = { "the1dynasty.android@gmail.com",
		        				"the1dynasty.android@gmail.com" };    
		        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
		        		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
		        				"YOUR PACK NAME HERE");  
		        		emailIntent.setType("plain/text");  
		        		startActivity(emailIntent);
		        		break;
					case 9:
						Intent about = new Intent(getSherlockActivity(), AboutDev.class);
						startActivity(about);
		        		break;
					case 10:
					    /* Change line 165 to match your own Donate URL. 
					     * Unless you want me to get your donations :)
					     */
						Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://bit.ly/YWwhWu"));
		        		startActivity(donate);
		        		break;
	}
	
	// Sets what happens when you click on a list item
				/** 
				 ** This checks if MY OSS app is installed. You can remove this
				 ** section completely or Add your own app to check against or leave
				 ** it and let it check for MY app :D
				 ** If it is installed, the app will open when you press the list item
				 ** If it is NOT installed, it will open up the play store to download it
				 ** Change line 92 with the play store link for your own app if you're 
				 ** using this feature!
				 **/
				/** 
				 ** This launches my community on G+
				 ** Please leave this link in here for others to join. Thank You!
				 **/
				/* Change line 136 to say whatever text you want people to share with
				 * Line 137 is the URL shown with the shared text
				 * Line 138 is where I promote and link my app. You can remove this if
				 * you're not using it
				 */
			     /* Add your email on lines 148 & 149
			      * Do not forget to change line 152 with your own pack name
			      * This is the name shown as the subject of the email they send you
			      */
			    /* Change line 165 to match your own Donate URL. 
			     * Unless you want me to get your donations :)
			     */
	}				
	
	/** 
	 ** This is the code needed to check the package in case 0
	 ** If you remove that check, you can remove this code too
	 ** Leaving it here won't harm anything either
	 **/
	public boolean isPackageExists(String targetPackage){
		  List<ApplicationInfo> packages;
		  PackageManager pm;
		  pm = getSherlockActivity().getPackageManager();
		  packages = pm.getInstalledApplications(0);
		  for (ApplicationInfo packageInfo : packages) {
		  if(packageInfo.packageName.equals(targetPackage)) return true;
		  }  
		  return false;
		  }

			/* Sets the Title text for each list view
			 * Change to whatever you want each list view to say
			 */
			
			/* Sets the descriptions text color
			 * You can reference any color in the colors.xml and even add some
			 * You can also individually set the color for each ListView by 
			 * referencing another color
			 */
			});
}
}