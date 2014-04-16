package your.icons.name.here.adapter;

import java.util.List;

import your.icons.name.here.R;
import your.icons.name.here.util.Utils;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplyLauncherAdapter extends BaseAdapter
{
	private ColorMatrixColorFilter grayscaleFilter;
	private Context context;
	private List<Integer> gridItem;
	private Resources res;
	private Typeface tfTitle;

	// Flag Constants
	public static final int APEX = 0;
	public static final int NOVA = 1;
	public static final int AVIATE = 2;
	public static final int ADW = 3;
	public static final int ACTION = 4;
	public static final int SMART = 5;
	public static final int NEXT = 6;
	public static final int GO = 7;
	public static final int HOLO = 8;

	public ApplyLauncherAdapter(Context context, List<Integer> gridItem) {
		this.gridItem = gridItem;
		this.context = context;
		this.res = context.getResources();
		
		// Set up color filter
		ColorMatrix matrix = new ColorMatrix();
	    matrix.setSaturation(0); //0 means grayscale
	    this.grayscaleFilter = new ColorMatrixColorFilter(matrix);
		
		/* 
		 * Sets the font type for the title
		 * Make sure the font file is in the projects Asset folder
		 * Default for this template is Roboto-Thin
		 * themefont.ttf is the font the theme grabs also
		 */
		this.tfTitle = Typeface.createFromAsset(context.getAssets(),"themefont.ttf");
	}

	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder;
		int entry = gridItem.get(position);
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.apply_launcher_layout, null);
			
			holder = new ViewHolder();
			holder.title = (TextView) v.findViewById(R.id.title);
			holder.launcher_Image = (ImageView) v.findViewById(R.id.list_image);
			holder.txtInstalled = (TextView) v.findViewById(R.id.txtInstalled);
			
			v.setTag(holder);
		}
		else
			holder = (ViewHolder) v.getTag();
		
		holder.title.setTypeface(tfTitle);
		switch(entry)
		{
			case APEX:
				holder.title.setText(res.getString(R.string.launcher_apex));
				setInstalledStatus(holder, res.getString(R.string.launcher_apex_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_apex);
				break;
			case NOVA:
				holder.title.setText(res.getString(R.string.launcher_nova));
				setInstalledStatus(holder, res.getString(R.string.launcher_nova_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_nova);
				break;
			case AVIATE:
				holder.title.setText(res.getString(R.string.launcher_aviate));
				setInstalledStatus(holder, res.getString(R.string.launcher_aviate_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_aviate);
				break;
			case ADW:
				holder.title.setText(res.getString(R.string.launcher_adw));
				setInstalledStatus(holder, res.getString(R.string.launcher_adw_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_adw);
				break;
			case ACTION:
				holder.title.setText(res.getString(R.string.launcher_al));
				setInstalledStatus(holder, res.getString(R.string.launcher_al_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_al);
				break;
			case SMART:
				holder.title.setText(res.getString(R.string.launcher_smart));
				setInstalledStatus(holder, res.getString(R.string.launcher_smart_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_smart);
				break;
			case NEXT:
				holder.title.setText(res.getString(R.string.launcher_next));
				setInstalledStatus(holder, res.getString(R.string.launcher_next_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_next);
				break;
			case GO:
				holder.title.setText(res.getString(R.string.launcher_go));
				setInstalledStatus(holder, res.getString(R.string.launcher_go_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_go);
				break;
			case HOLO:
				holder.title.setText(res.getString(R.string.launcher_holo));
				setInstalledStatus(holder, res.getString(R.string.launcher_holo_package));
				holder.title.setTextColor(res.getColor(R.color.apply_launcher_text));
				holder.launcher_Image.setImageResource(R.mipmap.banner_holo);
				break;
		}
			
		return v;
	}
	
	private void setInstalledStatus(ViewHolder holder, String packageName)
	{
		// Set installed status
		if(Utils.isPackageInstalled(packageName, context))
		{
			holder.txtInstalled.setText(res.getString(R.string.installed));
			holder.txtInstalled.setTextColor(res.getColor(R.color.holo_green_light));
			holder.launcher_Image.clearColorFilter();
		}
		else
		{
			holder.txtInstalled.setText(res.getString(R.string.not_installed));
			holder.txtInstalled.setTextColor(res.getColor(R.color.holo_red_light));
			holder.launcher_Image.setColorFilter(grayscaleFilter);
		}
	}

	@Override
	public int getCount() {
		return gridItem.size();
	}

	@Override
	public Integer getItem(int position) {
		return gridItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// Code added for better efficiency and less redraws
	private class ViewHolder {
		public TextView title;
		public ImageView launcher_Image;
		public TextView txtInstalled;
	}
}