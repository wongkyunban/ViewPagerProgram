package com.wong.viewpagerprogram;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager guide_vp;
    private List<View> views;
    private MyViewPagerAdapter adapter;
    private Button button;
    private ImageView[] dots;
    private int[] ids = {R.id.guide_iv1,R.id.guide_iv2,R.id.guide_iv3,R.id.guide_iv4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);

        initViews();
        initDots();
    }

    /*
    * 初始化欢迎的
    * */
    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(GuideActivity.this);
        views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.guide_01,null));
        views.add(inflater.inflate(R.layout.guide_02,null));
        views.add(inflater.inflate(R.layout.guide_03,null));
        views.add(inflater.inflate(R.layout.guide_04,null));

        adapter = new MyViewPagerAdapter(views);
        guide_vp = (ViewPager)findViewById(R.id.guide_vp);
        guide_vp.setAdapter(adapter);

        guide_vp.addOnPageChangeListener(this);


        /*
        * 这里要特别注意，第四个欢迎页上的按钮通过
        * button = (Button)findViewById(R.id.button)
        * 肯定是不行的，一定会报空指针，因为当前的view是R.layout.guide_activity
        * 必须在button所在的view进行findViewById查找才可以
        *
        * */
        button = (Button)views.get(3).findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    * 初始化点
    * */
    private void initDots(){
        dots = new ImageView[views.size()];
        for (int i = 0;i < ids.length;i++){
            dots[i] = (ImageView)findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0 ; i < ids.length;i++){
            if(position == i){
                dots[i].setImageResource(R.drawable.item_selected);
            }else{
                dots[i].setImageResource(R.drawable.item_unselected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
