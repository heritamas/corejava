package p2ch10;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import java.util.Scanner;

public class SimpleCallbackHandler implements CallbackHandler
{
   public SimpleCallbackHandler()
   { }

   public void handle(Callback[] callbacks)
   {
      for (Callback callback : callbacks)
      {
         if (callback instanceof TextInputCallback)
         {
            String prompt = ((TextInputCallback) callback).getPrompt();
            System.out.println(prompt);
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            ((TextInputCallback) callback).setText(answer);
         }
      }
   }
}
