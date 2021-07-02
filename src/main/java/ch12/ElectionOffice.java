package ch12;

import java.util.BitSet;
import java.util.EnumMap;

public class ElectionOffice {

    private final EnumMap<Candidate, Long> allVotes = new EnumMap<Candidate, Long>(Candidate.class);
    private final BitSet collected;

    public ElectionOffice(int numStations) {
        collected = new BitSet(numStations);
    }

    public synchronized void collectVotes(PollingStation ps) {
        for (Candidate cd : Candidate.values()) {
            allVotes.merge(cd, ps.getVotes(cd), Long::sum);
        }
        collected.set(ps.getId());
        notifyAll();
    }

    private synchronized void waitUntilReady()  {
        while(collected.nextClearBit(0) != collected.length()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Long getVotes(Candidate c) {
        waitUntilReady();
        return allVotes.getOrDefault(c, 0L);
    }

}
