package ch12;

import java.util.EnumMap;

public class ElectionOffice {

    private final EnumMap<Candidate, Long> allVotes = new EnumMap<Candidate, Long>(Candidate.class);

    public void collectVotes(PollingStation ps) {
        for (Candidate cd : Candidate.values()) {
            allVotes.merge(cd, ps.getVotes(cd), Long::sum);
        }
    }

    public Long getVotes(Candidate c) {
        return allVotes.getOrDefault(c, 0L);
    }

}
