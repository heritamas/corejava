package p2ch10;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASTester {
    public static void main(String[] args) throws LoginException {
        var context = new LoginContext("Login1", new SimpleCallbackHandler(3, 5));
        context.login();
        Subject subject = context.getSubject();
        context.logout();
    }
}
