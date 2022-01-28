package p2ch10;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;

public class SimpleCallbackHandler implements CallbackHandler
{
   private String answer;

   /**
    * Constructs the callback handler.
    * @param username the user name
    * @param password a character array containing the password
    */
   public SimpleCallbackHandler(String answer)
   {
      this.answer = answer;
   }

   public void handle(Callback[] callbacks)
   {
      for (Callback callback : callbacks)
      {
         if (callback instanceof TextInputCallback)
         {
            ((TextInputCallback) callback).setText(answer);
         }
      }
   }
}
