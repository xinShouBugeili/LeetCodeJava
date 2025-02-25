/**
 * 题目：
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 *         (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 */

/**
 * 边界条件：
 * 1. nums 0 元素
 * 2. nums 1 元素
 * 3. nums 队首 < 队尾
 *
 * 二分查找边界条件
 * 1. nums 0 元素
 * 2. nums 1 元素
 * 3. while循环里，先判断nums[i], 再判断nums[j], 再判断i + 1 != j，再找中间k
 *
 * 找最大元素的index边界条件
 * 1. nums 0 元素
 * 2. nums 1 元素
 * 3. nums 首 小于 尾 返回 0
 * */
class Solution_033 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        int maxValueIndex = binarySearchMaxIndex(nums);
        int result_1 = binarySearch(nums, target, 0, maxValueIndex);
        int result_2 = binarySearch(nums, target, maxValueIndex + 1, nums.length - 1);

        return result_1 == -1 ? result_2 == -1 ? -1 : result_2 : result_1;
    }

    public static int binarySearch(int[] nums, int target, int minCoor, int maxCoor) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int i = minCoor;
        int j = maxCoor;

        do {
            // 1. 判断首尾是否等于target
            if (nums[i] == target) {
                return i;
            }
            if (nums[j] == target) {
                return j;
            }
            // 2. 判断首尾之间还有没有元素
            if (i + 1 == j) {
                return -1;
            }
            // 3. 判断元素在首尾里面
            if (nums[i] > target || nums[j] < target) {
                return -1;
            }
            // 4. 元素等于新的边界吗 这里向下取整
            int k = (i + j) / 2;
            if (nums[k] == target) {
                return k;
            }
            // 5. 设置新的边界是最大还是最小边界
            if (nums[k] > target) {
                j = k;
                continue;
            }
            if (nums[k] < target) {
                i = k;
                continue;
            }
        } while(i != j);

        return -1;
    }

    public static int binarySearchMaxIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return 0;
        }
        int i = 0;
        int j = nums.length - 1;
        if (nums[i] < nums[j]) {
            return 0;
        }
        do {
            if (nums[i] > nums[i+1]) {
                return i;
            }
            int k = (i + j) / 2;
            if (nums[k] < nums[i]) {
                j = k;
            }
            if (nums[k] > nums[i]) {
                i = k;
            }
        } while (i != j);
        return -1;
    }

    public static void main(String[] args) {
        int[] testArray = new int[]{0, 1, 2, 4, 5, 6, 7};
        System.out.println(Solution_033.binarySearch(testArray, 3, 0, testArray.length - 1));

        int[] testArray2 = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(Solution_033.binarySearchMaxIndex(testArray2));

        System.out.println(Solution_033.search(testArray2, 4));
    }
}