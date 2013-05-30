package your.icons.name.here;

import your.icons.name.here.R;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.SherlockActivity;

public class AboutThemeActivity extends SherlockActivity {
	
	
	private ImageButton previous, next;
	
	private ViewFlipper page;

	   Animation animUpLeft;
	   Animation animUpRight;
	   Animation animDownLeft;
	   Animation animDownRight;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.theme_fragment);
	  
	  
      previous = (ImageButton) findViewById(R.id.previous);
      previous.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
            	  SwipeLeft();
              }	
      });
      
      next = (ImageButton) findViewById(R.id.next);
      next.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
            	  SwipeRight();
              }	
      });
      
      page = (ViewFlipper)findViewById(R.id.nowanim);

      animUpLeft = AnimationUtils.loadAnimation(this, R.anim.up_left);
      animUpRight = AnimationUtils.loadAnimation(this, R.anim.up_right);
      animDownLeft = AnimationUtils.loadAnimation(this, R.anim.out_left);
      animDownRight = AnimationUtils.loadAnimation(this, R.anim.out_right);
     
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
 
		@Override
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
			  finish();
		  }
}