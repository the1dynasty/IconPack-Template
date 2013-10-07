package adapters;

import java.util.List;

import your.icons.name.here.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestAdapter extends BaseAdapter {
	private Context context;
	private List<AdapterItem> gridItem;

	public RequestAdapter(Context context, List<AdapterItem> gridItem) {
		this.gridItem = gridItem;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		AdapterItem entry = gridItem.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.request_item, null);

			holder = new ViewHolder();
			holder.txtCode = (TextView) convertView.findViewById(R.id.txtCode);
			holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
			holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
			holder.chkSelected = (CheckBox) convertView.findViewById(R.id.chkSelected);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtCode.setText(entry.getCode());
		holder.txtName.setText(entry.getName());
		holder.imgIcon.setImageDrawable(entry.getImage());
		holder.chkSelected.setChecked(entry.isSelected());

		return convertView;
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
		public TextView txtCode;
		public TextView txtName;
		public ImageView imgIcon;
		public CheckBox chkSelected;
	}

	public static class AdapterItem {
		String Code;
		String Name;
		Drawable Image;
		Boolean Selected;

		/** CONSTRUCTORS **/
		public AdapterItem() {
			this.Code = null;
			this.Name = null;
			this.Image = null;
			this.Selected = null;
		}

		public AdapterItem(String Code, String Name, Drawable Image,
				Boolean Selected) {
			this.Code = Code;
			this.Name = Name;
			this.Image = Image;
			this.Selected = Selected;
		}

		/** SETTERS **/
		public void setCode(String Code) {
			this.Code = Code;
		}

		public void setName(String Name) {
			this.Name = Name;
		}

		public void setImage(Drawable Image) {
			this.Image = Image;
		}

		public void setSelected(boolean Selected) {
			this.Selected = Selected;
		}

		/** GETTERS **/
		public String getCode() {
			return Code;
		}

		public String getName() {
			return Name;
		}

		public Drawable getImage() {
			return Image;
		}

		public boolean isSelected() {
			return Selected;
		}
	}
}