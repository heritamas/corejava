package p2ch10;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.util.stream.Collectors;

public class JAASTester {
    public static void main(String[] args) throws LoginException {
        System.setSecurityManager(new SecurityManager());
        var context = new LoginContext("Login1", new SimpleCallbackHandler());
        context.login();
        Subject subject = context.getSubject();
        String principals = subject.getPrincipals().stream()
            .map(Principal::toString)
            .collect(Collectors.joining(";"));
        System.out.println(principals);
        SysPropAction action = new SysPropAction("user.home");
        String result = Subject.doAsPrivileged(subject, action, null);
        System.out.println(result);
        context.logout();
    }
}
