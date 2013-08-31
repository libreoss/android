package com.example.libre;

import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

@SuppressWarnings("unused")
public class MainActivity extends FragmentActivity {
	ViewPager mViewPager;
	SectionsPagerAdapter mSectionsPagerAdapter;

	List<Post> posts; 
	Post membersPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Task t = new Task(this); 
		t.execute();	
	}

	void UpdateDisplay(List<Post> _posts, Post _membersPage) 
	{
		posts = _posts; 
		membersPage = _membersPage;
		
        	mSectionsPagerAdapter = new SectionsPagerAdapter(
        	getSupportFragmentManager());
	
	        mViewPager = (ViewPager) findViewById(R.id.pager);
	        mViewPager.setAdapter(mSectionsPagerAdapter);
	}
	private class SectionsPagerAdapter extends FragmentPagerAdapter {

        	public SectionsPagerAdapter(FragmentManager fm) {
        	    super(fm);
        	}
        
		@Override
        	public Fragment getItem(int position) {
        	    Fragment fragment = new Page(posts, membersPage);
        	    Bundle args = new Bundle();
        	    args.putInt(Page.ARG_SECTION_NUMBER, position);
        	    fragment.setArguments(args);
        	    return fragment;
        	}

        	@Override
        	public int getCount() {
        	    return 3;
        	}
	
	        @Override
	        public CharSequence getPageTitle(int position) {
	            switch (position) {
	            case 0:
	                return getString(R.string.title_section1).toUpperCase();
	            case 1:
	                return getString(R.string.title_section2).toUpperCase();
	            case 2:
	                return getString(R.string.title_section3).toUpperCase();
	            }
	            return null;
	        }
	}	
}
