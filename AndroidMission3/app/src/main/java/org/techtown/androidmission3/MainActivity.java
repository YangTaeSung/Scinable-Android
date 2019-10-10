package org.techtown.androidmission3;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    ScrollView scrollView2;
    ImageView imageView1;
    ImageView imageView2;
    BitmapDrawable bitmap1;
    BitmapDrawable bitmap2;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        scrollView.setHorizontalScrollBarEnabled(true);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        scrollView2 = findViewById(R.id.scrollView2);
        scrollView2.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap1 = (BitmapDrawable)res.getDrawable(R.drawable.a);
        int bitmapWidth = bitmap1.getIntrinsicWidth();
        int bitmapHeight = bitmap1.getIntrinsicHeight();

        imageView1.setImageDrawable(bitmap1);
        imageView1.getLayoutParams().width = bitmapWidth;
        imageView1.getLayoutParams().height = bitmapHeight;

        Resources res2 = getResources();
        bitmap2 = (BitmapDrawable)res2.getDrawable(R.drawable.b);
        int bitmapWidth2 = bitmap2.getIntrinsicWidth();
        int bitmapHeight2 = bitmap2.getIntrinsicHeight();

        imageView2.setImageDrawable(bitmap2);
        imageView2.getLayoutParams().width = bitmapWidth2;
        imageView2.getLayoutParams().height = bitmapHeight2;

    }

    public void onButtonClicked(View v) {
        buttonChanged();
    }

    private void buttonChanged() {
        if ( flag == 0) {
            scrollView = findViewById(R.id.scrollView);
            imageView1 = findViewById(R.id.imageView1);
            imageView2 = findViewById(R.id.imageView2);
            scrollView.setHorizontalScrollBarEnabled(true);

            Resources res = getResources();
            bitmap1 = (BitmapDrawable)res.getDrawable(R.drawable.a);
            int bitmapWidth = bitmap1.getIntrinsicWidth();
            int bitmapHeight = bitmap1.getIntrinsicHeight();

            imageView2.setImageDrawable(bitmap1);
            imageView2.getLayoutParams().width = bitmapWidth;
            imageView2.getLayoutParams().height = bitmapHeight;

            Resources res2 = getResources();
            bitmap2 = (BitmapDrawable)res2.getDrawable(R.drawable.b);
            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView1.setImageDrawable(bitmap2);
            imageView1.getLayoutParams().width = bitmapWidth2;
            imageView1.getLayoutParams().height = bitmapHeight2;

            flag = 1;
        }
        else {
            scrollView = findViewById(R.id.scrollView);
            imageView1 = findViewById(R.id.imageView1);
            imageView2 = findViewById(R.id.imageView2);
            scrollView.setHorizontalScrollBarEnabled(true);

            Resources res = getResources();
            bitmap1 = (BitmapDrawable)res.getDrawable(R.drawable.a);
            int bitmapWidth = bitmap1.getIntrinsicWidth();
            int bitmapHeight = bitmap1.getIntrinsicHeight();

            imageView1.setImageDrawable(bitmap1);
            imageView1.getLayoutParams().width = bitmapWidth;
            imageView1.getLayoutParams().height = bitmapHeight;

            Resources res2 = getResources();
            bitmap2 = (BitmapDrawable)res2.getDrawable(R.drawable.b);
            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView2.setImageDrawable(bitmap2);
            imageView2.getLayoutParams().width = bitmapWidth2;
            imageView2.getLayoutParams().height = bitmapHeight2;

            flag = 0;
        }
    }


}
