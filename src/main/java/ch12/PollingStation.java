package ch12;

import java.util.EnumMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PollingStation {

    private final EnumMap<Candidate, Long> votes = new EnumMap<Candidate, Long>(Candidate.class);

    private final int maxBallots;
    private final int id;
    private final CountDownLatch myLatch;

    final Lock lock = new ReentrantLock();
    final Condition votingInProgress  = lock.newCondition();
    private boolean ready = false;

    public PollingStation(CountDownLatch stationsLatch, int id) {
        this.maxBallots = Util.nextInt(100);
        this.id = id;
        this.myLatch = stationsLatch;
    }

    public int getId() {
        return id;
    }

    public void castVotes() {
        try {
            lock.lock();
            // voting
            Util.sleep(Util.nextInt(1000));

            IntStream.range(0, maxBallots)
                    .forEach(i -> {
                        Candidate randomCandidate = Util.getRandomCandidate();
                        votes.merge(randomCandidate, 1L, Long::sum);
                    });

            // counting
            Util.sleep(Util.nextInt(1000));

        } finally {
            ready = true;
            myLatch.countDown();
            votingInProgress.signal();
            lock.unlock();
        }
    }

    private void waitUntilReady() {
        try {
            lock.lock();
            while (!ready) {
                votingInProgress.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Long getVotes(Candidate c) {
        waitUntilReady();
        return votes.getOrDefault(c, 0L);
    }

}
