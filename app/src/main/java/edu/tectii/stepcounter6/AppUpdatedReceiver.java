package edu.tectii.stepcounter6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import edu.tectii.stepcounter6.util.Logger;
//Es un servicio que se encarga de registrar lo que hace el SensorListener
public class AppUpdatedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {
		if (BuildConfig.DEBUG)
			Logger.log("app updated");
		context.startService(new Intent(context, SensorListener.class));
	}

}
