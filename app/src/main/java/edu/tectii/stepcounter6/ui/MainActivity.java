package edu.tectii.stepcounter6.ui;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;
import android.view.MenuItem;

import edu.tectii.stepcounter6.BuildConfig;
import edu.tectii.stepcounter6.R;
import edu.tectii.stepcounter6.SensorListener;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle b) {
        super.onCreate(b);
        startService(new Intent(this, SensorListener.class));
        if (b == null) {
            // Crea un nuevo fragmento y una transaccion
            Fragment newFragment = new Fragment_Overview();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Remplaza lo que se encuentre en  fragment_container por lo que se encuentre en este fragmento

            transaction.replace(android.R.id.content, newFragment);

            // comenta la transaccion
            transaction.commit();
        }
    //premiso para guardar en la memoria externa
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= 23 && PermissionChecker
                .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
 //vuelve a la Actividad o Fragmento anterior al que te encuentras en el momento,
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }
 //opciones item que tenemos en el menu
    public boolean optionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStackImmediate();
                break;
            case R.id.action_settings:
                getFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new Fragment_Settings()).addToBackStack(null)
                        .commit();
                break;

        }
        return true;
    }
}
