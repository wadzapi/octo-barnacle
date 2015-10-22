import java.util.concurrent.atomic.AtomicInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Основной класс для вывода чисел с неповторяющимися символами 
 **/
public class WadzapiAppDaddy {
	
	private static final int DEFAULT_MIN = Integer.MIN_VALUE;
	
	private static final int DEFAULT_MAX = Integer.MAX_VALUE;
	
	/**
	 * Конструктор класса
	 **/
	private WadzapiAppDaddy() {
		System.out.println("Test App 4 AppDaddy! Hey-hey-hey");
	}
	
	public static void printNumbers() {
		printNumbers(DEFAULT_MIN, DEFAULT_MAX);
	}
	
	public static void printNumbers(int min, int max) {
		for (AtomicInteger counter = new AtomicInteger(min); counter.get() < max; counter.getAndIncrement()) {
			String digitString = String.valueOf(counter.get());
			//System.out.println("digitString: " + digitString);
			//System.out.println("digits as stream foreach: ");
			//digitString.chars().mapToObj(digitChar -> (char)digitChar).forEach(System.out.println(digitChar));
			//digitString.chars().forEach(digitChar -> System.out.println((char)digitChar));
			//List<Character> charList = Arrays.asList(digitString.toCharArray());
			char[] chars = digitString.toCharArray();
			System.out.println(chars.length);
			List<Long> listLongs = Arrays.stream(new long[] {1L, 2L, 3L, 4L}).boxed().collect(Collectors.toList());
			System.out.println(listLongs.size());
			System.out.println("-------");
			//List<Character> digitList = Arrays.stream(chars).boxed().collect(Collectors.toList());
			//System.out.println(new HashSet(chars));
			//System.out.println("digitList len: " + digitList.size());
			
		}
	}
	
	public static void main(String[] args) {
		printNumbers(0, 10);
	}
	
}
