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

package org.lugons.libre.view;

import org.lugons.libre.controller.ITabChangedListener;
import org.lugons.libre.controller.LibreTabListener;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Klasa koja je MAIN tab sa Action Barom i Pagerom
 */
public class GlavnaAktivnost extends SherlockFragmentActivity implements ITabChangedListener {
	private ActionBar actionBar;
	private ViewPager pregledPager;

	// TODO: Getteri i Seteri nisu implementirani
	private static boolean jelPrikazanPrviTab;
	private static boolean jelPrikazanDrugiTab;
	private static boolean jelPrikazanTreciTab;
	private static boolean jelPrikazanCetvrtiTab;
	private static Menu meniInstanca;

	protected static GlavnaAktivnost THIS = null;

	/**
	 * I tabovi action Bara su ovde
	 */
	private void dodajTabove() {
		LibreTabListener tabListener = new LibreTabListener(this, pregledPager);
		tabListener.addTabChangedListener(this);

		ActionBar.Tab prviTab = actionBar.newTab();
		prviTab.setText("Vesti");
		tabListener.addTab(prviTab, PrviTabVesti.class, null);
		prviTab.setTabListener(tabListener);

		ActionBar.Tab drugiTab = actionBar.newTab();
		drugiTab.setText("Info");
		tabListener.addTab(drugiTab, DrugiTabInfo.class, null);
		drugiTab.setTabListener(tabListener);
		
		ActionBar.Tab treciTab = actionBar.newTab();
		treciTab.setText("Redakcija");
		tabListener.addTab(treciTab, TreciTabRedakcija.class, null);
		treciTab.setTabListener(tabListener);

		// ActionBar.Tab cetvrtiTab = _actionBar.newTab();
		// cetvrtiTab.setText("Fourth");
		// myTabListener.addTab(cetvrtiTab, FourthFragment.class, null);
		// cetvrtiTab.setTabListener(myTabListener);
	}

	/**
	 * Sakrij svu aktivnost Action Bar fragmenta
	 * 
	 * @param meni
	 *            Action bar meni instanca
	 */
	private void sakrijSveActionInstance(Menu meni) {
		if (meni != null) {
			for (int i = 0; i < meni.size(); i++)
				meni.getItem(i).setVisible(false);
		}
	}

	/**
	 * Inicijalizuj Action Bar i postavi na navigacioni mod
	 */
	private void initActionBar() {
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	/**
	 * Inicijalizuj Pager i postavi na Content pregled
	 */
	private void initPregledPager() {
		pregledPager = new ViewPager(this);
		pregledPager.setId(R.id.pager);
		setContentView(pregledPager);
	}

	@Override
	protected void onCreate(Bundle sacuvanoStanjeInstance) {
		super.onCreate(sacuvanoStanjeInstance);

		THIS = this;
		initActionBar();
		initPregledPager();
		dodajTabove();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu meni) {
		meniInstanca = meni;

		// TODO videti zasto ikonica setingsa ne prelazi lepo nego nestaje
		if (!jelPrikazanTreciTab) {
			meni.add(getString(R.string.action_refresh)).setIcon(R.drawable.action_refresh).setVisible(true).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			resetujVidljvaPolja();
		}
		meni.add(getString(R.string.action_settings)).setIcon(R.drawable.action_settings).setVisible(jelPrikazanTreciTab).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return true;
	}

	@Override
	public void onTabChanged(int indeksStranice, ActionBar.Tab tab, View pregledTaba) {
		resetujVidljvaPolja();
		if (meniInstanca != null) {
			sakrijSveActionInstance(meniInstanca);

			switch (indeksStranice) {
			case 0:
				prikaziPrviTab(meniInstanca);
				break;

			case 1:
				prikaziDrigiTab(meniInstanca);
				break;

			case 2:
				prikaziTreciTab(meniInstanca);
				break;

			case 3:
				prikaziCetvrtiTab(meniInstanca);
				break;
			}
		}
	}

	/**
	 * Postavi vidljivost svih tabova na "false"
	 */
	private void resetujVidljvaPolja() {
		jelPrikazanPrviTab = false;
		jelPrikazanDrugiTab = false;
		jelPrikazanTreciTab = false;
		jelPrikazanCetvrtiTab = false;
	}

	/**
	 * Prikaži Prvi Tab
	 * 
	 * @param meni
	 *            Action bar meni instanca
	 */
	private void prikaziPrviTab(Menu meni) {
		if (meni != null && meni.size() == 2) {
			meni.getItem(0).setVisible(true);

			jelPrikazanPrviTab = true;
		}
	}

	/**
	 * Prikaži Drugi Tab
	 * 
	 * @param meni
	 *            Action bar meni instanca
	 */
	private void prikaziDrigiTab(Menu meni) {
		if (meni != null && meni.size() == 2) {
			meni.getItem(0).setVisible(true);

			jelPrikazanDrugiTab = true;
		}
	}

	/**
	 * Prikaži Treći Tab
	 * 
	 * @param meni
	 *            Action bar meni instanca
	 */
	private void prikaziTreciTab(Menu meni) {
		if (meni != null && meni.size() == 2) {
			meni.getItem(1).setVisible(true); // prikaz Podešavanja

			jelPrikazanTreciTab = true;
		}
	}

	/**
	 * Prikaži Četvrti Tab
	 * 
	 * @param meni
	 *            Action bar meni instanca
	 */
	private void prikaziCetvrtiTab(Menu meni) {
		if (meni != null && meni.size() == 2) {
			meni.getItem(0).setVisible(true);

			jelPrikazanCetvrtiTab = true;
		}
	}

}