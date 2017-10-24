package app.swiremedicine.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import app.swiremedicine.R;
import app.swiremedicine.view.fragment.Inform_Fragment;
import app.swiremedicine.view.fragment.Punch_card_Fragment;
import app.swiremedicine.view.fragment.Report_Fragment;
import app.swiremedicine.view.fragment.User_Fragment;


public class MainActivity extends AppCompatActivity{
    private BottomTabBar tabBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabBar= (BottomTabBar) findViewById(R.id.bar333);
        tabBar.init(getSupportFragmentManager())
                .addTabItem("打卡",R.mipmap.daka, R.mipmap.dakachushi, Punch_card_Fragment.class)
                .addTabItem("汇报",R.mipmap.huibaochushi,R.mipmap.huibao, Report_Fragment.class)
                .addTabItem("通知",R.mipmap.tongzhi,R.mipmap.tongzhichushi, Inform_Fragment.class)
                .addTabItem("我的",R.mipmap.my,R.mipmap.mychushi, User_Fragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(false) ;
    }



}
