package p2ch07;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ListResourceBundle;
import java.util.Locale;

public class PracticeBundle_hu extends ListResourceBundle {
  private static final Locale magyar = Locale.forLanguageTag("hu");
  private static final DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(magyar);
  
  static {
    numberFormat.applyPattern("###,###.## Ft");
  }
  private final Object[][] array =  {
      {"welcome", "Üdvözlet {0}"},
      {"date", new MessageFormat("{0,date,long}", magyar)},
      {"currency", numberFormat}
  };
  
  @Override
  protected Object[][] getContents() {
    return array;
  }
}
