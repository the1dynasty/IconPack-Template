package your.icons.name.here.fragment;


import java.util.ArrayList;
import java.util.List;

import view.ScrollGridView;
import your.icons.name.here.R;
import your.icons.name.here.activity.AboutThemeActivity;
import your.icons.name.here.activity.ApplyLauncherMain;
import your.icons.name.here.activity.RequestActivity;
import your.icons.name.here.activity.Wallpaper;
import your.icons.name.here.adapter.MainAdapter;
import your.icons.name.here.adapter.MainAdapter.AdapterItem;
import android.content.Intent;
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
	
	public static final int APPLY = 0;
	public static final int WALLPAPER = 1;
	public static final int REQUEST = 2;
	public static final int THEMEINFO = 3;

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
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_request), 
					getResources().getString (R.string.desc_request), 2));
			listOfStuff.remove(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 3));

			
		} else {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_apply), 
					getResources().getString (R.string.desc_apply), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_walls), 
					getResources().getString (R.string.desc_walls), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_request), 
					getResources().getString (R.string.desc_request), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_info), 
					getResources().getString (R.string.desc_info), 3));
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
					if (tabletSize) { // For TABLETS
						
						switch (position) {
					case APPLY:
						Intent launcher = new Intent(getSherlockActivity(), ApplyLauncherMain.class);
						startActivity(launcher);
			        	break;
					case WALLPAPER:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
			        	break;
					case REQUEST:
						Intent request = new Intent(getSherlockActivity(), RequestActivity.class);
						startActivity(request);
			        	break;
						}	
				} else {	// For PHONES
					switch (position) {
					case APPLY:
						Intent launcher = new Intent(getSherlockActivity(), ApplyLauncherMain.class);
						startActivity(launcher);
		        		break;
					case WALLPAPER:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
		        		break;
					case REQUEST:
						Intent request = new Intent(getSherlockActivity(), RequestActivity.class);
						startActivity(request);
		        		break;
					case THEMEINFO:
						Intent aboutTheme = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutTheme);
		        		break;
		        		
					}
				}
				}	
			});
			
	}
}