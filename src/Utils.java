import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 03/11/2015.
 */
public class Utils {

    private static String[] listeJoursFeries = {
            "01/01/2017",
            "17/04/2017",
            "01/05/2017",
            "08/05/2017",
            "25/05/2017",
            "05/06/2017",
            "14/07/2017",
            "15/08/2017",
            "01/11/2017",
            "11/11/2017",
            "25/12/2017"
    };

    private static Map<String, Date> jourFeries = new HashMap<>();

    public static String dateToSimpleString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static boolean estJourTravaille(Date dateJour) {
        Calendar calendar = Calendar.getInstance();
        if (jourFeries.isEmpty()) {
            // On initialise les jours fériés une seule fois.
            for(int i = 0; i < listeJoursFeries.length; i++){
                String sDate = listeJoursFeries[i];
                calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(sDate.substring(0, 2)));
                calendar.set(Calendar.MONTH, Integer.valueOf(sDate.substring(3, 5)));
                calendar.set(Calendar.YEAR, Integer.valueOf(sDate.substring(6, 10)));
                jourFeries.put(sDate, calendar.getTime());
            }
        }
        calendar.setTime(dateJour);
        // Si la date est un jour du week-end, on considère que ce n'est pas un jour ouvré
        if(calendar.get(Calendar.DAY_OF_WEEK) == 7 || calendar.get(Calendar.DAY_OF_WEEK) == 1)
            return false;
        else
            return jourFeries.get(calendar.get(Calendar.DAY_OF_MONTH) + '/' + calendar.get(Calendar.MONTH) + '/' + calendar.get(Calendar.YEAR)) == null;
    }
}