import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Main {

    public static final Map<Integer, Integer> SIZWE_TO_FREQ = new HashMap<>();

    public static void main(String[] args) {


        for (int i = 0; i < 1000; i++) {
            int numberOfThread = i + 1;
            new Thread(() -> {
                String result = generateRoute("RLRFR", 100);
                int resultLength = result.replaceAll("[^R]", "").length();
                //int resultLength = (int) route.chars().filter(ch -> ch == 'R').length();

                synchronized (SIZWE_TO_FREQ) {
                    if (SIZWE_TO_FREQ.containsKey(resultLength)) {
                        SIZWE_TO_FREQ.put(resultLength, SIZWE_TO_FREQ.get(resultLength) + 1);
                    } else {
                        SIZWE_TO_FREQ.put(resultLength, 1);
                    }
                }
                System.out.println("- " + numberOfThread + " число R - (" + resultLength + " раз)");//

            }).start();

        }

        Map.Entry<Integer, Integer> max = SIZWE_TO_FREQ
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();

        System.out.println("Самое частое количество повторений  " + max.getKey()
                + "(встретилось  " + max.getValue() + " раз)");
    }

    //Для генерации маршрутов вы используете функцию:
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
