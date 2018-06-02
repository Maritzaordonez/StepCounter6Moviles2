package edu.tectii.stepcounter6.util;

import java.util.Calendar;

public abstract class Util {

    /**
     * guarda la hora, los minutos, los segundos y los milisegundos de la zona horaria local del dia actual
     */
    public static long getToday() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * guarda la hora, los minutos, los segundos y los milisegundos de la zona horaria local del dia posterior
     */
    public static long getTomorrow() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, 1);
        return c.getTimeInMillis();
    }
}
