package com.test.zhaoziliang.mymomentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_moment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_moment = (TextView) findViewById(R.id.tv_moment);
        tv_moment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_moment:
                startActivity(new Intent(MainActivity.this, MomentActivity.class));
                break;
            default:
                break;
        }
    }
}
