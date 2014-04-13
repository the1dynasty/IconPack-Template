package your.icons.name.here.fragment;

import java.util.ArrayList;
import java.util.List;

import your.icons.name.here.R;
import your.icons.name.here.adapter.ApplyLauncherAdapter;
import your.icons.name.here.adapter.ApplyLauncherAdapter.LauncherItem;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

/**
 ** Some lines may be off a few numbers Just be sure you're in the general area
 **/

public class ApplyLauncherFragment extends SherlockFragment {

	GridView gridView;
	final List<LauncherItem> launcherStuff = new ArrayList<LauncherItem>();

	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container_launcher,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.launcher_behind, null);
		gridView = (GridView) view.findViewById(R.id.grid);
		return view;
	}

	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		launcherStuff.add(new LauncherItem("Apex", 0));
		launcherStuff.add(new LauncherItem("Nova", 1));
		launcherStuff.add(new LauncherItem("Holo", 2));
		launcherStuff.add(new LauncherItem("ADW", 3));
		launcherStuff.add(new LauncherItem("Action", 4));
		launcherStuff.add(new LauncherItem("Go", 5));
		launcherStuff.add(new LauncherItem("Next", 6));
		launcherStuff.add(new LauncherItem("Cancel", 7));

		ApplyLauncherAdapter adapter = new ApplyLauncherAdapter(getActivity(),
				launcherStuff);

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
				final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
				final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
				final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";
				final String ACTION_SET_THEME = "com.anddoes.launcher.SET_THEME";
				final String EXTRA_PACKAGE_NAME = "com.anddoes.launcher.THEME_PACKAGE_NAME";

				@SuppressWarnings("unused")
				MainFragment gridContentT = null;

				switch (position) {
				case 0:
					Intent apex = new Intent(ACTION_SET_THEME);
					apex.putExtra(EXTRA_PACKAGE_NAME, getSherlockActivity().getPackageName());
					apex.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					try {
						startActivity(apex);

						Toast applied = Toast.makeText(getSherlockActivity().getBaseContext(), 
								getResources().getString (R.string.finish_apply),
								Toast.LENGTH_LONG);
						applied.show();
					} catch (ActivityNotFoundException e) {
						Intent apexMarket = new Intent(Intent.ACTION_VIEW);
						apexMarket.setData(Uri
								.parse("market://details?id=com.anddoes.launcher"));
						startActivity(apexMarket);
						Toast failedApex = Toast
								.makeText(
										getSherlockActivity().getBaseContext(),
										getResources().getString (R.string.apex_market),
										Toast.LENGTH_SHORT);
						failedApex.show();
					}

					break;
				case 1:
					Intent nova = new Intent(ACTION_APPLY_ICON_THEME);
					nova.setPackage(NOVA_PACKAGE);
					nova.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
					nova.putExtra(EXTRA_ICON_THEME_PACKAGE,
							"your.icons.name.here");
					try {
						startActivity(nova);
					} catch (ActivityNotFoundException e) {
						Intent novaMarket = new Intent(Intent.ACTION_VIEW);
						novaMarket.setData(Uri
								.parse("market://details?id=com.teslacoilsw.launcher"));
						startActivity(novaMarket);
						Toast failedNova = Toast
								.makeText(
										getSherlockActivity().getBaseContext(),
										getResources().getString (R.string.nova_market),
										Toast.LENGTH_SHORT);
						failedNova.show();
					}
					break;
				case 2:
					Toast failedHolo = Toast.makeText(getSherlockActivity().getBaseContext(),
							getResources().getString (R.string.not_supported),
							Toast.LENGTH_LONG);
					failedHolo.show();
					break;
				case 3:
					Intent adw = new Intent("org.adw.launcher.SET_THEME");
					adw.putExtra("org.adw.launcher.theme.NAME",
							"your.icons.name.here");
					try {
						startActivity(Intent.createChooser(adw,
								"activating theme..."));
					} catch (ActivityNotFoundException e) {						
						Intent adwMarket = new Intent(Intent.ACTION_VIEW);
						adwMarket.setData(Uri
								.parse("market://details?id=org.adw.launcher"));
						startActivity(adwMarket);
						Toast failedADW = Toast
								.makeText(
										getSherlockActivity().getBaseContext(),
										getResources().getString (R.string.adw_market),
										Toast.LENGTH_SHORT);
						failedADW.show();
					} 
					((Activity) getSherlockActivity()).finish();
					break;
				case 4:
					Intent al = getSherlockActivity().getPackageManager().getLaunchIntentForPackage(
							"com.actionlauncher.playstore");
					if (al != null) {

						String packageName = "your.icons.name.here";
						al.putExtra("apply_icon_pack", packageName);
						startActivity(al);
					} else {
						Intent alMarket = new Intent(Intent.ACTION_VIEW);
						alMarket.setData(Uri
								.parse("market://details?id=com.actionlauncher.playstore"));
						startActivity(alMarket);
						Toast failedAL = Toast
								.makeText(
										getSherlockActivity().getBaseContext(), 
										getResources().getString (R.string.al_market),
										Toast.LENGTH_SHORT);
						failedAL.show();
					}
					break;
				case 5:
					Intent goApply = getSherlockActivity().getPackageManager().getLaunchIntentForPackage(
							"com.gau.go.launcherex");
					if (goApply != null) {
						Intent go = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
		                go.putExtra("type",1);
		                go.putExtra("pkgname", getSherlockActivity().getPackageName());
		                getSherlockActivity().sendBroadcast(go);
						Toast appliedGo = Toast
		                .makeText(getSherlockActivity().getBaseContext(), getResources().getString
		                		(R.string.go_applied), Toast.LENGTH_LONG);
						appliedGo.show();
						startActivity(goApply); 
				   } else {
						Intent goMarket = new Intent(Intent.ACTION_VIEW);
						goMarket.setData(Uri.parse("market://details?id=com.anddoes.launcher"));
						startActivity(goMarket);
						
						Toast failedGo = Toast
						.makeText(getSherlockActivity().getBaseContext(), getResources().getString 
								(R.string.go_market), Toast.LENGTH_SHORT);
						failedGo.show();
					}
					break;
				case 6:
					Toast failedNext = Toast.makeText(getSherlockActivity().getBaseContext(),
							getResources().getString (R.string.not_supported),
							Toast.LENGTH_LONG);
					failedNext.show();
					break;
				/* This is your cancel button
				 * Always leave this as the last item
				 */
				case 7:
					((Activity) getSherlockActivity()).finish();
					break;
				}
			}
		});
	}
}