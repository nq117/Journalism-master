package com.example.nq.journalism_master.homepage.about;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import com.example.nq.journalism_master.R;
import com.example.nq.journalism_master.customtabs.CustomFallback;
import com.example.nq.journalism_master.customtabs.CustomTabActivityHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/13.
 */
public class AboutPresenter implements AboutContract.Presenter{

    private AboutContract.View view;
    private AppCompatActivity activity;
    private SharedPreferences sp;
    private CustomTabsIntent.Builder customTabsIntent;

    public AboutPresenter(AppCompatActivity activity, AboutContract.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);
        sp = activity.getSharedPreferences("user_settings", MODE_PRIVATE);

        customTabsIntent = new CustomTabsIntent.Builder();
        customTabsIntent.setToolbarColor(activity.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void rate() {

    }

    @Override
    public void openLicense() {

    }

    @Override
    public void followOnGithub() {

        if (sp.getBoolean("in_app_browser", true)) {
            CustomTabActivityHelper.openCustomTab(
                    activity,
                    customTabsIntent.build(),
                    Uri.parse(activity.getString(R.string.fy_college)),
                    new CustomFallback() {
                        @Override
                        public void openUri(Activity activity, Uri uri) {
                            super.openUri(activity, uri);
                        }
                    }
            );
        } else {
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(activity.getString(R.string.fy_college))));
            } catch (android.content.ActivityNotFoundException ex) {
                view.showBrowserNotFoundError();
            }
        }

    }

    @Override
    public void followOnZhihu() {
        if (sp.getBoolean("in_app_browser", true)) {
            CustomTabActivityHelper.openCustomTab(
                    activity,
                    customTabsIntent.build(),
                    Uri.parse(activity.getString(R.string.nq_college)),
                    new CustomFallback() {
                        @Override
                        public void openUri(Activity activity, Uri uri) {
                            super.openUri(activity, uri);
                        }
                    }
            );
        } else {
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData( Uri.parse(activity.getString(R.string.nq_college))));
            } catch (android.content.ActivityNotFoundException ex){
                view.showBrowserNotFoundError();
            }
        }
    }

    @Override
    public void feedback() {

    }

    @Override
    public void donate() {

    }

    @Override
    public void showEasterEgg() {
        if (sp.getBoolean("in_app_browser", true)) {
            CustomTabActivityHelper.openCustomTab(
                    activity,
                    customTabsIntent.build(),
                    Uri.parse(activity.getString(R.string.xt_college)),
                    new CustomFallback() {
                        @Override
                        public void openUri(Activity activity, Uri uri) {
                            super.openUri(activity, uri);
                        }
                    }
            );
        } else {
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(activity.getString(R.string.xt_college))));
            } catch (android.content.ActivityNotFoundException ex) {
                view.showBrowserNotFoundError();
            }
        }
    }

    @Override
    public void start() {

    }
}
