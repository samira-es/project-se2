package com.example.mybook;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_main, container, false);
            TextView mPageTv, mChaptTv, mTitleTv, mDetailTv;
            mPageTv = v.findViewById(R.id.pageTv);
            mChaptTv = v.findViewById(R.id.chaptTv);
            mTitleTv = v.findViewById(R.id.titleTv);
            mDetailTv = v.findViewById(R.id.detailTv);

            SqlLiteDbHelper dbHelper;
            ArrayList<Model> modelList;
            int page = 0;
            String chap="", title="", detail="";
            modelList = new ArrayList<>();
            modelList.clear();
            dbHelper = new SqlLiteDbHelper(getContext());
            try {
                dbHelper.openDataBase();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            modelList = dbHelper.getDetails();

            if (getArguments().getInt(ARG_SECTION_NUMBER)==1){
                //get first record
                Model count = modelList.get(0);
                //save record in strings
                page = page + count.getIds();
                chap = chap + count.getChapters();
                title = title + count.getTitles();
                detail = detail + count.getDetails().replace(",,,,","\n");
                //set in TextView
                mPageTv.setText(page+"");
                mChaptTv.setText(chap);
                mTitleTv.setText(title);
                mDetailTv.setText(detail);

                return v;
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                //get second record
                Model count = modelList.get(1);
                //save record in strings
                page = page + count.getIds();
                chap = chap + count.getChapters();
                title = title + count.getTitles();
                detail = detail + count.getDetails().replace(",,,,","\n");
                //set in TextView
                mPageTv.setText(page+"");
                mChaptTv.setText(chap);
                mTitleTv.setText(title);
                mDetailTv.setText(detail);

                return v;
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==3){
                //get third record
                Model count = modelList.get(2);
                //save record in strings
                page = page + count.getIds();
                chap = chap + count.getChapters();
                title = title + count.getTitles();
                detail = detail + count.getDetails().replace(",,,,","\n");
                //set in TextView
                mPageTv.setText(page+"");
                mChaptTv.setText(chap);
                mTitleTv.setText(title);
                mDetailTv.setText(detail);

                return v;
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==4){
                //get fourth record
                Model count = modelList.get(3);
                //save record in strings
                page = page + count.getIds();
                chap = chap + count.getChapters();
                title = title + count.getTitles();
                detail = detail + count.getDetails().replace(",,,,","\n");
                //set in TextView
                mPageTv.setText(page+"");
                mChaptTv.setText(chap);
                mTitleTv.setText(title);
                mDetailTv.setText(detail);

                return v;
            }
            if (getArguments().getInt(ARG_SECTION_NUMBER)==5){
                //get fifth record
                Model count = modelList.get(4);
                //save record in strings
                page = page + count.getIds();
                chap = chap + count.getChapters();
                title = title + count.getTitles();
                detail = detail + count.getDetails().replace(",,,,","\n");
                //set in TextView
                mPageTv.setText(page+"");
                mChaptTv.setText(chap);
                mTitleTv.setText(title);
                mDetailTv.setText(detail);

                return v;
            }

            return v;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }
    }
}