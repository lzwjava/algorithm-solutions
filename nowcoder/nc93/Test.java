package nowcoder.nc93;

/**
 * Created by lzw on 2021/11/20 11:56 AM.
 */
public class Test {

    public static void run() {
        int[][] arr = new int[][]{{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        int[] lru = new Solution().LRU(arr, 3);
        System.out.println(lru);
    }
}
