/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package your.icons.name.here.activity;

import java.util.Locale;

import your.icons.name.here.R;
import your.icons.name.here.fragment.IconFragmentAll;
import your.icons.name.here.fragment.IconFragmentGames;
import your.icons.name.here.fragment.IconFragmentLatest;
import your.icons.name.here.fragment.IconFragmentMisc;
import your.icons.name.here.fragment.IconFragmentPlay;
import your.icons.name.here.fragment.IconFragmentSystem;
import your.icons.name.here.util.PagerSlidingTabStrip;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;


public class AllIcons extends FragmentActivity{

	private PagerSlidingTabStrip tabs;
	//private ViewPager pager;
	private IconPagerAdapter adapter;

	// Flag Constants
	public static final int LATEST = 0;
	public static final int ALL = 1;
	public static final int SYSTEM = 2;
	public static final int PLAY = 3;
	public static final int GAMES = 4;
	public static final int MISC = 5;
	
		//This activity is what displays the icon fragments (the icon categories) see iconfragment for the tabs themselves
	   @Override
	    public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.all_icons_layout);

	        
	        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
			ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
			adapter = new IconPagerAdapter(getSupportFragmentManager());

			pager.setAdapter(adapter);
			

			final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
					.getDisplayMetrics());
			pager.setPageMargin(pageMargin);

			tabs.setViewPager(pager);

	        // Set Present tab as default
	        pager.setCurrentItem(1);

	    }
	   
	   /** TODO 
	    * Add search function to Theme Icons section
	    */
	   /*
	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	       getMenuInflater().inflate(R.menu.main_search, menu);
	       MenuItem searchItem = menu.findItem(R.id.action_search);
	       SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
	       // Configure the search info and add any event listeners
	      
	       return super.onCreateOptionsMenu(menu);
	   }
		*/


	public class IconPagerAdapter extends FragmentPagerAdapter {

		private final int[] TITLES = 
			{ 
				R.string.icons_latest, 
				R.string.icons_all,  
				R.string.icons_system, 
				R.string.icons_play, 
				R.string.icons_games,
				R.string.icons_misc
			};

		public IconPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case LATEST:
				return getString(R.string.icons_latest).toUpperCase(l);
			case ALL:
				return getString(R.string.icons_all).toUpperCase(l);
			case SYSTEM:
				return getString(R.string.icons_system).toUpperCase(l);
			case PLAY:
				return getString(R.string.icons_play).toUpperCase(l);
			case GAMES:
				return getString(R.string.icons_games).toUpperCase(l);
			case MISC:
				return getString(R.string.icons_misc).toUpperCase(l);	
			}
			return null;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new Fragment();
			switch(position){
			case LATEST:
				f= new IconFragmentLatest();	
				break;
			case ALL:
				f= new IconFragmentAll();	
				break;
			case SYSTEM:
				f= new IconFragmentSystem();
				break;
			case PLAY:
				f= new IconFragmentPlay();	
				break;
			case GAMES:
				f= new IconFragmentGames();	
				break;	
			case MISC:
				f= new IconFragmentMisc();	
				break;	
			}
			return f;
		}
		
		@Override
		public int getCount() {
			return TITLES.length;
		}	
		
	}

}