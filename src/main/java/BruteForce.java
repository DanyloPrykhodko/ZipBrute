import java.math.BigInteger;

/**
 * The BruteForce is class for brute passwords.
 */
public class BruteForce {
    private char[] chars; // Charset.
    private int passwordLength; // Password length.
    private volatile BigInteger index; // Index.
    private BigInteger lastIndex; // Last index.
    private volatile boolean isBusy; // Is busy.
    private volatile int numberOfThreads; // Number of the threads.

    /** Constructor. */
    public BruteForce(char[] chars, int passwordLength) {
        this.chars = chars;
        this.passwordLength = passwordLength;
        this.index = BigInteger.ZERO;
        this.isBusy = false;
        this.numberOfThreads = 0;
        String index = "";
        for (int i = 0; i < passwordLength; i++)
            index += chars[chars.length - 1];
        this.lastIndex = toIndex(index);
    }

    /**
     * Brute the password from index.
     * @param index Index of password.
     * @return Password.
     */
    public String brute(BigInteger index) {
        String password = "";
        // While the index is bigger than 0.
        while (index.signum() == 1) {
            password = chars[index.subtract(index.divide(BigInteger.valueOf(chars.length)).multiply(BigInteger.valueOf(chars.length))).intValue()] + password;
            index = index.divide(BigInteger.valueOf(chars.length));
        }
        // Fill the empty spaces with first char.
        while (password.length() < passwordLength)
            password = chars[0] + password;
        return password;
    }

    /**
     * Brute the next password.
     * @return Next password.
     */
    public String nextBrute() {
        synchronized (this) {
            while (numberOfThreads > 0)
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            numberOfThreads++;
            isBusy = true;
            // Brute the password.
            String password = brute(index);
            // Index increment.
            index = index.add(BigInteger.ONE);
            isBusy = false;
            numberOfThreads--;
            return password;
        }
    }

    /**
     * Get index of the password.
     * @param password Password.
     * @return Index of password.
     */
    public BigInteger toIndex(String password) {
        BigInteger index = BigInteger.ZERO;
        int[] count = new int[password.length()];
        for (int i = 0; i < password.length(); i++) {
            char c = password.toCharArray()[i];
            for (int j = 0; j < chars.length; j++)
                if (c == chars[j])
                    count[i] = j;
        }
        for (int i = 0; i < password.length(); i++) {
            BigInteger a = new BigInteger(String.valueOf(count[i]));
            for (int j = 0; j < password.length() - (i + 1); j++)
                a = a.multiply(BigInteger.valueOf(chars.length));
            index = index.add(a);
        }
        return index;
    }

    /** Get brute force progress. */
    public double getProgress() {
        return index.multiply(BigInteger.valueOf(10000L)).divide(lastIndex).doubleValue() / 10000.0;
    }

    /** The index is less than the last index. */
    public boolean isAlive() {
        return index.compareTo(lastIndex) == -1;
    }

    /** The nextBrute method is busy. */
    public boolean isBusy() {
        return isBusy;
    }

    public BigInteger getIndex() {
        return index;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    public BigInteger getLastIndex() {
        return lastIndex;
    }
}
