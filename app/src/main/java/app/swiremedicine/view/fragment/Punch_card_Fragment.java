package app.swiremedicine.view.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import app.swiremedicine.R;
import app.swiremedicine.view.CastomView.MyButton;
import app.swiremedicine.view.CastomView.MyButton2;

/**
 * Created by 王坤 on 2017/10/23.
 */

/**
 * 郝建澄 打卡页面
 * 2017/10/25
 */
public class Punch_card_Fragment extends Fragment {
    private TextView dingweitv;
    public AMapLocationClient mLocationClient = null;
    private MyButton shangbandaka;
    private MyButton2 chuchadaka;
    //permission.ACCESS_FINE_LOCATION定位权限
    public static final String LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private static final int GO_TO_SETTING_REQUEST_CODE = 10;
    public static String TAG = "permission_tag";

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            // TODO Auto-generated method stub
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    double latitude = amapLocation.getLatitude();//获取纬度
                    String city = amapLocation.getCity();//城市
                    String district = amapLocation.getDistrict();//城区信息

//                    Log.e("Amap==经度：纬度", "locationType:"+locationType+",latitude:"+latitude+district+street+streetNum+cityCode
//                    +adCode+aoiName+buildingId+floor);
                    dingweitv.setText("精度："+locationType+"--维度："+latitude+" ---城市："+city+"城区信息："+district);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError","location Error, ErrCode:"
//                            + amapLocation.getErrorCode() + ", errInfo:"
//                            + amapLocation.getErrorInfo());
                }
            }

        }
    };

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.punch_card_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dingweitv=getActivity().findViewById(R.id.dingweitv);

        if (ContextCompat.checkSelfPermission(getActivity(), LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "===========检查权限---用户已经拥有定位这个权限了");

        } else {
            Log.e(TAG, "===========检查权限---用户没有定位这个权限");
            ActivityCompat.requestPermissions(getActivity(), new String[]{LOCATION_PERMISSION}, LOCATION_PERMISSION_REQUEST_CODE);
        }


        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocationLatest(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

        /**
         * 获取一次定位
         */
        //该方法默认为false，true表示只定位一次
        mLocationOption.setOnceLocation(true);
        getdaka();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GO_TO_SETTING_REQUEST_CODE:
                if (ContextCompat.checkSelfPermission(getActivity(), LOCATION_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "===========设置页面返回之后-再次检查权限---用户已经拥有定位这个权限了");
                } else {
                    Log.e(TAG, "===========设置页面返回之后-再次检查权限---用户没有开启这个权限，在这不用再去请求权限了");
                }
                break;
        }


    }

    private void getdaka() {
        shangbandaka=getActivity().findViewById(R.id.sahngbandaka);
        chuchadaka=getActivity().findViewById(R.id.chuchaidaka);

        shangbandaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "上班打卡", Toast.LENGTH_SHORT).show();
            }
        });
        chuchadaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "出差打卡", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (permissions[0].equals(LOCATION_PERMISSION)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "===========权限回调---用户同意了");
                    } else {
                        Log.e(TAG, "===========权限回调---用户拒绝了");

                        //// TODO: 2017/10/16
                        //如果有系统权限弹窗的话，就不用去解释权限了直接告诉用户怎么设置就行
//                        showTipGoSetting();
                        //如果没有系统权限弹窗的话，需要解释一下需要什么权限
//                        showTipExplainPermission();

                        /**
                         * 如 果系统不再解释权限，我们去解释权限 （用户勾选了不再询问，系统就不会再解释权限了）
                         */
                        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),LOCATION_PERMISSION)){
                            Log.e(TAG,"=========== shouldShowRequestPermissionRationale 返回值为 true");

                            //返回tue 因为系统刚刚有权限弹窗，所以不用解释了，直接告诉用户如何开启权限
                            showTipGoSetting();
                        }else {
                            //返回false ，用户勾选了  不再询问，之后系统也不会再弹出系统权限弹框，所以我们自己弹框解释
                            Log.e(TAG,"=========== shouldShowRequestPermissionRationale 返回值为 false");
                            showTipExplainPermission();

                        }
                    }
                }
                break;
        }
    }

    private void showTipExplainPermission() {
        new AlertDialog.Builder(getActivity())
                .setTitle("说明")
                .setMessage("需要定位权限，去开起")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //告诉用户怎么去打开权限
                        showTipGoSetting();

                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    private void showTipGoSetting() {
        new AlertDialog.Builder(getActivity())
                .setTitle("需要打开定位权限")
                .setMessage("在设置-权限中去打开定位权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //告诉用户怎么去打开权限
                        goToSetting();
                    }


                })

                .setNegativeButton("取消",null)
                .show();

    }

    private void goToSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, GO_TO_SETTING_REQUEST_CODE);
    }

//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        super.onDestroy();
//
//        mLocationClient.stopLocation();
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();
    }


    public String getPackageName() {
        return LOCATION_PERMISSION;
    }
}
