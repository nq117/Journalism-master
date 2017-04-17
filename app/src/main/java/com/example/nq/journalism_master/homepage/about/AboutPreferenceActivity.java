package com.example.nq.journalism_master.homepage.about;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nq.journalism_master.BaseActivity;
import com.example.nq.journalism_master.R;

public class AboutPreferenceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initViews();

        AboutPreferenceFragment fragment = new AboutPreferenceFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.about_container, fragment)
                .commit();

        new AboutPresenter(AboutPreferenceActivity.this, fragment);
    }

    private void initViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.about_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
