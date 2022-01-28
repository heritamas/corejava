package p2ch10;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SimpleLoginModule implements LoginModule {
    private static Random random = new Random();

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> options;
    private int n;
    private int m;


    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.options = options;

        this.n = random.nextInt(10);
        this.m = random.nextInt(10);
    }

    public boolean login() throws LoginException {
        if (callbackHandler == null) throw new LoginException("no handler");

        var questionCall = new TextInputCallback(String.format("%d + %d = ?", n, m));
        try {
            callbackHandler.handle(new Callback[]{ questionCall });
        } catch (UnsupportedCallbackException e) {
            var e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        } catch (IOException e) {
            var e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        }
        return checkLogin(questionCall.getText());
    }

    private boolean checkLogin(String answer) {
        boolean passed = false;
        try {
            int num = Integer.parseInt(answer);
            int expected = n + m;
            if (num == expected) {
                passed = true;
                Set<Principal> principals = subject.getPrincipals();
                principals.add(new SimplePrincipal("human", "yes"));
            }
        } catch (NumberFormatException e) {
            passed = false;
        }

        return passed;
    }

    public boolean logout() {
        return true;
    }

    public boolean abort() {
        return true;
    }

    public boolean commit() {
        return true;
    }
}

