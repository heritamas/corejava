package ch12;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Logger;

public class Election {

    private final static int NUM_POLLING_STATIONS = 50;
    private final static Logger log = Logger.getLogger(Election.class.getName());

    private PollingStation[] stations = new  PollingStation[NUM_POLLING_STATIONS];
    private ThreadGroup casting = new ThreadGroup("casting");
    private ThreadGroup aggregation = new ThreadGroup("aggregation");
    private ElectionOffice eo = new ElectionOffice(NUM_POLLING_STATIONS);

    private void runElection() throws InterruptedException {
        for (int i = 0; i < NUM_POLLING_STATIONS ; ++i) {

            final PollingStation ps = new PollingStation(i);
            final int stationId = i;

            stations[stationId] = ps;

            // init polling stations, and voting
            new Thread(casting, () -> {
                // cast votes
                ps.castVotes();
            }).start();

            // collecting
            new Thread(aggregation, () -> {
                // collect votes when ready
                eo.collectVotes(ps);
            }).start();

        }


    }

    private void checkElectionFraud() {
        for (Candidate cd : Candidate.values()) {
            Long checkedVotes = Arrays.stream(stations)
                    .map(ps -> ps.getVotes(cd))
                    .mapToLong(Long::longValue)
                    .sum();

            Long aggregatedVotes = eo.getVotes(cd);

            String msg = MessageFormat.format("aggregated: {0,number} and checked: {1,number} votes for candidate: {2}", aggregatedVotes, checkedVotes, cd);
            assert aggregatedVotes.equals(checkedVotes) : "fraud! " + msg ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Election el = new Election();
        el.runElection();
        el.checkElectionFraud();
    }

}
