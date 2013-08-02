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

import android.view.View;
import com.actionbarsherlock.app.ActionBar;

/**
 * Action Bar TAB Listener Interfejs, kada korisnik promeni tab
 */
public interface ITabChangedListener {
	/**
	 * Ova metoda se poziva kada korisnik promeni stranicu u Tabu
	 * 
	 * @param indeksStranice
	 *            , Index trenutne stranice.
	 * @param TAB
	 *            Instanca selektovane TAB kontrole.
	 * @param tabPregled
	 *            Instanca TAB pregleda.
	 */
	void onTabChanged(int indeksStranice, ActionBar.Tab tab, View tabPregled);
}