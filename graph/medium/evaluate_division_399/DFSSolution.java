package graph.medium.evaluate_division_399;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 399. Evaluate Division
 https://leetcode.com/problems/evaluate-division/

 Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

 According to the example above:
 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 --------

 1. Complexity
     1.1 Time Complexity is O(n)
     1.2 Space Complexity is O(n)
 2. Approach
     2.1 First, we need to create edges between variables to link them according to equations.
     2.2 Then we do a DFS to find required variable starting the given one and multiply edge weight after passing it.
 3. Implementation
     3.1 Add new classes to keep Edge with value and Variable with edges to the next variables.
     3.1 For every equation, check both variables in the map (if they do not exist - create a new object). Then, set an Edge
        for both of them with weight and 1/weight for opposite variable. At the end we have graph with linked variables.
     3.2 For every query:
        3.2.1 Check if variable exists in the map, if not - return -1
        3.3.2 If variable 1 equals variable 2, return 1 as it is a/a query
        3.3.3 For others iterate all edges of variable 1 and check linked variables. Then iterate their edges and variables
            and continue until you rich the variable 2. Over the pass multiply edge value by previous value and final result
            will be the answer.
 */

class DFSSolution {
    class Variable {
        List<Edge> edges = new ArrayList<>();
    }

    class Edge {
        Variable to;
        double val;

        public Edge(Variable to, double val) {
            this.to = to;
            this.val = val;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || values == null || equations.size() != values.length) {
            return new double[0];
        }

        Map<String, Variable> map = new HashMap<>();
        int i = 0;
        for (List<String> equation : equations) {
            Variable var1 = getVariable(map, equation.get(0));
            Variable var2 = getVariable(map, equation.get(1));
            double result = values[i];

            var1.edges.add(new Edge(var2, result));
            var2.edges.add(new Edge(var1, 1/result));

            i++;
        }

        double[] result = new double[queries.size()];
        i = 0;
        for (List<String> query : queries) {
            Variable var1 = map.get(query.get(0));
            Variable var2 = map.get(query.get(1));

            if (var1 == null || var2 == null) {
                result[i] = -1;
            } else if (var1 == var2) {
                result[i] = 1;
            } else {
                result[i] = dfs(var1, null, var2, 1);
            }

            i++;
        }

        return result;
    }

    private Variable getVariable(Map<String, Variable> map, String name) {
        Variable result = map.getOrDefault(name, new Variable());
        map.put(name, result);

        return result;
    }

    private double dfs(Variable from, Variable prev, Variable searched, double product) {
        if (from.edges == null) {
            return -1;
        }

        for (Edge edge : from.edges) {
            double res = product * edge.val;
            Variable v = edge.to;
            if (edge.to == searched) {
                return res;
            } else if (edge.to != prev) {
                double val = dfs(edge.to, from, searched, res);
                if (val != -1) {
                    return val;
                }
            }
        }

        return -1;
    }
}
