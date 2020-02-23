package com.mahai._006_dp_min_coins;

import org.omg.CORBA.INTERNAL;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 2/16/20
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    // 保存中间结果
    private static HashMap<Integer, Integer> map = new HashMap();

    /**
     * 带备忘录的递归求解
     * @param amount
     * @param coins
     * @return
     */
    private static int exchangeRecursive(int amount, int[] coins) {
        if (map.get(amount) != null) {
            return map.get(amount);
        }

        // 说明零钱已经凑完
        if (amount == 0) {
            return 0;
        }

        // 说明没有满足的条件
        if (amount < 0) {
            return -1;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subMin = exchangeRecursive(amount - coins[i], coins);
            if (subMin == -1) continue;
            result = Math.min(subMin + 1, result);
        }

        // 说明没有符合问题的解
        if (result == Integer.MAX_VALUE) {
            return -1;
        }

        map.put(amount, result);
        return result;
    }

    // 动态规划求解
    private static int exchangeDP(int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        // 初始化每个值为 amount+1，这样当最终求得的 dp[amount] 为 amount+1 时，说明问题无解
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }

        // 0 硬币本来就没有，所以设置成 0
        dp[0] = 0;

        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i- coins[j]], dp[i]) + 1;
                }
            }
        }

        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }

    public static  void main(String[] args) {
        int amount = 11;
        int[] coins = {1,2,5};
        int result = exchangeDP(amount, coins);
        System.out.println("result = " + result);
    }

}