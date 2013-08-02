/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses />.
 */

package org.lugons.libre.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.ArrayList;

/**
 * Prepravljen TAB listener. Taboovi implementirani u Action Baru.
 * Svaki sadržaj Taba je Sherlock fragment
 */
public class LibreTabListener extends FragmentStatePagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    private final Context SADRZAJ;
    private final ActionBar ACTION_BAR;
    private final ViewPager PAGER_PREGLED;
    private final ArrayList<TabInfo> TABS = new ArrayList<TabInfo>();
    private final ArrayList<ITabChangedListener> PROMENJEN_TAB_LISTENERES = new ArrayList<ITabChangedListener>();

    static final class TabInfo {
        private final Class<?> CALZZ;
        private final Bundle ARGUMENTI;

        TabInfo(Class<?> clss, Bundle args) {
            CALZZ = clss;
            ARGUMENTI = args;
        }
    }
    

    public LibreTabListener(SherlockFragmentActivity aktivnost, ViewPager pager) {
        super(aktivnost.getSupportFragmentManager());
        SADRZAJ = aktivnost;
        ACTION_BAR = aktivnost.getSupportActionBar();
        PAGER_PREGLED = pager;
        PAGER_PREGLED.setAdapter(this);
        PAGER_PREGLED.setOnPageChangeListener(this);
    }

    /**
     * Dodavanje Tabova u ActionBar.
     *
     * @param tab  Tab koji se dodaje
     * @param clzz Klasa koje je povezana sa Tabom
     * @param args Extra argumenti
     */
    public void addTab(ActionBar.Tab tab, Class<?> clzz, Bundle args) {
        TabInfo info = new TabInfo(clzz, args);
        tab.setTag(info);
        tab.setTabListener(this);
        TABS.add(info);
        ACTION_BAR.addTab(tab);
        notifyDataSetChanged();
    }

    public void addTabChangedListener(ITabChangedListener listener) {
        PROMENJEN_TAB_LISTENERES.add(listener);
    }

    @Override
    public int getCount() {
        return TABS.size();
    }

    @Override
    public Fragment getItem(int pozicija) {
        TabInfo info = TABS.get(pozicija);
        return Fragment.instantiate(SADRZAJ, info.CALZZ.getName(), info.ARGUMENTI);
    }

    private void notifyTabChangedListeners(int indeksTaba, Tab tab, View pregledTaba) {
        for (ITabChangedListener listener : PROMENJEN_TAB_LISTENERES) {
            listener.onTabChanged(indeksTaba, tab, pregledTaba);
        }
    }

    @Override
    public void onPageScrolled(int pozicija, float pozicioniOffset,
                               int pozicioniOffsetPixels) {
    }

    @Override
    public void onPageSelected(int pozicija) {
        ACTION_BAR.setSelectedNavigationItem(pozicija);
    }

    @Override
    public void onPageScrollStateChanged(int stanje) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        Object tag = tab.getTag();
        for (int i = 0; i < TABS.size(); i++) {
            if (TABS.get(i) == tag) {
                PAGER_PREGLED.setCurrentItem(i);
                notifyTabChangedListeners(i, tab, tab.getCustomView());
            }
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
}
