package com.def.max.moviedesk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.def.max.moviedesk.Fragments.PopularMoviesFragment;
import com.def.max.moviedesk.Fragments.TopRatedMoviesFragment;
import com.def.max.moviedesk.Utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionUtil = new PermissionUtil(this);

        if (checkPermission(PermissionUtil.READ_INTERNET) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,android.Manifest.permission.INTERNET))
            {
                showPermissionExplanation(PermissionUtil.READ_INTERNET);
            }
            else if (permissionUtil.checkPermissionPreference(PermissionUtil.PERMISSION_INTERNET))
            {
                requestPermission(PermissionUtil.READ_INTERNET);
                permissionUtil.updatePermissionPreference(PermissionUtil.PERMISSION_INTERNET);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Please allow internet permission in your app settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",MainActivity.this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }

        Toolbar mainToolbar =findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        String[] title = {"Top-Rated", "Popular"};

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.main_tab_content);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < title.length; i++)
        {
            tabLayout.getTabAt(i).setText(title[i]);
        }

        tabLayout.getTabAt(0).select();
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.insertNewFragment(new TopRatedMoviesFragment());
        adapter.insertNewFragment(new PopularMoviesFragment());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        void insertNewFragment(Fragment fragment)
        {
            mFragmentList.add(fragment);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (checkPermission(PermissionUtil.READ_INTERNET) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,android.Manifest.permission.INTERNET))
            {
                showPermissionExplanation(PermissionUtil.READ_INTERNET);
            }
            else if (permissionUtil.checkPermissionPreference(PermissionUtil.PERMISSION_INTERNET))
            {
                requestPermission(PermissionUtil.READ_INTERNET);
                permissionUtil.updatePermissionPreference(PermissionUtil.PERMISSION_INTERNET);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Please allow internet permission in your app settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",MainActivity.this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }

    }

    private int checkPermission(int permission)
    {
        int status = PackageManager.PERMISSION_DENIED;

        switch (permission)
        {
            case PermissionUtil.READ_INTERNET:
                status = ContextCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET);
                break;
        }
        return status;
    }

    private void requestPermission(int permission)
    {
        switch (permission)
        {
            case PermissionUtil.READ_INTERNET:
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.INTERNET},PermissionUtil.REQUEST_INTERNET);
                break;
        }
    }

    private void showPermissionExplanation(final int permission)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        switch (permission)
        {
            case PermissionUtil.READ_INTERNET:
                builder.setMessage("This app need to access your internet..");
                builder.setTitle("Internet Permission Needed..");
                break;
        }

        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (permission)
                {
                    case PermissionUtil.READ_INTERNET:
                        requestPermission(PermissionUtil.READ_INTERNET);
                        break;
                }
            }
        });

        builder.setNegativeButton("Deny", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
