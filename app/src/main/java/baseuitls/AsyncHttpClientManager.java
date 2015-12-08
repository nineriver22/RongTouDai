package baseuitls;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by caixiao on 2015/8/1 0001.
 */
public class AsyncHttpClientManager {

    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient(); // 实例话对象

    static {
        asyncHttpClient.setTimeout(3000); // 设置链接超时，如果不设置，默认为5s
    }

    //用一个完整url获取一个string对象
    public static void get(String urlString, AsyncHttpResponseHandler res) {
        try {
            asyncHttpClient.get(urlString, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String urlString, RequestParams params, AsyncHttpResponseHandler res) {
        try {
            asyncHttpClient.get(urlString, params, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //不带参数，获取json对象或者数组
    public static void get(String urlString, JsonHttpResponseHandler res) {
        try {
            asyncHttpClient.get(urlString, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void get(String urlString, RequestParams params, JsonHttpResponseHandler res) {
        try {
            asyncHttpClient.get(urlString, params, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String urlString, RequestParams params, AsyncHttpResponseHandler res) {
        try {
            asyncHttpClient.post(urlString, params, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void post(String urlString, RequestParams params, JsonHttpResponseHandler res) {
        try {
            asyncHttpClient.post(urlString, params, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
