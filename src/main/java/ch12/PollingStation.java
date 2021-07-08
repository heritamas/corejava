package ch12;

import java.util.EnumMap;
import java.util.stream.IntStream;

public class PollingStation {

    private final int maxBallots;
    private final EnumMap<Candidate, Long> votes = new EnumMap<Candidate, Long>(Candidate.class);
    private boolean ready = false;

    public PollingStation() {
        maxBallots = Util.nextInt(100);
    }

    public synchronized void castVotes() {
        // it takes a while everyone casts his vote
        Util.sleep(Util.nextInt(1000));

        IntStream.range(0, maxBallots)
                .forEach(i -> {
                    Candidate randomCandidate = Util.getRandomCandidate();
                    votes.merge(randomCandidate, 1L, Long::sum);
                });

        // and they are counted
        Util.sleep(Util.nextInt(1000));

        ready = true;
        notifyAll();
    }

    public synchronized void waitUntilReady() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Long getVotes(Candidate c) {
        waitUntilReady();
        return votes.getOrDefault(c, 0L);
    }

}
