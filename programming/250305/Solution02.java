import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Solution02 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Stream");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        logger.info(numbers.toString());
        logger.info(numbers.stream().map(x -> x * 2).toList().toString());
    }
}
