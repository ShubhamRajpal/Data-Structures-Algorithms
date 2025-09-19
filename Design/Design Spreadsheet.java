/*
  Problem Link: https://leetcode.com/problems/design-spreadsheet/
*/

//Approach (Simple simulation)
// T.C. : Constructor → O(rows), setCell / resetCell → O(1), getValue → O(L) for substr where L = length of formula
// S.C. : O(1)
class Spreadsheet {
    int[][] matrix;

    public Spreadsheet(int rows) {
        matrix = new int[rows][26]; // rows x 26 (A-Z)
    }

    private int[] getCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;

        return new int[] { row, col };
    }

    public void setCell(String cell, int value) {
        int[] getRowCol = getCell(cell);
        matrix[getRowCol[0]][getRowCol[1]] = value;
    }

    public void resetCell(String cell) {
        int[] getRowCol = getCell(cell);
        matrix[getRowCol[0]][getRowCol[1]] = 0;
    }

    private int solve(String s) {
        if (Character.isDigit(s.charAt(0))) {
            return Integer.parseInt(s);
        }

        int[] getRowCol = getCell(s);
        return matrix[getRowCol[0]][getRowCol[1]];
    }

    public int getValue(String formula) {
        // Example: "=A1+B2"
        int plusInd = formula.indexOf('+');
        String X = formula.substring(1, plusInd); // Remove '='
        String Y = formula.substring(plusInd + 1);

        return solve(X) + solve(Y);

    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
