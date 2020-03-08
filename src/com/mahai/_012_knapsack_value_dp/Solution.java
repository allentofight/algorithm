package com.mahai._012_knapsack_value_dp;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 3/8/20
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    // 最终的解：即背包可放入的最大重量
    private static int cv = Integer.MIN_VALUE;

    // 每个物品的重量
    private static int[] weights = {2,2,4,6,3};

    // 每个物品的价值
    private static int[] values = {3,4,8,9,6};

    // 背包能承载的最大重量
    private static final int knapsackWeigth = 9;

    /**
     *
     * @param weights 各个物品的重量
     * @param weights 各个物品的价值
     * @param knapsackWeight    背包可承受的最大质量
     * @return
     */
    public static int knapsack(int[] weights, int values[], int knapsackWeight) {
        int n = weights.length; // 物品个数
        int[][] states = new int[n][knapsackWeight+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < knapsackWeight+1; j++) {
                states[i][j]  = -1;
            }
        }

        // 第一个物品不选
        states[0][0] = 0;

        if (weights[0] <= knapsackWeight) {
            // 第一个物品选了
            states[0][weights[0]] = values[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < knapsackWeight + 1; j++) {
                // 第 i 个物品不放入背包中
                if (states[i-1][j] > 0) {
                    states[i][j] = states[i-1][j];
                }
            }

            for (int j = 0; j <= knapsackWeight-weights[i]; ++j) {
                //把第i个物品放入背包
                if (states[i-1][j] >= 0) {
                    states[i][j+weights[i]] = Math.max(states[i-1][j] + values[i], states[i][j+weights[i]]);
                }
            }
        }

        // 求出
        int max = Integer.MIN_VALUE;
        for (int j = knapsackWeight; j >= 0; j--) {
            max = Math.max(max, states[n-1][j]);
        }
        return max;
    }

    /**
     * 使用一维数组来保存每个阶段的解
     * @param weights 各个物品的重量
     * @param weights 各个物品的价值
     * @param knapsackWeight    背包可承受的最大质量
     * @return
     */
    public static int knapsack2(int[] weights, int values[], int knapsackWeight) {
        int n = weights.length; // 物品个数

        // 改用一维数组来保存每个阶段的状态，减少空间复杂度
        int[] states = new int[knapsackWeight+1];

        for (int j = 0; j < knapsackWeight+1; j++) {
            states[j]  = -1;
        }

        // 第一个物品不选
        states[0] = 0;

        if (weights[0] <= knapsackWeight) {
            // 第一个物品选了
            states[weights[0]] = values[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = knapsackWeight-weights[i]; j >= 0; --j) {
                //把第i个物品放入背包
                if (states[j] >= 0) {
                    states[j+weights[i]] = Math.max(states[j] + values[i], states[j+weights[i]]);
                }
            }
        }

        // 所有阶段结束后求出 states 中的最大解
        int max = Integer.MIN_VALUE;
        for (int j = knapsackWeight; j >= 0; j--) {
            max = Math.max(max, states[j]);
        }
        return max;
    }


    public static void main(String[] args) {
        int result = knapsack(weights, values, knapsackWeigth);
        System.out.println("result = " + result);
    }
}