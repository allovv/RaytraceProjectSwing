package TimeRT;

public class Timer {
    private static long start, stop;

    //----------------------------------------------------------------------------------------
    //измеряет время в миллисекундах
    public static void startTimer() { start = System.currentTimeMillis(); }
    public static void stopTimer() { stop = System.currentTimeMillis(); }

    public static long getTimeMillis() { return stop - start; }
    public static double getTimeSeconds() { return (double)(stop - start) / 1000; }
    //----------------------------------------------------------------------------------------
}
