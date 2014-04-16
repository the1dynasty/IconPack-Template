package your.icons.name.here.fragment;

import java.util.ArrayList;
import java.util.List;

import your.icons.name.here.R;
import your.icons.name.here.adapter.ApplyLauncherAdapter;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ApplyLauncherFragment extends Fragment {

	GridView gridView;
	Button btnCancel;
	final List<Integer> applyLauncher = new ArrayList<Integer>();

	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container_launcher,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.apply_launcher_behind, null);
		gridView = (GridView) view.findViewById(R.id.grid);
		return view;
	}

	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		applyLauncher.add(ApplyLauncherAdapter.APEX);
		applyLauncher.add(ApplyLauncherAdapter.NOVA);
		applyLauncher.add(ApplyLauncherAdapter.AVIATE);
		applyLauncher.add(ApplyLauncherAdapter.ADW);
		applyLauncher.add(ApplyLauncherAdapter.ACTION);
		applyLauncher.add(ApplyLauncherAdapter.SMART);
		applyLauncher.add(ApplyLauncherAdapter.NEXT);
		applyLauncher.add(ApplyLauncherAdapter.GO);
		applyLauncher.add(ApplyLauncherAdapter.HOLO);

		ApplyLauncherAdapter adapter = new ApplyLauncherAdapter(getActivity(),
				applyLauncher);

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
				final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
				final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
				final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";
				final String APEX_ACTION_SET_THEME = "com.anddoes.launcher.SET_THEME";
				final String APEX_EXTRA_PACKAGE_NAME = "com.anddoes.launcher.THEME_PACKAGE_NAME";
				final String AVIATE_ACTION_SET_THEME = "com.tul.aviate.SET_THEME";
				final String AVIATE_EXTRA_PACKAGE_NAME = "THEME_PACKAGE";

				@SuppressWarnings("unused")
				MainFragment gridContentT = null;

				switch (position) {
				case ApplyLauncherAdapter.APEX:
					Intent apex = new Intent(APEX_ACTION_SET_THEME);
					apex.putExtra(APEX_EXTRA_PACKAGE_NAME, getActivity().getPackageName());
					apex.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					try {
						startActivity(apex);

						Toast applied = Toast.makeText(getActivity().getBaseContext(), 
								getResources().getString (R.string.finish_apply),
								Toast.LENGTH_LONG);
						applied.show();
					} catch (ActivityNotFoundException e) {
						Intent apexMarket = new Intent(Intent.ACTION_VIEW);
						apexMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_apex_market)));
						startActivity(apexMarket);
						Toast failedApex = Toast
								.makeText(
										getActivity().getBaseContext(),
										getResources().getString (R.string.apex_market),
										Toast.LENGTH_SHORT);
						failedApex.show();
					}

					break;
				case ApplyLauncherAdapter.NOVA:
					Intent nova = new Intent(ACTION_APPLY_ICON_THEME);
					nova.setPackage(NOVA_PACKAGE);
                    nova.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
					nova.putExtra(EXTRA_ICON_THEME_PACKAGE,
							getResources().getString (R.string.package_name));
					try {
						startActivity(nova);
					} catch (ActivityNotFoundException e) {
						Intent novaMarket = new Intent(Intent.ACTION_VIEW);
						novaMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_nova_market)));
						startActivity(novaMarket);
						Toast failedNova = Toast
								.makeText(
										getActivity().getBaseContext(),
										getResources().getString (R.string.nova_market),
										Toast.LENGTH_SHORT);
						failedNova.show();
					}
					break;
				case ApplyLauncherAdapter.AVIATE:
					Intent intent = new Intent(AVIATE_ACTION_SET_THEME);
					  intent.putExtra(AVIATE_EXTRA_PACKAGE_NAME,
								getResources().getString (R.string.package_name));
					  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					  try {
					    startActivity(intent);
					  } catch (ActivityNotFoundException e) {						
							Intent adwMarket = new Intent(Intent.ACTION_VIEW);
							adwMarket.setData(Uri
									.parse(getResources().getString(R.string.launcher_aviate_market)));
							startActivity(adwMarket);
							Toast failedADW = Toast
									.makeText(
											getActivity().getBaseContext(),
											getResources().getString (R.string.aviate_market),
											Toast.LENGTH_SHORT);
							failedADW.show();
						} 
					break;
				case ApplyLauncherAdapter.ADW:
					Intent adw = new Intent("org.adw.launcher.SET_THEME");
					adw.putExtra("org.adw.launcher.theme.NAME",
							getResources().getString (R.string.package_name));
					try {
						startActivity(adw);
					} catch (ActivityNotFoundException e) {						
						Intent adwMarket = new Intent(Intent.ACTION_VIEW);
						adwMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_adw_market)));
						startActivity(adwMarket);
						Toast failedADW = Toast
								.makeText(
										getActivity().getBaseContext(),
										getResources().getString (R.string.adw_market),
										Toast.LENGTH_SHORT);
						failedADW.show();
					} 
					((Activity) getActivity()).finish();
					break;
				case ApplyLauncherAdapter.ACTION:
					Intent al = getActivity().getPackageManager().getLaunchIntentForPackage(
							"com.actionlauncher.playstore");
					if (al != null) {

						String packageName = getResources().getString (R.string.package_name);
						al.putExtra("apply_icon_pack", packageName);
						startActivity(al);
					} else {
						Intent alMarket = new Intent(Intent.ACTION_VIEW);
						alMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_al_market)));
						startActivity(alMarket);
						Toast failedAL = Toast
								.makeText(
										getActivity().getBaseContext(), 
										getResources().getString (R.string.al_market),
										Toast.LENGTH_SHORT);
						failedAL.show();
					}
					break;
				case ApplyLauncherAdapter.SMART:
					Intent smart = new Intent("ginlemon.smartlauncher.setGSLTHEME");
					smart.putExtra("package", getResources().getString (R.string.package_name));
					smart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  		
			  		try {
			  			startActivity(smart);
			   		} catch (ActivityNotFoundException e) {
			  					  			
			  			Intent smartMarket = new Intent(Intent.ACTION_VIEW);
						smartMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_smart_market)));
						startActivity(smartMarket);
						
			  			Toast failedSmart = Toast
								.makeText(
										getActivity().getBaseContext(), 
										getResources().getString (R.string.smart_market), 
										Toast.LENGTH_SHORT);
								failedSmart.show();
			   		}	
			  		break;
				case ApplyLauncherAdapter.NEXT:
					Intent nextApply = getActivity().getPackageManager().getLaunchIntentForPackage(
							"com.gtp.nextlauncher");
					if (nextApply != null) {
						Intent go = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
		                go.putExtra("type",1);
		                go.putExtra("pkgname", getResources().getString (R.string.package_name));
		                getActivity().sendBroadcast(go);
						Toast appliedGo = Toast
		                .makeText(getActivity().getBaseContext(), getResources().getString
		                		(R.string.finish_apply), Toast.LENGTH_LONG);
						appliedGo.show();
						startActivity(nextApply);
				   } else {
						Intent nextMarket = new Intent(Intent.ACTION_VIEW);
						nextMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_next_market)));
						startActivity(nextMarket);
	
						Toast failedGo = Toast
						.makeText(getActivity().getBaseContext(), getResources().getString 
								(R.string.next_market), Toast.LENGTH_SHORT);
						failedGo.show();
					}
					break;
				case ApplyLauncherAdapter.GO:
					Intent goApply = getActivity().getPackageManager().getLaunchIntentForPackage(
							"com.gau.go.launcherex");
					if (goApply != null) {
						Intent go = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
		                go.putExtra("type",1);
		                go.putExtra("pkgname", getResources().getString (R.string.package_name));
		                getActivity().sendBroadcast(go);
						Toast appliedGo = Toast
		                .makeText(getActivity().getBaseContext(), getResources().getString
		                		(R.string.finish_apply), Toast.LENGTH_LONG);
						appliedGo.show();
						startActivity(goApply); 
				   } else {
						Intent goMarket = new Intent(Intent.ACTION_VIEW);
						goMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_go_market)));
						startActivity(goMarket);
	
						Toast failedGo = Toast
						.makeText(getActivity().getBaseContext(), getResources().getString 
								(R.string.go_market), Toast.LENGTH_SHORT);
						failedGo.show();
					}
					break;
					
				case ApplyLauncherAdapter.HOLO:
					Intent holo = new Intent(Intent.ACTION_MAIN);
					holo.setComponent(new ComponentName("com.mobint.hololauncher",
							"com.mobint.hololauncher.SettingsActivity"));

					try{startActivity(holo);

					Toast applied = Toast.makeText(getActivity().getBaseContext(), 
							getResources().getString (R.string.finish_holo_apply),
							Toast.LENGTH_LONG);
					applied.show();

					}catch(ActivityNotFoundException e) {
						Intent holoMarket = new Intent(Intent.ACTION_VIEW);
						holoMarket.setData(Uri
								.parse(getResources().getString(R.string.launcher_holo_market)));
						startActivity(holoMarket);
						Toast failedHolo = Toast
								.makeText(
										getActivity().getBaseContext(),
										getResources().getString (R.string.holo_market),
										Toast.LENGTH_SHORT);
						failedHolo.show();
					}
					break;
				}
			}
		});

		//Cancels and returns to main screen of app
		  btnCancel = (Button) getView().findViewById(R.id.btnCancel);
		  btnCancel.setOnClickListener(new OnClickListener() {
		          public void onClick(View v) {
		        	  ((Activity) getActivity()).finish();
		          }	
	      });
	}
}