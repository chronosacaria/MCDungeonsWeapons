package chronosacaria.mcdw.api.util;

import java.util.Random;

public class CleanlinessHelper {
    static Random random = new Random();

    public static boolean percentToOccur (int chance) {
        return random.nextInt(100) <= chance;
    }
}
