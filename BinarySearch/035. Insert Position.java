/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 */

/**
 * 二分查找就是递归，想好了if else，想好了边界条件再写，辅助函数的参数一般是nums[] target posX posY
 */
class Solution_035 {
    /**
     * 解题思路：
     * 1.1 左等于 》 return 左
     * 1.2 右等于 〉 return 右边
     * 1.3 都不等于
     * 1.3.0 小于左 》 左 大于右 〉 右 + 1
     * 1.3.1 左 + 1 == 右 return 右
     * 1.3.2 不是这个case
     * 1.3.2.1 等于中 》 return中
     * 1.3.2.2 小于中 〉 return 左中
     * 1.3.2.3 大于中 》 return 中右
     * 边界条件：
     * 1. nums == null || nums.length == 0 -1
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return solutionA(nums, target, 0, nums.length - 1);
    }

    public int solutionA(int[] nums, int target, int x, int y) {
        if (nums[x] == target) {
            return x;
        }
        if (nums[y] == target) {
            return y;
        }
        if (nums[x] > target) {
            return x;
        }
        if (nums[y] < target) {
            return y + 1;
        }
        if (x + 1 == y) {
            return y;
        }
        int k = (x + y) / 2;
        if (nums[k] == target) {
            return k;
        }
        if (nums[k] < target) {
            return solutionA(nums, target, k, y);
        }
        return solutionA(nums, target, x, k);
    }
}