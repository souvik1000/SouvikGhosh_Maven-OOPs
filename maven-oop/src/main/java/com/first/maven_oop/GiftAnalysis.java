package com.first.maven_oop;

import org.apache.log4j.Logger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Random;

public class GiftAnalysis implements Generator<Sweet>, Iterable<Sweet> {

    private static final Logger LOG = Logger.getLogger(GiftAnalysis.class);

    private static Random rand = new Random();

    private static final int SUGAR_MIN = 0;

    private static final int SUGAR_MAX = 100;

    private static final int WEIGHT_MIN = 50;

    private static final int WEIGHT_MAX = 200;

    private static final DecimalFormat PRECISION = new DecimalFormat("#.#");

    static {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        PRECISION.setDecimalFormatSymbols(dfs);
    }

    private int size = 0;

    private Sweet[] instances = {new Chocolate(), new KajuBarfi(),
            new KhirKadam(), new Candy(),
    };

    public GiftAnalysis() {
    }

    public GiftAnalysis(int size) {
        this.size = size;
    }

    public Sweet next() {
        Sweet current = instances[rand.nextInt(instances.length)];
        double sugarParam = paramFormatter(randomSugarLevel(), PRECISION);
        double weightParam = paramFormatter(randomWeight(), PRECISION);

        try {
            return (Sweet) current.getClass()
                    .getConstructor(double.class, double.class)
                    .newInstance(sugarParam, weightParam);
            // Report programmer errors at run time:
        } catch (Exception e) {
            LOG.error("RuntimeException", e);
            throw new RuntimeException(e);
        }
    }


    private double paramFormatter(double sugarParam, DecimalFormat df) {
        return Double.parseDouble(df.format(sugarParam));
    }


    private double randomWeight() {
        return WEIGHT_MIN + (Math.random() * ((WEIGHT_MAX - WEIGHT_MIN) + 1));
    }


    private double randomSugarLevel() {
        return SUGAR_MIN + (Math.random() * ((SUGAR_MAX - SUGAR_MIN) + 1));
    }

    class SweetsIterator implements Iterator<Sweet> {
        int count = size;

        public boolean hasNext() {
            return count > 0;
        }

        public Sweet next() {
            count--;
            return GiftAnalysis.this.next();
        }

        public void remove() { 
            LOG.error("UnsupportedOperationException");
            throw new UnsupportedOperationException();
        }
    }

    ;

    public Iterator<Sweet> iterator() {
        return new SweetsIterator();
    }

}
