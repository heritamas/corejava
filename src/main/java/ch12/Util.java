package ch12;

import java.util.Random;
import java.util.logging.Logger;

public class Util {
    private static Logger log = Logger.getLogger(Util.class.getName());
    private static final Random rnd = new Random(System.currentTimeMillis());

    public static Candidate getRandomCandidate() {
        return Candidate.values()[rnd.nextInt(Candidate.values().length)];
    }

    public static int nextInt(int max) {
        return rnd.nextInt(max);
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.warning("Sleep interrupted.");
        }
    }
}
