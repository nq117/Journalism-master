package com.example.nq.journalism_master.homepage.about;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nq.journalism_master.R;

/**
 * Created by Administrator on 2017/4/13.
 */
public class AboutPreferenceFragment extends PreferenceFragmentCompat
        implements AboutContract.View {

    private Toolbar toolbar;
    private AboutContract.Presenter presenter;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.about_preference_fragment);

        initViews(getView());

        findPreference("xt").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.showEasterEgg();
                return false;
            }
        });

        findPreference("fy").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.followOnGithub();
                return false;
            }
        });

        findPreference("nq").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.followOnZhihu();
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showRateError() {
        Snackbar.make(toolbar, "没有市场类App",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showFeedbackError() {
        Snackbar.make(toolbar, "没有找到邮件类App",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showBrowserNotFoundError() {
        Snackbar.make(toolbar, "没有安装浏览器",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(AboutContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }
}
