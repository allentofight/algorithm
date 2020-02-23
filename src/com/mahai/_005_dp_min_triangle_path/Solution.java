package com.mahai._005_dp_min_triangle_path;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 2/16/20
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {
    private static int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
    };

    /**
     * 动态规划求解三角形的最短路径
     * @return
     */
    public static int traverse() {
        int ROW = 4;
        int[] mini = triangle[ROW - 1];
        // 从倒数第二行求起，因为最后一行的值本身是固定的
        for (int i = ROW - 2; i >= 0; --i) {
            for (int j = 0; j < triangle[i].length; ++j) {

                // 为0的不考虑
                if (triangle[i][j] == 0) {
                    continue;
                }

                mini[j] = triangle[i][j] + Math.min(mini[j], mini[j+1]);
            }
        }
        return mini[0];
    }

    public static  void main(String[] args)  throws Throwable {
        int minPathSum = traverse();
        System.out.println("sum = " + minPathSum);
    }
}