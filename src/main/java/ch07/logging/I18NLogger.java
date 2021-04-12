package ch07.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

class Ancestor {
    protected final static Logger log;

    static {
        try(InputStream logprops = Ancestor.class.getResourceAsStream("/logging.properties")) {
            LogManager.getLogManager().readConfiguration(logprops);
        } catch (IOException e) {
            Logger.getAnonymousLogger().severe("Log configuration failed.%n");
            Logger.getAnonymousLogger().severe(e.getStackTrace().toString());
        } finally {
            log = Logger.getLogger("ch07.logging", "corejava.logmessages");
        }
    }

    public Ancestor() {
        final String method = "ancestor constructor";
        log.log(Level.INFO, "entering", method);
        log.log(Level.INFO, "executing", method);
        log.log(Level.INFO, "exiting", method);
    }
}

class Derived extends Ancestor {

    public Derived() {
        final String method = "derived constructor";
        log.log(Level.INFO, "entering", method);
        log.log(Level.INFO, "executing", method);
        log.log(Level.INFO, "exiting", method);
    }

    void problem(String stuff) {
        log.log(Level.SEVERE, "problem", stuff);
    }
}

public class I18NLogger {
    public static void main(String[] args) {
        Derived d = new Derived();
        d.problem("wtf???");
    }
}
