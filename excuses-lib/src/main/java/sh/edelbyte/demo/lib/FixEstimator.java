package sh.edelbyte.demo.lib;

import sh.edelbyte.demo.duration.FixDuration;
import sh.edelbyte.demo.duration.FixDurationPhrases;

import java.util.List;
import java.util.Random;

public class FixEstimator {

    private final Random random;
    private final List<String> phrases;

    public FixEstimator() {
        this.random = new Random();
        this.phrases = FixDurationPhrases.PHRASES;
    }

    public FixEstimator(long seed) {
        this.random = new Random(seed);
        this.phrases = FixDurationPhrases.PHRASES;
    }

    public FixDuration estimateFixDuration() {
        String phrase = phrases.get(random.nextInt(phrases.size()));
        return new FixDuration(phrase);
    }
}
