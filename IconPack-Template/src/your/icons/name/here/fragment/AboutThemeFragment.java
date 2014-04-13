package your.icons.name.here.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import your.icons.name.here.R;
import your.icons.name.here.activity.AboutThemeMain;

public class AboutThemeFragment extends AboutThemeMain {
	
	
	private ImageButton previous, next;
	
	private ViewFlipper page;

	   Animation animUpLeft;
	   Animation animUpRight;
	   Animation animDownLeft;
	   Animation animDownRight;
	
	   @Override
	   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		   return inflater.inflate(R.layout.theme_fragment, container, false);
		  	   
	   }
	   
	@Override
	  public void onStart() {
	  super.onStart();

      Typeface font1 = Typeface.createFromAsset(getSherlockActivity().getAssets(), "RobotoSlab-Regular.ttf");
      TextView desc1 = (TextView) getSherlockActivity().findViewById(R.id.description1);
      desc1.setTypeface(font1);
	
	  
      previous = (ImageButton) getView().findViewById(R.id.previous);
      previous.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
            	  SwipeRight();
              }	
      });
      
      next = (ImageButton) getView().findViewById(R.id.next);
      next.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
            	  SwipeLeft();
              }	
      });
      
      page = (ViewFlipper)getView().findViewById(R.id.nowanim);

      animUpLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.plus_page_in_right);
      animUpRight = AnimationUtils.loadAnimation(getActivity(), R.anim.plus_page_in_left);
      animDownLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.plus_page_out_right);
      animDownRight = AnimationUtils.loadAnimation(getActivity(), R.anim.plus_page_out_left);
     
  }
		
		private void SwipeRight(){
			page.setInAnimation(animUpRight);
			page.setOutAnimation(animDownRight);
			page.showPrevious();
  }
 
		private void SwipeLeft(){
			page.setInAnimation(animUpLeft);
			page.setOutAnimation(animDownLeft);
			page.showNext();
  }
 
		public boolean onTouchEvent(MotionEvent event) {
			return gestureDetector.onTouchEvent(event);
}

		SimpleOnGestureListener simpleOnGestureListener
		= new SimpleOnGestureListener(){

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY) {

				float sensitvity = 50;
				if((e1.getX() - e2.getX()) > sensitvity){
					SwipeLeft();
				}else if((e2.getX() - e1.getX()) > sensitvity){
					SwipeRight();
				}
				return true;
			}
		};
 
		@SuppressWarnings("deprecation")
		GestureDetector gestureDetector
		= new GestureDetector(simpleOnGestureListener);
		{
	}
		@Override
		  public void onPause(){
			  super.onPause();
			  //finish();
		  }
}