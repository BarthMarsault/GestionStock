package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static Utils.Consts.LOG_PATH;

public class Log {


    /**
     * Logger that write in a text file all error that it called for.
     * @param ex Exception
     * @param args Array of String to add message(s) if needed
     */
    public static void logError(Exception ex,String... args){

        BufferedWriter logger = null;
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Calendar cal = Calendar.getInstance();

        try {
            logger = new BufferedWriter(new FileWriter(LOG_PATH,true));

            try {
                logger.write( dateTimeFormat.format(cal.getTime()) + System.getProperty("line.separator") + ex.getMessage() + System.getProperty("line.separator"));
                for(String str : args){ //Write every message of args in a new line
                    logger.write(str + System.getProperty("line.separator"));
                }

            } finally {
                logger.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
