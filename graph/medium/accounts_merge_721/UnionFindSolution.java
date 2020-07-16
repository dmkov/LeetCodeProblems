package graph.medium.accounts_merge_721;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 721. Accounts Merge
 https://leetcode.com/problems/accounts-merge/
 
 Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

 Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

 After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

 Example 1:
 Input:
 accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 Explanation:
 The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 The second John and Mary are different people as none of their email addresses are used by other accounts.
 We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

 --------

 1. Complexity
    1.1 Time Complexity is O(nlogn) because of sorting
    1.2 Space Complexity is O(n)
 2. Approach
    2.1 Put relations between accounts (edges), then use BFS to collect linked path
 */

class UnionFindSolution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
            return result;
        }

        /**
         - initialize UF
         - iterate over lists
             - put emails into the sets -> index:set map
             - put emails into the string:position map
             - for every existing email:
                - union current index and index from string:position map
         - for every index:set map entity:
            if parent != itself -> append to the parent and remove from the map
         - for every index:set map entity:
            construct result with sorting
         */

        UF uf = new UF(accounts.size());
        Map<String, Integer> emailToAccount = new HashMap<>();
        Map<Integer, Set<String>> accountEmails = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> list = accounts.get(i);
            if (list.size() < 1) {
                continue;
            }

            Set<String> emails = new HashSet<>();
            for (int j = 1; j < list.size(); j++) {
                Integer indx = emailToAccount.get(list.get(j));
                if (indx != null) {
                    uf.union(i, indx);
                }

                emailToAccount.put(list.get(j), i);
                emails.add(list.get(j));
            }
            accountEmails.put(i, emails);
        }

        for (int i = 0; i < accounts.size(); i++) {
            int parent = uf.find(i);
            if (parent != i) {
                accountEmails.get(parent).addAll(accountEmails.get(i));
                accountEmails.remove(i);
            }
        }

        for (Map.Entry<Integer, Set<String>> entry : accountEmails.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(entry.getKey()).get(0));

            List<String> sorted = new ArrayList<>(entry.getValue());
            Collections.sort(sorted);
            list.addAll(sorted);

            result.add(list);
        }

        return result;
    }

    class UF {
        int[] parent;

        public UF(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int i) {
            while (i != parent[i]) {
                i = parent[parent[i]];
            }
            return i;
        }

        public void union(int a, int b) {
            parent[find(a)] = find(b);
        }
    }

}
