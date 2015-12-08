package skipviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.linghang.rongtoudai.R;

import baseuitls.MyScrollView;

/**
 * Created by caixiao on 2015/12/3 0003.
 */
public class MyScrollViewTestActivity extends Activity implements MyScrollView.OnScrollListener {

    private MyScrollView myScrollView;
    private ImageView ivTopBuy;
    private ImageView ivBottomBuy;
    private LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myscrollviewtest);

        myScrollView = (MyScrollView) findViewById(R.id.mysv_test);
        ivTopBuy = (ImageView) findViewById(R.id.iv_topbuy);
        ivBottomBuy = (ImageView) findViewById(R.id.iv_bottombuy);
        llMain = (LinearLayout) findViewById(R.id.ll_main);

        myScrollView.setOnScrollListener(this);

        llMain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(myScrollView.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, ivBottomBuy.getTop());
        ivTopBuy.layout(0, mBuyLayout2ParentTop, ivTopBuy.getWidth(), mBuyLayout2ParentTop + ivTopBuy.getHeight());
    }
}
