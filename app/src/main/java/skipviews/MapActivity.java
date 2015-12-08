package skipviews;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.linghang.rongtoudai.BaseApplication;
import com.linghang.rongtoudai.R;

/**
 * Created by caixiao on 2015/12/1 0001.
 */
public class MapActivity extends Activity {

    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
    private MyLocationConfiguration.LocationMode mlocationMode = MyLocationConfiguration.LocationMode.NORMAL;
    private volatile boolean isFristLocation = true;
    private MapView mvMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mvMap = (MapView) findViewById(R.id.baidumapview);
        initMyLocation();
    }

    private void initMyLocation() {
        mLocationClient = new LocationClient(BaseApplication.getContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setOpenGps(true);
        locationClientOption.setAddrType("bd09ll");
        locationClientOption.setScanSpan(1000);
        mLocationClient.setLocOption(locationClientOption);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mvMap.onPause();
    }

    @Override
    protected void onDestroy() {
        mvMap = null;
        super.onDestroy();
    }
}
