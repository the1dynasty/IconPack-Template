/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */

package your.icons.name.here.fragment;

import java.util.ArrayList;

import your.icons.name.here.R;
import your.icons.name.here.util.IconsProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

	public class IconFragmentMisc extends Fragment implements AdapterView.OnItemClickListener{
	    private static final String RESULT_OK = null;
		public Uri CONTENT_URI;

	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.all_icons_main, container, false);
	        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
	        GridView gridview = (GridView) view.findViewById(R.id.icon_grid);
	        gridview.setAdapter(new IconAdapter(getActivity(), iconSize));
	        CONTENT_URI=Uri.parse("content://"+IconsProvider.class.getCanonicalName());
			return view;
			
	    }

		@Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        String icon=adapterView.getItemAtPosition(i).toString();
	        Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,icon));
	        setResult(RESULT_OK, result);
	        finish();
	    }
	    private void setResult(String resultOk, Intent result) {

		}
		private void finish() {

		}
		
		private class IconAdapter extends BaseAdapter{
			private Context mContext;
			public IconAdapter(Context mContext, int iconsize) {
				this.mContext = mContext;
				loadIcon();
			}

	        @Override
	        public int getCount() {
	            return mThumbs.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return mThumbs.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView imageView;
	            if (convertView == null) {
	                imageView = new ImageView(mContext);
	                imageView.setLayoutParams(new GridView.LayoutParams(144, 144));
	            } else {
	                imageView = (ImageView) convertView;
	            }
	            imageView.setImageResource(mThumbs.get(position));
	            return imageView;
			}

	        private ArrayList<Integer> mThumbs;
	        ////////////////////////////////////////////////
	        private void loadIcon() {
	            mThumbs = new ArrayList<Integer>();

	            final Resources resources = getResources();
	            final String packageName = getActivity().getApplication().getPackageName();
/** TODO
 * Change array
 */
	            addIcon(resources, packageName, R.array.miscicons);

	        }
	        private void addIcon(Resources resources, String packageName, int list) {
	            final String[] extras = resources.getStringArray(list);
	            for (String extra : extras) {
	                int res = resources.getIdentifier(extra, "drawable", packageName);
	                if (res != 0) {
	                    final int thumbRes = resources.getIdentifier(extra,"drawable", packageName);
	                    if (thumbRes != 0) {
	                        mThumbs.add(thumbRes);
	                    }
	                }
	            }
	        }

	    }
	}
