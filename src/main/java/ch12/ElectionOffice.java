package ch12;

import java.util.concurrent.ConcurrentHashMap;

public class ElectionOffice {

    private final ConcurrentHashMap<Candidate, Long> allVotes = new ConcurrentHashMap<Candidate, Long>();

    public void collectVotes(PollingStation ps) {
        for (Candidate cd : Candidate.values()) {
            allVotes.merge(cd, ps.getVotes(cd), Long::sum);
        }
    }

    public Long getVotes(Candidate c) {
        return allVotes.getOrDefault(c, 0L);
    }

}
