package com.linghang.rongtoudai;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import baseuitls.AsyncHttpClientManager;
import baseuitls.ShakeUnits;
import constans.ServiceConstans;
import cz.msebera.android.httpclient.Header;
import databean.ProjectBean;
import skipviews.MyScrollViewTestActivity;
import skipviews.MyViewTestActivity;
import skipviews.ProjectDetailActivity;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private FrameLayout frameLayoutMainContent;
    private FrameLayout frameLayoutMainLeft;
    private View mainContent;
    private ListView lvMain;
    private LinearLayout llIndex;
    private LinearLayout llFollow;
    private List<ProjectBean.VarlistEntity> allVarlistEntity;
    private ProjectListViewAdpter mAdapter;
    private ProjectBean projectBean;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        frameLayoutMainContent = (FrameLayout) findViewById(R.id.fl_main_content);
        frameLayoutMainLeft = (FrameLayout) findViewById(R.id.fl_main_left);

        mainContent = View.inflate(this, R.layout.activity_maincontent, null);
        frameLayoutMainContent.addView(mainContent);
        //frameLayoutMainLeft.addView(personView);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.activity_main_drawer);
        lvMain = (ListView) mainContent.findViewById(R.id.lv_main);
        mAdapter = new ProjectListViewAdpter();
        allVarlistEntity = new ArrayList<ProjectBean.VarlistEntity>();
        lvMain.setAdapter(mAdapter);
        llIndex = (LinearLayout) mainContent.findViewById(R.id.ll_mainbottom_index);
        llFollow = (LinearLayout) mainContent.findViewById(R.id.ll_mainbottom_follow);
        llIndex.setOnClickListener(this);
        llFollow.setOnClickListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        getProject(1, "all", "all", "all", "all");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator nopeAnimator = ShakeUnits.tada(llIndex,2f);
                nopeAnimator.setRepeatCount(5);
                nopeAnimator.start();
            }
        }, 2000);
    }

    private void getProject(final int pageNO, final String keyWord, final String kind, final String status, final String area) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("currentPage", pageNO);
        requestParams.put("showCount", 5);
        requestParams.put("step", status);
        requestParams.put("zone", area);
        requestParams.put("catid", kind);
        requestParams.put("kword", keyWord);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
        Log.i("TAG", "Max memory is " + maxMemory + "M");

        AsyncHttpClientManager.post(ServiceConstans.projectURL, requestParams, new AsyncHttpResponseHandler() {
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

    //解析获取的网络数据
    private void resolveData(String json) {
        Gson gson = new Gson();
        try {
            projectBean = gson.fromJson(json, ProjectBean.class);
            imgPath = projectBean.imgPath;
            allVarlistEntity.addAll(projectBean.varlist);
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mainbottom_index:
                //Intent projectIntent = new Intent(this, MapActivity.class);
                Intent projectIntent = new Intent(this, MyScrollViewTestActivity.class);
                startActivity(projectIntent);
                break;
            case R.id.ll_mainbottom_follow:
                Intent myViewIntent = new Intent(this, MyViewTestActivity.class);
                startActivity(myViewIntent);
                break;
        }
    }

    static class ViewHolder {

        public LinearLayout llProject;
        public TextView tvProjectName;
        public TextView tvProjectInfo;
        public TextView tvFollowCount;
        public TextView tvInfoCount;
        public TextView tvInfoText;
        public SimpleDraweeView sdvProjectPic;
        public ImageView ivProjectStage;
        public CardView cvProject;
    }

    private class ProjectListViewAdpter extends BaseAdapter {

        private ViewHolder viewHolder;

        @Override
        public int getCount() {
            return allVarlistEntity.size();
        }

        @Override
        public Object getItem(int position) {
            return allVarlistEntity.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(BaseApplication.getContext(), R.layout.project_listitem, null);
                viewHolder = new ViewHolder();
                viewHolder.llProject = (LinearLayout) convertView.findViewById(R.id.ll_project);
                viewHolder.tvProjectName = (TextView) convertView.findViewById(R.id.tv_project_listitem_projectname);
                viewHolder.tvProjectInfo = (TextView) convertView.findViewById(R.id.tv_project_listitem_projectinfo);
                viewHolder.tvFollowCount = (TextView) convertView.findViewById(R.id.tv_project_listitem_followcount);
                viewHolder.tvInfoCount = (TextView) convertView.findViewById(R.id.tv_project_listitem_infocount);
                viewHolder.tvInfoText = (TextView) convertView.findViewById(R.id.info_text);
                viewHolder.sdvProjectPic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_project_listitem_projectpic);
                viewHolder.ivProjectStage = (ImageView) convertView.findViewById(R.id.iv_project_stage);
                viewHolder.cvProject = (CardView) convertView.findViewById(R.id.cv_project);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvProjectName.setText(allVarlistEntity.get(position).title);
            viewHolder.tvInfoCount.setText(String.valueOf(allVarlistEntity.get(position).extend) + "条跟踪信息");
            viewHolder.tvProjectInfo.setText(allVarlistEntity.get(position).keywords);
            viewHolder.tvFollowCount.setText(String.valueOf(allVarlistEntity.get(position).hits) + "关注");

            switch (allVarlistEntity.get(position).stage) {
                case 1:
                    viewHolder.ivProjectStage.setBackgroundResource(R.drawable.iv_projectstatus_1);
                    break;
                case 2:
                    viewHolder.ivProjectStage.setBackgroundResource(R.drawable.iv_projectstatus_2);
                    break;
                case 3:
                    viewHolder.ivProjectStage.setBackgroundResource(R.drawable.iv_projectstatus_3);
                    break;
                case 4:
                    viewHolder.ivProjectStage.setBackgroundResource(R.drawable.iv_projectstatus_4);
                    break;
                case 5:
                    viewHolder.ivProjectStage.setBackgroundResource(R.drawable.iv_projectstatus_5);
                    break;
            }

            Uri uri = Uri.parse(imgPath + allVarlistEntity.get(position).attachment);
            viewHolder.sdvProjectPic.setImageURI(uri);
            viewHolder.cvProject.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, v, "transs");
                    Intent intent = new Intent(MainActivity.this, ProjectDetailActivity.class);
                    intent.putExtra("projectID", allVarlistEntity.get(position).id);
                    ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                }
            });

            viewHolder.ivProjectStage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return convertView;
        }

    }


}
