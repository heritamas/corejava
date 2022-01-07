package p2ch07;

import java.text.MessageFormat;
import java.util.ListResourceBundle;

public class PracticeBundle extends ListResourceBundle {
  private final Object[][] array =  {
      {"welcome", "Csácsumi {0}!"},
      {"goodbye", "Csö"}
  };

  @Override
  protected Object[][] getContents() {
    return array;
  }
}
