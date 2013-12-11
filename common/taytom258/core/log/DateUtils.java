package taytom258.core.log;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import taytom258.lib.Reference;

public class DateUtils {

  public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(Reference.DATE_FORMAT);
    return sdf.format(cal.getTime());
  }
}

