package com.ioreno.grecoantoine.ioreno;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        drawPie();

}

public void drawPie(){
    AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
    AnimatedPieViewConfig config = new AnimatedPieViewConfig();
    config.startAngle(-90)// Starting angle offset
            //put row values here, does math antumatically
            .addData(new SimplePieInfo(30, Color.parseColor("#FF0055"), "30"))//Data (bean that implements the IPieInfo interface)
            .addData(new SimplePieInfo(18.0f, Color.parseColor("#03C0FF"), "18")).drawText(true)
      .duration(2000).textSize(50).canTouch(true);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
    mAnimatedPieView.applyConfig(config);
    mAnimatedPieView.start();}
}

