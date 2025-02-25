/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
 */

/**
 * 解题思路
 * 1. 二分递归，每次把n除以2，把x 乘以x，
 * 2. 如果n是奇数，先把n - 1，最后结果再乘以x
 * 3. 如果n是负数 先计算n是正数，然后用1除以
 * 边界条件
 * 1. x = 0 > return 0
 * 2. x = 1 > return 1
 * 3. n = 1 > return x
 */
class Solution_050 {
    public double solution(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        /**
         * 这段代码在处理负数幂时会栈溢出，
         * 主要是因为当 n = Integer.MIN_VALUE (-2147483648) 时，执行 n = -1 * n 会导致整数溢出，
         * n 的值仍然是 -2147483648。这导致了无限递归。
         */
        if (n == Integer.MIN_VALUE) {
            x = x * x;
            n = n/2;
        }

        boolean abs = false;
        if (n < 0) {
            n = -1 * n;
            abs = true;
        }
        boolean multiOne = false;
        if (n / 2 * 2 != n) {
            n = n - 1;
            multiOne = true;
        }
        return multiOne
                ? abs
                ? 1 / solutionA(x * x, n / 2) / x
                : solutionA(x * x, n / 2) * x
                : abs
                ? 1 / solutionA(x * x, n / 2)
                : solutionA(x * x, n / 2);
    }

    public double solutionA(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        boolean multiOne = false;
        if (n / 2 * 2 != n) {
            n = n - 1;
            multiOne = true;
        }
        return multiOne ? solutionA(x * x, n / 2) * x : solutionA(x * x, n / 2);
    }
}