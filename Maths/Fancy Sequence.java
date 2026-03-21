/*
  Problem Link: https://leetcode.com/problems/fancy-sequence/
*/

//Approach - Simple maths + Binary Exponentiation and Fermat's Little Theorem
//T.C : O(log(M)) for Binary Exponentiation power(mult, M-2)
//S.C : O(1)
class Fancy {

    List<Long> seq;
    long multiplier = 1;
    long add = 0;
    int mod = 1000000007;

    public long power(long a, long b) {
        if (b == 0)
            return 1;

        long half   = power(a, b / 2);
        long result = (half * half) % mod;

        if (b % 2 == 1) {
            result = (result * a) % mod;
        }

        return result;
    }

    public Fancy() {
        seq = new ArrayList<>();
    }
    
    public void append(int val) {
        long x = ((val - add) % mod + mod) * power(multiplier, mod - 2) % mod;
        seq.add(x);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % mod;
    }
    
    public void multAll(int m) {
        multiplier = (multiplier * m) % mod;
        add = (add * m) % mod;
    }
    
    public int getIndex(int idx) {
        if(idx >= seq.size()) 
            return -1;

        return (int) ((multiplier * seq.get(idx) + add) % mod);    
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
