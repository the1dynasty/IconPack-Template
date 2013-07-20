package fragments;

import gridview.LauncherMain;
import gridview.NewIconsMain;
import gridview.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import your.icons.name.here.AboutDev;
import your.icons.name.here.AboutThemeActivity;
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

import com.actionbarsherlock.app.SherlockFragment;


/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class MainFragment extends SherlockFragment{
	
	ScrollGridView gridView;
	final List<AdapterItem> listOfStuff = new ArrayList<AdapterItem>();

	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.gridview_behind, null);
	}
	
	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	/* 
	 * This part does two things
	 * First - It counts the number of items and displays them
	 * Second - It displays the text in the "" which is a brief description of that item
	 * Removing any of these will remove that item but be sure to edit ALL the cases below or your list
	 * won't line up properly
	 */
		
		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove BOTH parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/

		/* 
		 * Sets the Title and description text for each GridView item
		 * Check res/values/strings.xml to change text to whatever you want each GridView to say
		 */
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_oss), 
					getResources().getString (R.string.desc_oss), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_new_icons), 
					getResources().getString (R.string.desc_new_icons), 1));
			listOfStuff.remove(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 4));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_rate), 
					getResources().getString (R.string.desc_rate), 5));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_community), 
					getResources().getString (R.string.desc_community), 6));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_gplus), 
					getResources().getString (R.string.desc_gplus), 7));;
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_email), 
					getResources().getString (R.string.desc_email), 8));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_about), 
					getResources().getString (R.string.desc_about), 9));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_donate), 
					getResources().getString (R.string.desc_donate), 10));
			
		} else {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_oss), 
					getResources().getString (R.string.desc_oss), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_new_icons), 
					getResources().getString (R.string.desc_new_icons), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 4));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_rate), 
					getResources().getString (R.string.desc_rate), 5));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_community), 
					getResources().getString (R.string.desc_community), 6));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_gplus), 
					getResources().getString (R.string.desc_gplus), 7));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_email), 
					getResources().getString (R.string.desc_email), 8));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_about), 
					getResources().getString (R.string.desc_about), 9));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_donate), 
					getResources().getString (R.string.desc_donate), 10));
		}

		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove both parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/
			MainAdapter adapter = new MainAdapter(getActivity(), listOfStuff);
	
			gridView.setAdapter(adapter);
			gridView.setExpanded(true);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					
					@SuppressWarnings("unused")
					MainFragment gridContentT = null;
					
					boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
					if (tabletSize) { // for tablets
						
						switch (position) {
						case 0:
							/** 
							 ** This checks if MY OSS app is installed. You can remove this case
							 ** statement completely or add your own app to check against or leave
							 ** it and let it check for MY app :D
							 ** If it is installed, the app will open when you press the list item
							 ** If it is NOT installed, it will open up the play store to download it
							 ** Change line 157 with the play store link for your own app if you're 
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
										("market://details?id=app.the1dynasty.oss"));
								startActivity(oss);
						}
			    			break;
						case 1:
							Intent newIcons = new Intent(getSherlockActivity(), NewIconsMain.class);
							startActivity(newIcons);
			        		break;
						case 2:
							Intent launcher = new Intent(getSherlockActivity(), LauncherMain.class);
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
							// Change line 184 with the link for YOUR own G+ Account
							Intent gplus = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
									("https://plus.google.com/110748421773388678236/posts"));
			        		startActivity(gplus);
			        		break;
						case 7:
						     /* 
						      * Add your email on lines 206 & 207
						      * Do not forget to check the res/values/strings.xml to add the subject you
						      * want people to email you from this app with
						      * This is the name shown as the subject of the email they send you
						      */
							Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
			        		String aEmailList[] = { "the1dynasty.android@gmail.com",
			        				"the1dynasty.android@gmail.com" };    
			        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
			        		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
			        				getResources().getText(R.string.email_subject));  
			        		emailIntent.setType("plain/text");  
			        		startActivity(emailIntent);
			        		break;
						case 8:
							Intent about = new Intent(getSherlockActivity(), AboutDev.class);
							startActivity(about);
			        		break;
						case 9:
						    /* 
						     * Change line 224 to match your own Donate URL. 
						     * Unless you want me to get your donations :)
						     */
							Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
									("http://bit.ly/YWwhWu"));
			        		startActivity(donate);
			        		break;
		}	
				} else {	// for phones
					switch (position) {
					case 0:
						/** 
						 ** This checks if MY OSS app is installed. You can remove this
						 ** section completely or Add your own app to check against or leave
						 ** it and let it check for MY app :D
						 ** If it is installed, the app will open when you press the list item
						 ** If it is NOT installed, it will open up the play store to download it
						 ** Change line 249 with the play store link for your own app if you're 
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
									("market://details?id=app.the1dynasty.oss"));
							startActivity(oss);
					}
		    			break;
					case 1:
						Intent newIcons = new Intent(getSherlockActivity(), NewIconsMain.class);
						startActivity(newIcons);
		        		break;
					case 2:
						Intent aboutTheme = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutTheme);
		        		break;
					case 3:
						Intent launcher = new Intent(getSherlockActivity(), LauncherMain.class);
						startActivity(launcher);
		        		break;
					case 4:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
		        		break;
					case 5:
		            	Intent rate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
		            			("market://details?id=your.icons.name.here"));
		            	startActivity(rate);
		        		break;
					case 6:
						/** 
						 ** This launches my community on G+
						 ** Please leave this link in here for others to join. Thank You!
						 **/
						Intent gpCommunity = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://bit.ly/14F6Eez"));
		          		startActivity(gpCommunity);
		        		break;
					case 7:
						// Change line 282 with the link for YOUR own G+ Account
						Intent gplus = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("https://plus.google.com/110748421773388678236/posts"));
		        		startActivity(gplus);
		        		break;
					case 8:
					     /* 
					      * Add your email on lines 302 & 303
					      * Do not forget to check the res/values/strings.xml to add the subject you
					      * want people to email you from this app with
					      * This is the name shown as the subject of the email they send you
					      */
						Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
		        		String aEmailList[] = { "the1dynasty.android@gmail.com",
		        				"the1dynasty.android@gmail.com" };    
		        		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
		        		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
		        				getResources().getText(R.string.email_subject));  
		        		emailIntent.setType("plain/text");  
		        		startActivity(emailIntent);
		        		break;
					case 9:
						Intent about = new Intent(getSherlockActivity(), AboutDev.class);
						startActivity(about);
		        		break;
					case 10:
					    /* 
					     * Change line 320 to match your own Donate URL. 
					     * Unless you want me to get your donations :)
					     */
						Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://bit.ly/YWwhWu"));
		        		startActivity(donate);
		        		break;
		        		
					}
				}
				}	
/*
			case 8:
				Intent shareIntent = new Intent();
            	shareIntent.setAction(Intent.ACTION_SEND);
            	shareIntent.putExtra(Intent.EXTRA_TEXT, 
            			getResources().getString (R.string.share_text));
            	shareIntent.setType("text/plain");
            	startActivity(Intent.createChooser(shareIntent, 
            			getResources().getText(R.string.app_name)));
        		break;
*/	
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
			});
			
	}
}