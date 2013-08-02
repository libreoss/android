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

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;

/**
 * Treći TAB
 */
public class TreciTabRedakcija extends SherlockListFragment {
	private View pregledFragmenta;

	@Override
	public void onActivityCreated(Bundle sacuvanoStanjeInstance) {
		super.onActivityCreated(sacuvanoStanjeInstance);

	}

	@Override
	public void onCreate(Bundle sacuvanoStanjeInstance) {
		super.onCreate(sacuvanoStanjeInstance);

		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sacuvanoStanjeInstance) {
		pregledFragmenta = inflater.inflate(R.layout.prikaz_trecag_taba, container, false);
		if (pregledFragmenta == null)
			return null;

		return pregledFragmenta;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem meni) {
		if (meni.getTitle().equals(getString(R.string.action_settings))) {
			goToSettings();
			return true;
		}

		return false;
	}

	/**
	 * Prikaži pregled podešavanja.
	 */
	private void goToSettings() {
		// TODO Napraviti pregled podešavanja.
		Intent intent = new Intent();
		intent.setClass(getActivity().getApplicationContext(), LibreTim.class);

		startActivity(intent);

		Toast.makeText(getActivity(), "Podešavanja...", Toast.LENGTH_SHORT).show();
	}

	/**
	 * Refrešuj Listu
	 */
	private void refresujListu() {
		// TODO Verovatno neće trebati.
		Toast.makeText(getActivity(), "NEMA REFREŠA...", Toast.LENGTH_SHORT).show();
	}

}
