package edu.tectii.stepcounter6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import edu.tectii.stepcounter6.util.Logger;
import edu.tectii.stepcounter6.util.Util;

public class ShutdownRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("shutting down");

        context.startService(new Intent(context, SensorListener.class));

        //si el usuario usó un script raíz para el apagado, el DEVICE_SHUTDOWN
       // la transmisión podría no enviarse. Por lo tanto, la aplicación comprobará esto
        //configurando el siguiente arranque y muestra un mensaje de error si no es
        //establecido en verdadero
        context.getSharedPreferences("pedometer", Context.MODE_PRIVATE).edit()
                .putBoolean("correctShutdown", true).commit();

        Database db = Database.getInstance(context);
        // el if se encarga de que este listo el servicio para inicializarlo un nuevo dia
        if (db.getSteps(Util.getToday()) == Integer.MIN_VALUE) {
            int steps = db.getCurrentSteps();
            int pauseDifference = steps -
                    context.getSharedPreferences("pedometer", Context.MODE_PRIVATE)
                            .getInt("pauseCount", steps);
            db.insertNewDay(Util.getToday(), steps - pauseDifference);
            if (pauseDifference > 0) {
                // actualiza pauseCount para el nuevo día
                context.getSharedPreferences("pedometer", Context.MODE_PRIVATE).edit()
                        .putInt("pauseCount", steps).commit();
            }
        } else {
            db.addToLastEntry(db.getCurrentSteps());
        }
        //los pasos actuales se reiniciarán al arrancar con BootReceiver
        db.close();
    }

}
