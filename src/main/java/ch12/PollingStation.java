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

    public void castVotes() {
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
    }

    public void waitUntilReady() {
        while (!ready) {
            Util.sleep(100);
        }
    }

    public Long getVotes(Candidate c) {
        return votes.getOrDefault(c, 0L);
    }

}
