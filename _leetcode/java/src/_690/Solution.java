package _690;

import java.util.*;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();

        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }

        return helper(map, id);
    }

    private int helper(HashMap<Integer, Employee> map, int id) {
        if (!map.containsKey(id)) return 0;

        Employee employee = map.get(id);

        int res = employee.importance;
        for (int subId : employee.subordinates) {
            res += helper(map, subId);
        }

        return res;
    }
}