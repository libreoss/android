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

	private class Task extends AsyncTask<Void, Void, List<Post>>
	{
		protected List<Post> doInBackground(Void... params)  
		{
			WordpressJSON wp = new WordpressJSON("http://libre.lugons.org");
			return wp.getLatestPosts(10);
		}
		protected void onPostExecute(List<Post> result) 
		{
			//UpdateDisplay(result);
		}
	}
	/*
	private void UpdateDisplay(List<Post> postList) {
		TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
		TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
		ListView itemlist = (ListView) findViewById(R.id.itemlist);

		feedtitle.setVisibility(View.GONE);
		//feedpubdate.setText(samplePost.getPubDate().toString());

		ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this,
				android.R.layout.simple_list_item_1, postList);

		itemlist.setAdapter(adapter);

		itemlist.setSelection(0);

		//final SherlockActivity mainActivityRef = this;
		final List<Post> postListRef = postList;

		itemlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parrent, View v, int index,
					long l) {
				Intent descIntent = new Intent(mainActivityRef,
						ShowDescription.class);

				Bundle b = new Bundle();

				Post samplePost = postListRef.get(index);
				b.putString("title", samplePost.getTitle());
				b.putString("description", samplePost.getContent());
				b.putString("link", samplePost.getUrl());
				b.putString("pubdate", samplePost.getPubDate().toString());
				descIntent.putExtra("android.intent.extra.INTENT", b);
				startActivity(descIntent);
			}
		});
		

	}*/


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(
        getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
		
		new Task().execute();
	}
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
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

    public static class DummySectionFragment extends Fragment {
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return textView;
        }
    }

}
