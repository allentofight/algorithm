package com.mahai._010_knapsack_dp;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 3/8/20
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    /**
     *
     * @param weights 各个物品的质量
     * @param knapsackWeight    背包可承受的最大质量
     * @return
     */
    public static int knapsack(int[] weights, int knapsackWeight) {
        int n = weights.length; // 物品个数
        boolean[][] states = new boolean[n][knapsackWeight+1];

        // 第一个物品不选
        states[0][0] = true;

        if (weights[0] <= knapsackWeight) {
            // 第一个物品选了
            states[0][weights[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < knapsackWeight + 1; j++) {
                // 第 i 个物品不放入背包中
                if (states[i-1][j]) {
                    states[i][j] = states[i-1][j];
                }
            }

            for (int j = 0; j <= knapsackWeight-weights[i]; ++j) {
                //把第i个物品放入背包
                if (states[i-1][j]) {
                    states[i][j+weights[i]] = true;
                }
            }
        }

        // 最后一个阶段决策后，从最后一行右到左取第一个值为 true 对应的重量
        for (int j = knapsackWeight; j >= 0; j--) {
            if (states[n-1][j]) {
                return j;
            }
        }
        return 0;
    }


    /**
     * 使用一维数组来保存每个阶段的解
     * @param weights 个物品的质量
     * @param knapsackWeight 背包可承受的最大质量
     * @return
     */
    public static int knapsack2(int[] weights, int knapsackWeight) {
        int n = weights.length; // 物品个数
        boolean[] states = new boolean[knapsackWeight+1];
        // 第一个物品不选
        states[0] = true;

        if (weights[0] <= knapsackWeight) {
            // 第一个物品选了
            states[weights[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = knapsackWeight - weights[i]; j >= 0; --j) {
                //把第i个物品放入背包
                if (states[j]) {
                    states[j + weights[i]] = true;
                }
            }
        }

        // 最后一个阶段决策后，从最后一行右到左取第一个值为 true 对应的重量
        for (int j = knapsackWeight; j >= 0; j--) {
            if (states[j]) {
                return j;
            }
        }
        return 0;
    }

    public static  void main(String[] args) {
        int[] weights = {2,2,4,6,3};
        int result = knapsack2(weights, 9);
        System.out.println("result = " + result);
    }

}