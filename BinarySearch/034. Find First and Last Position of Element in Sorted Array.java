/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 */

class Solution_034 {
    /**
     * 思路：
     * 三个函数，寻找当前数组的头尾pos of target A 和寻找当前数组min pos of target B 和max pos of target C
     * 其实B和C是A函数结果取一部分
     * 可以在写代码的时候先写B和C，然后后面换成A
     * 1. A
     * 1.1 头是
     * 1.1.1 尾是 》 输出头尾
     * 1.1.2 尾不是
     * 1.1.2.1 mid 是 〉 mid + 尾 - C
     * 1.1.2.2 mid 不是 〉头 + mid - C
     * 1.2 头不是
     * 1.2.1 尾是
     * 1.2.1.1 mid 是 》 头 + mid - B
     * 1.2.1.2 mid 不是 〉 mid + 尾 - B
     * 1.2.2 尾不是
     * 1.2.2.1 mid 是 》 头 + mid - B and 尾 + mid - C
     * 1.2.2.2 mid 大于 target 》 头 + mid - A
     * 1.2.2.3 mid 小于 target 〉 尾 + mid - A
     *
     * 边界条件：
     * 1. num == null || num.length == 0, [-1, -1]
     * 2. num.length == 1 [x, x] or [-1, -1]
     * 3. num.length == 2 [x, x] or [y, y] or [x, y] or [-1, -1]
     * @param nums
     * @param target
     * @return
     */
    public int[] solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        return solutionA(nums, target, 0, nums.length - 1);
    }

    public int[] solutionA(int[] nums, int target, int x, int y) {
        if (x == y) {
            if (nums[x] == target) {
                return new int[]{x, x};
            }
            return new int[]{-1, -1};
        }
        if (x + 1 == y) {
            if (nums[x] == target) {
                if (nums[y] == target) {
                    return new int[] {x, y};
                } else {
                    return new int[] {x, x};
                }
            }
            if (nums[y] == target) {
                return new int[]{y,y};
            }
            return new int[]{-1, -1};
        }

        if (nums[x] == target) {
            if (nums[y] == target) {
                return new int[]{x, y};
            }
            int k = (x + y) / 2;
            if (nums[k] == target) {
                return new int[] {x, solutionA(nums, target, k, y)[1]};
            }
            return new int[]{x, solutionA(nums, target, x, k)[1]};
        }
        if (nums[y] == target) {
            int k = (x + y) / 2;
            if (nums[k] == target) {
                return new int[]{solutionA(nums, target, x, k)[0], y};
            }
            return new int[]{solutionA(nums, target, k, y)[0], y};
        }
        int k = (x + y) / 2;
        if (nums[k] == target) {
            return new int[]{solutionA(nums, target, x, k)[0], solutionA(nums, target, k, y)[1]};
        }
        if (nums[k] > target) {
            return solutionA(nums, target, x, k);
        }
        return solutionA(nums, target, k, y);
    }
}