package com.mahai._011_knapsack_value_recursive;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 3/8/20
 * Time: 7:28 PM
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

    private static void knapsack(int i, int w, int v) {
        // 物品有多少个
        int weightSize = weights.length;

        // 物品选完或者背包里选的物品总质量超过了背包可承载的总重量，递归结束
        if (i > weightSize-1 || w >= knapsackWeigth) {
            if (w <= knapsackWeigth) {
                cv = Math.max(cv, v);
            }
            return;
        }

        // 第 i 个物品不选
        knapsack(i + 1, w, v);

        if (w + weights[i] <= knapsackWeigth) {
            // 选了第 i 个物品
            knapsack(i + 1, w + weights[i], v + values[i]);
        }
    }

    public static  void main(String[] args) {
        knapsack(0, 0, 0);
        System.out.println("cv = " + cv);

    }


}