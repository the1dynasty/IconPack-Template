package your.icons.name.here;

import your.icons.name.here.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class AboutThemeMain extends SherlockFragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.theme_main, container, false);
        
        //my_custom_layout is your own layout that will go on the top view, not behind      
       
        return rootView;
    }
    
}
