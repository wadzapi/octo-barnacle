import java.util.concurrent.atomic.AtomicInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;


/**
 * Основной класс для вывода чисел с неповторяющимися символами 
 **/
public class WadzapiAppDaddy {
	
	//private static final int DEFAULT_MIN = Integer.MIN_VALUE;
	private static final int DEFAULT_MIN = 0;

	
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
		for (AtomicInteger counter = new AtomicInteger(min); counter.get() <= max; counter.getAndIncrement()) {
			int currVal = counter.get();
			String digitString = String.valueOf(currVal);
			List<Character> charList = digitString.chars().mapToObj(digitChar -> Character.valueOf((char)digitChar)).collect(Collectors.toList());
			Set<Character> charSet = new HashSet<>(charList);
			//System.out.println("listLen: " + charList.size());
			//System.out.println("setLen: " + charSet.size());
			//System.out.println("-------");
			if (charSet.size() == charList.size()) {
				System.out.println(digitString);
			}
			
		}
	}
	
	public static void main(String[] args) {
		//printNumbers(98, 103);
		long currTime = System.nanoTime();
		printNumbers();
		long elapsed = System.nanoTime() - currTime;
		System.out.println("elapsed : " + elapsed + " ms");
	}
	
}
