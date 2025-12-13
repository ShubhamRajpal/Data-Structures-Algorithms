/*
  Problem Link: https://leetcode.com/problems/coupon-code-validator/
*/

//Approach (Using map and sorting)
//T.C : O(n*l + nlogn) where n = code.size() and l = average length of codes
//S.C : O(n)
class Pair {
    String code, bLine;

    public Pair(String c, String b) {
        this.code = c;
        this.bLine = b;
    }
}

class Solution {

    public boolean isAlphanumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        return (str.matches("^[a-zA-Z0-9_]+$"));
    }

    public boolean isCouponValid(String code, String bLine, boolean isActive, Map<String, Integer> businessLineOrder) {
        if (!isActive)
            return false;

        if (!isAlphanumeric(code))
            return false;

        if (!businessLineOrder.containsKey(bLine)) {
            return false;
        }

        return true;

    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        int n = code.length;
        List<Pair> validCoupons = new ArrayList<>();
        Map<String, Integer> businessLineOrder = new HashMap<>();

        businessLineOrder.put("electronics", 0);
        businessLineOrder.put("grocery", 1);
        businessLineOrder.put("pharmacy", 2);
        businessLineOrder.put("restaurant", 3);
        
        for (int i = 0; i < n; i++) {
            if (isCouponValid(code[i], businessLine[i], isActive[i], businessLineOrder)) {
                validCoupons.add(new Pair(code[i], businessLine[i]));
            }
        }

        validCoupons.sort((a, b) -> {
            int res = Integer.compare(businessLineOrder.get(a.bLine), businessLineOrder.get(b.bLine));
            if (res == 0) {
                return a.code.compareTo(b.code);
            }

            return res;
        });

        List<String> ans = new ArrayList<>();
        for (Pair p : validCoupons) {
            ans.add(p.code);
        }

        return ans;

    }
}
