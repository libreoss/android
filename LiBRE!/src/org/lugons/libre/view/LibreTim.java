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

import android.annotation.SuppressLint;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * Stranica koja prikazuje LiBRE! tim podatke
 */

public class LibreTim extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle sacuvanoStanjeInstance) {
        super.onCreate(sacuvanoStanjeInstance);
        setContentView(R.layout.prikaz_libre_tim);
        //TODO možda promenuti verziju
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	// Prikaži Up dugme u Action Baru
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem meni) {
        switch (meni.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
            
        }
        return super.onOptionsItemSelected(meni);
    }
}
