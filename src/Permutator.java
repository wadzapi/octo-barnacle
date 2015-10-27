    import java.util.*;
    import java.util.stream.Collectors;

    public class Permutator implements Enumeration<Integer> {
        
        private int n;
        private Integer[] nums;
        private boolean[] arrows;
        private boolean hasNext;
        private long count, cur;
        
        public Permutator(int n) {
            this.n = n;
            nums = new Integer[n];
            arrows = new boolean[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
                arrows[i] = false;
            }
            
            count = 1;
            for (int i = 1; i <= n; i++) {
                count *= i;
            }
            cur = 0;
        }
        
        public void step() {
            int biggest = 0, pos = -1;
            
            cur++;
            
            for (int i = 1; i < n; i++) {
                if (!arrows[i]) {
                    if (nums[i] > nums[i - 1] && nums[i] > biggest) {
                        pos = i;
                        biggest = nums[pos];
                    }
                }
                if (arrows[i - 1]) {
                    if (nums[i] < nums[i - 1] && nums[i - 1] > biggest) {
                        pos = i - 1;
                        biggest = nums[pos];
                    }
                }
            }
            
            if (pos < 0) {
                return;
            }
            
            int nei = pos + (arrows[pos] ? 1 : -1);
            nums[pos] = nums[nei];
            nums[nei] = biggest;
            boolean t = arrows[pos];
            arrows[pos] = arrows[nei];
            arrows[nei] = t;
            
            for (int i = 0; i < n; i++) {
                if (nums[i] > biggest) {
                    arrows[i] = !arrows[i];
                }
            }
        }
        
        
        //TODO Встроить логгер
        public static void main(String... args) {
            System.out.println("Вызов метода main для класса Permutator");
            int maxDigits = Integer.MAX_VALUE / 10;
			System.out.println("Начало перестановок для " + maxDigits + " элементов");
			long currTime = System.nanoTime();
            for (int n = 1; n < maxDigits; n++) {
				System.out.println("Начало перестановки из " + n + " элементов");
				Permutator pr = new Permutator(n);
				while (pr.hasMoreElements()) {
					System.out.println(String.valueOf(pr.nextElement()));
				}
			}
			long elapsed = System.nanoTime() - currTime;
		    System.out.println("elapsed : " + elapsed + " ms");
        }

        @Override
        public boolean hasMoreElements() {
            return cur < count;
        }

        @Override
        public Integer nextElement() {
            List<Integer> digitsList = Arrays.asList(Arrays.copyOf(nums, nums.length));
            step();
            String elemString = digitsList.stream().map(digit -> digit.toString()).collect(Collectors.joining());
            return Integer.valueOf(elemString);
        }
    }
