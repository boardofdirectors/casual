package app.swiremedicine.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import app.swiremedicine.R;
import app.swiremedicine.view.fragment.Inform_Fragment;
import app.swiremedicine.view.fragment.Punch_card_Fragment;
import app.swiremedicine.view.fragment.Report_Fragment;
import app.swiremedicine.view.fragment.User_Fragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout Framelayout_main;
    private RadioButton rad_punch_card;
    private RadioButton rad_report;
    private RadioButton rad_inform;
    private RadioButton rad_user;
    private Punch_card_Fragment punchCardFragment;
    private Report_Fragment reportFragment;
    private Inform_Fragment informFragment;
    private User_Fragment userFragment;
    private FragmentTransaction ft;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        Framelayout_main = (FrameLayout) findViewById(R.id.Framelayout_main);
        rad_punch_card = (RadioButton) findViewById(R.id.rad_punch_card);
        rad_report = (RadioButton) findViewById(R.id.rad_report);
        rad_inform = (RadioButton) findViewById(R.id.rad_inform);
        rad_user = (RadioButton) findViewById(R.id.rad_user);
        //创建相应的fragment
        punchCardFragment = new Punch_card_Fragment();
        reportFragment = new Report_Fragment();
        informFragment = new Inform_Fragment();
        userFragment = new User_Fragment();
        //开启事物
        supportFragmentManager = getSupportFragmentManager();
        ft = supportFragmentManager.beginTransaction();
        //默认显示首页
        ft.add(R.id.Framelayout_main, punchCardFragment, "打卡");
        /**
         *
         * 提交
         */
        ft.commit();
        //设置radiobutton的点击事件
        rad_punch_card.setOnClickListener(this);
        rad_report.setOnClickListener(this);
        rad_inform.setOnClickListener(this);
        rad_user.setOnClickListener(this);
        rad_punch_card.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        ft = supportFragmentManager.beginTransaction();
        switch (view.getId()) {
            /**
             * 判断是否添加进去布局
             * 如果没有那就添加布局
             * 否则就显示布局提交事物
             */
            case R.id.rad_punch_card:
                if (!punchCardFragment.isAdded()) {
                    ft.add(R.id.Framelayout_main, punchCardFragment, "打卡界面");
                }
                ft.show(punchCardFragment).hide(reportFragment).hide(informFragment).hide(userFragment);
                ft.commit();
                break;
            case R.id.rad_report:
                if (!reportFragment.isAdded()) {
                    ft.add(R.id.Framelayout_main, reportFragment, "汇报界面");
                }
                ft.show(reportFragment).hide(punchCardFragment).hide(informFragment).hide(userFragment);
                ft.commit();
                break;
            case R.id.rad_inform:
                if (!informFragment.isAdded()) {
                    ft.add(R.id.Framelayout_main, informFragment, "通知界面");
                }
                ft.show(informFragment).hide(punchCardFragment).hide(reportFragment).hide(userFragment);
                ft.commit();
                break;
            case R.id.rad_user:
                if (!userFragment.isAdded()) {
                    ft.add(R.id.Framelayout_main, userFragment, "我的界面");
                }
                ft.show(userFragment).hide(punchCardFragment).hide(reportFragment).hide(informFragment);
                ft.commit();
                break;
            default:
                break;
        }

    }
}
