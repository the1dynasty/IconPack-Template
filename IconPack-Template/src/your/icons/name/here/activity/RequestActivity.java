package your.icons.name.here.activity;

import java.util.List;

import your.icons.name.here.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.pk.requestmanager.AppInfo;
import com.pk.requestmanager.PkRequestManager;
import com.pk.requestmanager.RequestSettings;

public class RequestActivity extends SherlockActivity
{
	// Request Manager
	private PkRequestManager mRequestManager;
	
	// App List
	private List<AppInfo> mApps;
	
	// List & Adapter
	private ListView mList;
	private ListAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_main);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Grab a reference to the manager and store it in a variable. This helps make code shorter.
		mRequestManager = PkRequestManager.getInstance(this);
		
		// Enable debugging. Disable this during production!
		mRequestManager.setDebugging(true);
		
		// Set your custom settings. Email address is required! Everything else is set to default.
		mRequestManager.setSettings(new RequestSettings.Builder()
		.addEmailAddress(getResources().getString(R.string.dev_email))
		.build());
		
		// Load the list of apps if none are loaded. This should normally be done asynchronously.
		mRequestManager.loadAppsIfEmpty();
		
		// Get the list of apps
		mApps = mRequestManager.getApps();
		
		// Populate your ListView with your apps
		mList = (ListView) findViewById(R.id.appList);
		mAdapter = new ListAdapter(this, mApps);
		mList.setAdapter(mAdapter);
		
		// Set basic listener to your ListView
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Mark the app as selected
				AppInfo mApp = mApps.get(position);
				mApp.setSelected(!mApp.isSelected());
				mApps.set(position, mApp);
				
				// Let the adapter know you selected something
				mAdapter.notifyDataSetChanged();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getSupportMenuInflater().inflate(R.menu.request, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				finish();
				return true;
			case R.id.submitButton:
				// Small workaround
				mRequestManager.setActivity(this);
				
				// Build and send the request in the background.
				mRequestManager.sendRequestAsync();
				Toast.makeText(this, getString(R.string.building_request), Toast.LENGTH_LONG).show();
				
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	// You should probably put this in a separate .java file
	private class ListAdapter extends BaseAdapter
	{
		private Context mContext;
		private List<AppInfo> mApps;
		
		public ListAdapter(Context context, List<AppInfo> apps)
		{
			this.mContext = context;
			this.mApps = apps;
		}
		
		@Override
		public int getCount()
		{
			return mApps.size();
		}

		@Override
		public AppInfo getItem(int position)
		{
			return mApps.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder;
			AppInfo mApp = mApps.get(position);
			
			if (convertView == null)
			{
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.activity_request_item, null);
				
				holder = new ViewHolder();
				holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
				holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
				holder.chkSelected = (CheckBox) convertView.findViewById(R.id.chkSelected);
				
				convertView.setTag(holder);
			}
			else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.txtName.setText(mApp.getName());
			holder.imgIcon.setImageDrawable(mApp.getImage());
			holder.chkSelected.setChecked(mApp.isSelected());
			
			return convertView;
		}
		
		private class ViewHolder
		{
			public ImageView imgIcon;
			public TextView txtName;
			public CheckBox chkSelected;
		}
	}
}
