package _636;

import java.util.List;
import java.util.Stack;

class Solution {

    class Log {
        int id;
        String status;
        int time;

        Log(String log) {
            String[] content = log.split(":");
            id = Integer.parseInt(content[0]);
            status = content[1];
            time = Integer.parseInt(content[2]);
        }
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastEndTime = 0;

        for (String log : logs) {
            Log logObj = new Log(log);

            if (logObj.status.equals("start")) {
                if (!stack.isEmpty()) {
                    int id = stack.peek();
                    res[id] += logObj.time - lastEndTime;
                }
                lastEndTime = logObj.time;
                stack.push(logObj.id);
            } else {
                int id = stack.pop();
                res[id] += logObj.time - lastEndTime + 1;
                lastEndTime = logObj.time + 1;
            }
        }

        return res;
    }
}