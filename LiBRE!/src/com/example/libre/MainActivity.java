package com.example.libre;

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
import android.widget.TextView;

@SuppressWarnings("unused")
public class MainActivity extends FragmentActivity {
	ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;

	private Post samplePost;
	/*
	private void UpdateDisplay() {
		
		TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
		TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
		ListView itemlist = (ListView) findViewById(R.id.itemlist);
		List<Post> postList = new Vector<Post>(0);

		postList.add(samplePost);
		postList.add(samplePost);

		feedtitle.setVisibility(View.GONE);
		feedpubdate.setText(samplePost.getPubDate().toString());

		ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this,
				android.R.layout.simple_list_item_1, postList);

		itemlist.setAdapter(adapter);

		itemlist.setSelection(0);

	}
	*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(
        getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

		//samplePost = new Post();
		//UpdateDisplay();

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