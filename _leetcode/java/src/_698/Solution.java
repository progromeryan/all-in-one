package _698;

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int[] visited = new int[nums.length];
        return dfs(nums, visited, 0, k, 0, sum / k);
    }

    private boolean dfs(int[] nums, int[] visited, int start, int groupNum, int currSum, int target) {
        if (groupNum == 0) return true;

        if (currSum == target) {
            return dfs(nums, visited, 0, groupNum - 1, 0, target);
        }

        for (int i = start; i < nums.length; i++) {
            // 如果还没有被访问过
            if (visited[i] == 0) {
                visited[i] = 1;
                if (dfs(nums, visited, i + 1, groupNum, currSum + nums[i], target)) {
                    return true;
                }

                visited[i] = 0;
            }
        }

        return false;
    }
}
