package ch12;

import java.util.EnumMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class PollingStation {

    private final int maxBallots;
    private final EnumMap<Candidate, Long> votes = new EnumMap<Candidate, Long>(Candidate.class);
    private final int id;
    private boolean ready = false;
    private CountDownLatch countDownLatch;
    ReentrantLock castLock = new ReentrantLock();
    private Condition readyCondition = castLock.newCondition();
    private final static Logger log = Logger.getLogger(PollingStation.class.getName());

    public PollingStation(int id, CountDownLatch countDownLatch) {
        this.id = id;
        maxBallots = Util.nextInt(100);
        this.countDownLatch = countDownLatch;
    }

    public int getId() {
        return id;
    }

    public void castVotes() {
        castLock.lock();
        try {
            IntStream.range(0, maxBallots)
                    .forEach(i -> {
                        Candidate randomCandidate = Util.getRandomCandidate();
                        votes.merge(randomCandidate, 1L, Long::sum);
                    });


            // and they are counted
            Util.sleep(Util.nextInt(1000));

            ready = true;
            readyCondition.signalAll();
            countDownLatch.countDown();
            log.info("Polling Station with id " + id + " has finished voting, votes are: " + votes);
        } 
        finally {
            castLock.unlock();
        }
    }

    public void waitUntilReady() {
        castLock.lock();
        try {
            while (!ready) {
                readyCondition.await();
            }
            
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            castLock.unlock();
        }
    }

    public Long getVotes(Candidate c) {
        return votes.getOrDefault(c, 0L);
    }

}
