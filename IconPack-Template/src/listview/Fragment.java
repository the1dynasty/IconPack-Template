package listview;

import java.util.ArrayList;
import java.util.List;

import your.icons.name.here.R;
import activities.AboutDev;
import activities.AboutTheme;
import activities.LauncherDialog;
import activities.Wallpaper;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;

/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class Fragment extends SherlockListFragment{
	
	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.listview_behind, null);
	}
	
	// Starts when the Fragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	/* This part does two things
	 * First - It counts the number of items and displays them
	 * Second - It displays the text in the "" which is a brief description of that item
	 * Removing any of these, will remove that list item but be sure to edit ALL the cases below or you list
	 * won't line up properly
	 */
		List<String> list_desc = new ArrayList<String>();
		list_desc.add("Checkout my app. It's filled with all of my work in one easy to locate place!");
		list_desc.add("Details and a preview of this theme");
		list_desc.add("Choose from the launchers to apply the theme!");
		list_desc.add("Choose from the included wallpapers");
		list_desc.add("Rate this theme on the Play Store");
		list_desc.add("Join our Google+ Community and share your themes or find themes to download!");
		list_desc.add("Circle me on Google+");
		list_desc.add("Share this theme with others");
		list_desc.add("Send your requests or just give feedback");
		list_desc.add("Find out more about the developer");
		list_desc.add("If you like this theme, consider donating to help contribute to future releases!");
		
	// This is the layout the list is going to follow for the actual look
		MainAdapter adapter = new MainAdapter(getSherlockActivity(), R.layout.listview_layout, list_desc);
		setListAdapter(adapter);
	}
	
	// Sets what happens when you click on a list item
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		switch(position){
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
				Intent aboutTheme = new Intent(getSherlockActivity(), AboutTheme.class);
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
	
	// ENDS FRAGMENT, BEGIN ADAPTER
	
	public class MainAdapter extends ArrayAdapter{
		
		private Context mContext;
		private int id;
		private List<String> description;

		@SuppressWarnings("unchecked")
		public MainAdapter(Context context, int textViewResourceId, List<String> list_desc){
			super(context, textViewResourceId, list_desc);
			mContext = context;
			id = textViewResourceId;
			description = list_desc;
			
		}
		
		@Override
		public View getView(int position, View v, ViewGroup parent){
			View mView = v;
			if (mView == null){
				LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				mView = vi.inflate(id, null);
			}

			/* Sets the Title text for each list view
			 * Change to whatever you want each list view to say
			 */
			TextView title = (TextView)mView.findViewById(R.id.title);
			switch(position) {
			case 0:
				title.setText("the1dynasty OSS App");
				break;
			case 1:
				title.setText("Theme Info");
				break;
			case 2:
				title.setText("Apply Theme");
				break;
			case 3:
				title.setText("Wallpaper Picker");
				break;
			case 4:
				title.setText("Rate Theme");
				break;
			case 5:
				title.setText("G+ Community");
				break;
			case 6:
				title.setText("My Google+");
				break;
			case 7:
				title.setText("Share Theme");
				break;
			case 8:
				title.setText("Email Developer");
				break;
			case 9:
				title.setText("About Developer");
				break;
			case 10:
				title.setText("Donate");
				break;
			}
			
			/* Sets the descriptions text color
			 * You can reference any color in the colors.xml and even add some
			 * You can also individually set the color for each ListView by 
			 * referencing another color
			 */
			TextView text = (TextView)mView.findViewById(R.id.description);
			switch(position){
			case 0:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 1:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 2:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 3:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 4:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 5:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 6:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 7:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 8:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 9:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 10:
				text.setTextColor(text.getContext().getResources()
						.getColor(R.color.list_desc_color));
				break;
			}
			text.setText(description.get(position));
			
			
			// Sets the icon image for each listview
			ImageView icon_Image = (ImageView)mView.findViewById(R.id.list_image);
			switch(position){
			case 0:
				icon_Image.setImageResource(R.drawable.icon_oss);
				break;
			case 1:
				icon_Image.setImageResource(R.drawable.icon_info);
				break;
			case 2:
				icon_Image.setImageResource(R.drawable.icon_launcher);
				break;
			case 3:
				icon_Image.setImageResource(R.drawable.icon_wall);
				break;
			case 4:
				icon_Image.setImageResource(R.drawable.icon_rate);
				break;
			case 5:
				icon_Image.setImageResource(R.drawable.icon_community);
				break;
			case 6:
				icon_Image.setImageResource(R.drawable.icon_gplus);
				break;
			case 7:
				icon_Image.setImageResource(R.drawable.icon_share);
				break;
			case 8:
				icon_Image.setImageResource(R.drawable.icon_email);
				break;
			case 9:
				icon_Image.setImageResource(R.drawable.icon_dev_logo);
				break;
			case 10:
				icon_Image.setImageResource(R.drawable.icon_wallet);
				break;
			}
			
			
			return mView;
		}
	}
}