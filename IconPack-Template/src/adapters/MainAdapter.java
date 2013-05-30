package adapters;

import java.util.List;

import your.icons.name.here.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MainAdapter extends BaseAdapter{
	private Context context;
	private List<AdapterItem> gridItem;

	public MainAdapter(Context context, List<AdapterItem> gridItem) {
		this.gridItem = gridItem;
		this.context = context;
	}

	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder;
		AdapterItem entry = gridItem.get(position);
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = inflater.inflate(R.layout.gridview_layout, null);
			
			holder = new ViewHolder();
			holder.title = (TextView) v.findViewById(R.id.title);
			holder.text = (TextView) v.findViewById(R.id.description);
			holder.icon_Image = (ImageView) v.findViewById(R.id.list_image);
			
			v.setTag(holder);
		}
		else {
			holder = (ViewHolder) v.getTag();
		}
			holder.title.setText(entry.getTitle());
			
			/* Sets the descriptions text color
			 * You can reference any color in the colors.xml and even add some
			 * You can also individually set the color for each ListView by 
			 * referencing another color
			 */
			switch(entry.getID()){
			case 0:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 1:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 2:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 3:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 4:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 5:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 6:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 7:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 8:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 9:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			case 10:
				holder.text.setTextColor(context.getResources()
						.getColor(R.color.list_desc_color));
				break;
			}
			holder.text.setText(entry.getDescription());
			
			
			// Sets the icon image for each gridview
			switch(entry.getID()){
			case 0:
				holder.icon_Image.setImageResource(R.drawable.icon_oss);
				break;
			case 1:
				holder.icon_Image.setImageResource(R.drawable.icon_info);
				break;
			case 2:
				holder.icon_Image.setImageResource(R.drawable.icon_launcher);
				break;
			case 3:
				holder.icon_Image.setImageResource(R.drawable.icon_wall);
				break;
			case 4:
				holder.icon_Image.setImageResource(R.drawable.icon_rate);
				break;
			case 5:
				holder.icon_Image.setImageResource(R.drawable.icon_community);
				break;
			case 6:
				holder.icon_Image.setImageResource(R.drawable.icon_gplus);
				break;
			case 7:
				holder.icon_Image.setImageResource(R.drawable.icon_share);
				break;
			case 8:
				holder.icon_Image.setImageResource(R.drawable.icon_email);
				break;
			case 9:
				holder.icon_Image.setImageResource(R.drawable.icon_dev_logo);
				break;
			case 10:
				holder.icon_Image.setImageResource(R.drawable.icon_wallet);
				break;
			}

		return v;
	}

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
	
	public static class ViewHolder {
		public TextView title;
		public TextView text;
		public ImageView icon_Image;
	}
	
	public static class AdapterItem{
		String Title;
		String Description;
		int ID;
		
		public AdapterItem(String Title, String Description, int ID) {
			this.Title = Title;
			this.Description = Description;
			this.ID = ID;
		}
		
		public String getTitle() {
			return Title;
		}
		
		public String getDescription() {
			return Description;
		}
		
		public int getID() {
			return ID;
		}
	}
}