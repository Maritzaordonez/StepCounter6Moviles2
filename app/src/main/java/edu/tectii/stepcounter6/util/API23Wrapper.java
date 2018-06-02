package edu.tectii.stepcounter6.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
//imagen de la aplicacion al momento de instalar el apk si tienes un API23
@TargetApi(Build.VERSION_CODES.M)
public class API23Wrapper {

    public static void requestPermission(final Activity a, final String[] permissions) {
        a.requestPermissions(permissions, 42);
    }

}
