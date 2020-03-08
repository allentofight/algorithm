package com.mahai._009_knapsack_recursive;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 3/8/20
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    // 最终的解：即背包可放入的最大重量
    private static int maxw = Integer.MIN_VALUE;

    // 每个物品的重量
    private static int[] weights = {2,2,4,6,3};

    // 背包能承载的最大重量
    private static final int knapsackWeigth = 9;

    // 备忘录，缓存子问题
    private static HashMap mem = new HashMap<String, Integer>();

    private static void knapsack(int i, int w) {
        // 物品有多少个
        int weightSize = weights.length;

        // 物品选完或者背包里选的物品总质量超过了背包可承载的总重量，递归结束
        if (i > weightSize-1 || w >= knapsackWeigth) {
            if (w <= knapsackWeigth) {
                maxw = Math.max(maxw, w);
            }
            return;
        }

        String key = i + "," + w;
        // 有 value，说明子问题之前已经解过了，无需再计算!
        if (mem.get(key) != null) {
            return;
        }
        mem.put(key, 1);

        // 第 i 个物品不选
        knapsack(i + 1, w);

        if (w + weights[i] <= knapsackWeigth) {
            // 选了第 i 个物品
            knapsack(i + 1, w + weights[i]);
        }
    }

    public static  void main(String[] args) {
        knapsack(0, 0);
        System.out.println("maxw = " + maxw);
    }

}