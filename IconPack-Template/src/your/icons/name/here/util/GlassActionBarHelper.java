/*
 * Copyright (C) 2013 Manuel Peinado and the1dynasty
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package your.icons.name.here.util;

import your.icons.name.here.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;

public class GlassActionBarHelper implements OnGlobalLayoutListener {
    private int contentLayout;
    private FrameLayout frame;
    private View content;

    public GlassActionBarHelper contentLayout(int layout) {
        this.contentLayout = layout;
        return this;
    }

    public View createView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        frame = (FrameLayout) inflater.inflate(R.layout.gab__frame, null);
        content = inflater.inflate(contentLayout, (ViewGroup) frame, false);
        frame.addView(content, 0);

        frame.getViewTreeObserver().addOnGlobalLayoutListener(this);

        return frame;
    }

    @Override
    public void onGlobalLayout() {
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(frame.getWidth(), MeasureSpec.AT_MOST);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(LayoutParams.WRAP_CONTENT, MeasureSpec.EXACTLY);
        content.measure(widthMeasureSpec, heightMeasureSpec);
    }
}