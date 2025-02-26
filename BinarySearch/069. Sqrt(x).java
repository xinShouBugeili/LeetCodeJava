/**
 * #题目：
 * #Implement int sqrt(int x).
 *
 * #Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * #Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 */

/**
 * 解题思路：binary search
 * 0. 辅助函数的input是posX and posY，target，返回k
 * 0.5 if target > 临界值
 * 1. k = (x + y) / 2 > 65534 ? 65534 : (x + y) / 2
 * 1.1 k*k <= target && (k+1)*(k+1) > target 》 return k
 * 1.2 (k+1)*(k+1) < target 〉return (k, y)
 * 1.3 k * k > target 》 return (x, k)
 * 边界条件：
 * 1. target = 0 > return 0
 * 2. target = 1 > return 1
 * 3. target > 65535^2, return 65535
 */
class Solution_069 {
    public int solution(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }

        int threshold = 0;

        for (int i = (int) (32768 * 1.414); i < 32768 * 1.415; i ++) {
            if (i * i < 0) {
                threshold = i -1;
                break;
            }
        }

        if (x >= threshold * threshold) {
            return threshold;
        }

        int mid = x / 2;

        if (mid > threshold - 1) {
            mid = threshold - 1;
        }
        return solutionA(x, 0, mid * 2);
    }

    public int solutionA(int target, int x, int y) {
        int k = (x + y) / 2;
        if (k * k <= target && (k + 1) * (k + 1) > target) {
            return k;
        }
        if (k * k > target) {
            return solutionA(target, x, k);
        }
        return solutionA(target, k, y);
    }

    public static void main(String[] args) {
        int x = 32768*32768 + 1;
        Solution_069 solution069 = new Solution_069();
        System.out.println(solution069.solution(x));
    }
}

