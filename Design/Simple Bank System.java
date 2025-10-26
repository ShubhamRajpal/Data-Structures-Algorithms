/*
  Problem Link: https://leetcode.com/problems/simple-bank-system/  
*/

// Approach (Simple Simulation)
//T.C. : O(n) for Bank(), O(1) for withdraw(), transfer() and deposit()
//S.C. : O(n) for storing balance for each object
class Bank {
    private long[] balance;
    private int n;

    public Bank(long[] balance) {
        this.balance = balance;
        this.n = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (account1 > n || account2 > n || balance[account1 - 1] < money) {
            return false;
        }

        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > n) {
            return false;
        }

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > n || balance[account - 1] < money) {
            return false;
        }

        balance[account - 1] -= money;
        return true;
    }
}
