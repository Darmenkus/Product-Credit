package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IinUtils {
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyMMdd");
    private static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy");

    public static boolean iinCheck(String iin, Date birthDate, String gender) {
        if (!(iin.substring(0, 6).equals(dateFormat2.format(birthDate))))
            return false;

        int g1 = Integer.valueOf(iin.substring(6, 7));
        boolean g2 = gender.equals(Constants.MALE);
        if (((g1 % 2) == 1) != g2)
            return false;

        int centuries[] = new int[]{19, 19, 20, 20, 21, 21};
        int birthYear = Integer.valueOf(dateFormat3.format(birthDate));
        int century = birthYear / 100;
        if (century % 100 != 0)
            century++;
        if (centuries[g1 - 1] != century)
            return false;

        int[] b1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] b2 = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2};
        int[] a = new int[12];
        int control = 0;
        for (int i = 0; i < 12; i++) {
            a[i] = Integer.valueOf(iin.substring(i, i + 1));
            if (i < 11)
                control += a[i] * b1[i];
        }

        control = control % 11;

        if (control == 10) {
            control = 0;
            for (int i = 0; i < 11; i++) {
                a[i] = Integer.valueOf(iin.substring(i, i + 1));
                control += a[i] * b2[i];
            }
            control = control % 11;
        }
        return control == a[11];
    }
}
