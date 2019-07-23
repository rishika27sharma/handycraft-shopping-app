package com.strontech.imgautam.handycaft.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import com.strontech.imgautam.handycaft.R;

public class Converter {

  public static Drawable convertLayoutToImage(Context context, int count, int drawableId) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.badge_icon_layout, null);
    ((ImageView) view.findViewById(R.id.icon_badge)).setImageResource(drawableId);

    if (count == 0) {
      View counterTextPanel = view.findViewById(R.id.counterValuePanel);
      counterTextPanel.setVisibility(View.GONE);

    } else {
      TextView textView = view.findViewById(R.id.count);
      textView.setText("" + count);
    }

    view.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
        View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

    view.setDrawingCacheEnabled(true);
    view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
    view.setDrawingCacheEnabled(false);
    return new BitmapDrawable(context.getResources(), bitmap);

  }
}