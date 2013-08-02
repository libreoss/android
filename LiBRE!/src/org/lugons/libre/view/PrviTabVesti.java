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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;

/**
 * Prvi TAB
 */
public class PrviTabVesti extends SherlockListFragment {
	private View PregledFragmenta;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup kontejner, Bundle sacuvanoStanjeInstance) {
		PregledFragmenta = inflater.inflate(R.layout.prikaz_prvog_taba, kontejner, false);
		if (PregledFragmenta == null)
			return null;

		return PregledFragmenta;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem meni) {
		if (meni.getTitle().equals(getString(R.string.action_refresh))) {
			refresujListu();
			return true;
		}

		return false;
	}

	/**
	 * Refre�uj listu
	 */
	private void refresujListu() {
		// TODO implementacija RSS parsera
		Toast.makeText(getActivity(), "Refre�ujem vesti...", Toast.LENGTH_SHORT).show();
	}
}