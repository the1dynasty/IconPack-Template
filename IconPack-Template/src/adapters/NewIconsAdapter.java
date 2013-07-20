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

package adapters;

import java.util.List;

import your.icons.name.here.R;
import your.icons.name.here.TouchHighlightImageButton;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import fragments.NewIconsFragment;

public class NewIconsAdapter extends BaseAdapter{
	private Context context;
	private List<LauncherItem> gridItem;

	public NewIconsAdapter(Context context, List<LauncherItem> gridItem) {
		this.gridItem = gridItem;
		this.context = context;
	}

	public View getView(int position, View v, ViewGroup parent) {
		final ViewHolder holder;
		LauncherItem entry = gridItem.get(position);
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = inflater.inflate(R.layout.new_icons_layout, null);
			
			holder = new ViewHolder();
			holder.title = (TextView) v.findViewById(R.id.title);
			holder.launcher_image = (TouchHighlightImageButton) v.findViewById(R.id.thumb_button);
			holder.container = (LinearLayout) v.findViewById(R.id.container);
			
			v.setTag(holder);
		}
		else {
			holder = (ViewHolder) v.getTag();
		}		
		
		/************************************************************************
		 ****************** Add icons and zoomed images here ********************
		 ************************************************************************/
			switch(entry.getID()){
			case 0:
				holder.title.setTextColor(context.getResources()
						.getColor(R.color.black)); // Change text color here
				holder.launcher_image.setImageResource(R.mipmap.icon_apex); // Replace with your Icon
				holder.launcher_image.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                NewIconsFragment.zoomImageFromThumb(holder.launcher_image, 
		                		R.mipmap.banner_apex, // Replace with zoomed Image
		                		holder.container); 
		            }
		        });
				break;
			case 1:
				holder.title.setTextColor(context.getResources()
						.getColor(R.color.black)); // Change text color here
				holder.launcher_image.setImageResource(R.mipmap.icon_nova); // Replace with your Icon
				holder.launcher_image.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                NewIconsFragment.zoomImageFromThumb(holder.launcher_image, 
		                		R.mipmap.banner_nova, // Replace with zoomed Image
		                		holder.container); 
		            }
		        });
				break;
			case 2:
				holder.title.setTextColor(context.getResources()
						.getColor(R.color.black)); // Change text color here
				holder.launcher_image.setImageResource(R.mipmap.icon_holo); // Replace with your Icon
				holder.launcher_image.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                NewIconsFragment.zoomImageFromThumb(holder.launcher_image, 
		                		R.mipmap.banner_holo, // Replace with zoomed Image
		                		holder.container); 
		            }
		        });
				break;
			case 3:
				holder.title.setTextColor(context.getResources()
						.getColor(R.color.black)); // Change text color here
				holder.launcher_image.setImageResource(R.mipmap.icon_adw); // Replace with your Icon
				holder.launcher_image.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                NewIconsFragment.zoomImageFromThumb(holder.launcher_image, 
		                		R.mipmap.banner_adw, // Replace with zoomed Image
		                		holder.container); 
		            }
		        });
				break;
			case 4:
				holder.title.setTextColor(context.getResources()
						.getColor(R.color.black)); // Change text color here
				holder.launcher_image.setImageResource(R.mipmap.icon_al); // Replace with your Icon
				holder.launcher_image.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                NewIconsFragment.zoomImageFromThumb(holder.launcher_image, 
		                		R.mipmap.banner_al, // Replace with zoomed Image
		                		holder.container); 
		            }
		        });
				break;
			}
			holder.title.setText(entry.getTitle());
			
		return v;
	}


	
	/************************************************************************
	 ********************** Nothing more to see here ************************
	 ******************* DO NOT TOUCH BEYOND THIS POINT *********************
	 ************************************************************************/
	
	@Override
	public int getCount() {
		return gridItem.size();
	}

	@Override
	public Object getItem(int position) {
		return gridItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// Code added for better efficiency and less redraws
	public static class ViewHolder {
		public TextView title;
		public TouchHighlightImageButton launcher_image;
		public LinearLayout container;
	}
	
	public static class LauncherItem{
		String Title;
		int ID;
		
		public LauncherItem(String Title, int ID) {
			this.Title = Title;
			this.ID = ID;
		}
		
		public String getTitle() {
			return Title;
		}
		
		public int getID() {
			return ID;
		}
	}
}