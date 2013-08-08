/*
 * Copyright 2013 the1dynasty and Pkmmte
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

import your.icons.name.here.R;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import fragments.NewIconsFragment;

public class NewIconsMain extends SherlockFragmentActivity {

	// Starts the Activity for the gridview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_icons_main);
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container_launcher, new NewIconsFragment())
		.commit();
	}
	
	// This will return the Activity to the Main Screen when the app is in a Paused (not used) state
	@Override
	  public void onPause(){
		  super.onPause();
		  finish();
	  }
}
