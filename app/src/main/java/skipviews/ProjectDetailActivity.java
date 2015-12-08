package skipviews;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.linghang.rongtoudai.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import baseuitls.AsyncHttpClientManager;
import constans.ServiceConstans;
import cz.msebera.android.httpclient.Header;
import databean.ProjectContentBean;

/**
 * Created by caixiao on 2015/11/30 0030.
 */
public class ProjectDetailActivity extends Activity {

    private SimpleDraweeView sdvProject;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvArea;
    private TextView tvKind;
    private TextView tvStage;
    private TextView tvPrice;
    private TextView tvMoneySource;
    private TextView tvEquipment;
    private TextView tvWork;
    private TextView tvService;
    private TextView tvContent;
    private TextView tvEvolve;
    private TextView tvBegin;
    private TextView tvEnd;
    private ScrollView svProjectContent;
    private TextView tvInfoText;
    private ProjectContentBean projectContentBean;
    private ProjectContentBean.DataEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetail);
        sdvProject = (SimpleDraweeView) findViewById(R.id.sdv_projectcontent_pic);
        tvName = (TextView) findViewById(R.id.tv_projectcontent_name);
        tvAddress = (TextView) findViewById(R.id.tv_projectcontent_address);
        tvArea = (TextView) findViewById(R.id.tv_projectcontent_area);
        tvKind = (TextView) findViewById(R.id.tv_projectcontent_kind);
        tvStage = (TextView) findViewById(R.id.tv_projectcontent_stage);
        tvPrice = (TextView) findViewById(R.id.tv_projectcontent_price);
        tvMoneySource = (TextView) findViewById(R.id.tv_projectcontent_moneysource);
        tvEquipment = (TextView) findViewById(R.id.tv_projectcontent_equipment);
        tvWork = (TextView) findViewById(R.id.tv_projectcontent_work);
        tvService = (TextView) findViewById(R.id.tv_projectcontent_service);
        tvContent = (TextView) findViewById(R.id.tv_projectcontent_content);
        tvEvolve = (TextView) findViewById(R.id.tv_projectcontent_evolve);
        tvBegin = (TextView) findViewById(R.id.tv_projectcontent_begin);
        tvEnd = (TextView) findViewById(R.id.tv_projectcontent_end);
        tvInfoText = (TextView) findViewById(R.id.info_text);
        svProjectContent = (ScrollView) findViewById(R.id.sv_projectcontent);
        getProject(getIntent().getIntExtra("projectID", 0));
    }

    private void getProject(final int projectID) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("ProjID", projectID);

        AsyncHttpClientManager.post(ServiceConstans.ProjectContentURL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String json = new String(responseBody);
                resolveData(json);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void resolveData(String json) {
        Gson gson = new Gson();
        try {
            projectContentBean = gson.fromJson(json, ProjectContentBean.class);
            data = projectContentBean.data;
            tvName.setText(data.title);
            tvAddress.setText(data.zone + data.address);
            tvArea.setText(data.area);
            tvKind.setText(data.projtype);
            tvStage.setText(data.stage);
            tvPrice.setText(data.investment);
            tvMoneySource.setText(data.sources);
            tvEquipment.setText(data.facility);
            tvWork.setText(data.engineering);
            tvService.setText(data.expertise);
            tvContent.setText(data.introduce);
            tvEvolve.setText(data.progress);
            tvBegin.setText(data.startime);
            tvEnd.setText(data.endtime);
            Uri uri = Uri.parse(data.thumb);
            sdvProject.setImageURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finishAfterTransition();
    }

}
