package gridview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import your.icons.name.here.R;
import adapters.RequestIconsAdapter;
import adapters.RequestIconsAdapter.AdapterItem;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class RequestIconsMain extends SherlockActivity {
	// Views
	private  GridView grid;
	private Button btnSubmit;
	
	// List of installed apps
	private int numSelected;
	private List<AdapterItem> appList;
	private RequestIconsAdapter appAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_main);

		initViews();
		loadAppList();
		initGrid();
		submitData();
	}
	
	private void initViews()
	{
		grid = (GridView) findViewById(R.id.grid);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	}

	private void loadAppList() {
		// Initialize app list
		appList = new ArrayList<AdapterItem>();
		numSelected = 0;
		
		// Create package manager and sort it
		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		Collections.sort(packages, new ApplicationInfo.DisplayNameComparator(pm));

		// Loops through package manager to find all installed apps
		String appCode = null;
		String appName = null;
		Drawable appIcon = null;
		for (ApplicationInfo packageInfo : packages) {
			// Examine only valid packages
			if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null && !pm.getLaunchIntentForPackage(packageInfo.packageName).equals("")) {

				// Get app icon
				appIcon = pm.getApplicationIcon(packageInfo);
				// Get app name
				appName = pm.getApplicationLabel(packageInfo).toString();
				// Get app launch intent
				String LaunchIntent = pm.getLaunchIntentForPackage(packageInfo.packageName).toString().split("cmp=")[1];
				
				// Trim code
				appCode = LaunchIntent.substring(0, LaunchIntent.length() - 1);
				if (appCode.split("/")[1].startsWith("."))
					appCode = appCode.split("/")[0] + "/" + appCode.split("/")[0] + appCode.split("/")[1];
				
				// Add App to List
				appList.add(new AdapterItem(appCode, appName, appIcon, false));
				
				// Nullify objects for memory
				appCode = null;
				appName = null;
				appIcon = null;
			}
		}
	}
	
	private void initGrid()
	{
		appAdapter = new RequestIconsAdapter(RequestIconsMain.this, appList);
		grid.setAdapter(appAdapter);
		grid.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long index) {
				AdapterItem app = appList.get(position);
				app.setSelected(!app.isSelected());
				if(app.isSelected())
					numSelected++;
				else
					numSelected--;
				appList.remove(position);
				appList.add(position, app);
				appAdapter.notifyDataSetChanged();
			}
		});
	}
	
	private void submitData()
	{
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(numSelected > 0)
					sendData();
				else
					Toast.makeText(RequestIconsMain.this, "No apps selected", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void sendData()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Hello " + getResources().getString(R.string.dev_name) + ",\n");
		builder.append("these icons are missing from your icon pack:\n\n");
		
		for(AdapterItem app : appList)
		{
			if(app.isSelected())
			{
				builder.append("Name: " + app.getName() + "\n");
				builder.append("Package: " + app.getCode().split("/")[0] + "\n");
				builder.append("Activity: " + app.getCode() + "\n");
				builder.append("Link: " + "https://play.google.com/store/apps/details?id=" + app.getCode().split("/")[0] + "\n");
				builder.append("_______________________\n\n");
			}
		}
		
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "the1dynasty.android@gmail.com" });
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Icon request for " + getResources().getString(R.string.app_name));
		emailIntent.putExtra(Intent.EXTRA_TEXT, builder.toString());
		emailIntent.setType("plain/text");
		
		try
		{
			startActivity(Intent.createChooser(emailIntent, "Contact Developer"));
		}
		catch (android.content.ActivityNotFoundException ex)
		{
	        Toast.makeText(RequestIconsMain.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	    }
	}
}
