import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Map<Integer, Integer> SIZWE_TO_FREQ = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int numberOfThread = i + 1;
            new Thread(() -> {
                String result = generateRoute("RLRFR", 100);
                int resultLength = result.replaceAll("[^R]", "").length();
                synchronized (SIZWE_TO_FREQ) {
                    if (SIZWE_TO_FREQ.containsKey(resultLength)) {
                        SIZWE_TO_FREQ.put(resultLength, SIZWE_TO_FREQ.get(resultLength) + 1);
                    } else {
                        SIZWE_TO_FREQ.put(resultLength, 1);
                    }
                }
                System.out.println("Поток №" + numberOfThread + " число R - " + resultLength);
            }).start();
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}