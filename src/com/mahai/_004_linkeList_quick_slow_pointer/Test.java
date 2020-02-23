package com.mahai._004_linkeList_quick_slow_pointer;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 2/2/20
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */

import com.sun.source.tree.BreakTree;

import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * VM Args:-Xss160k
 */
public class Test {


    public static int knapsack(int[] weight, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w-weight[i]; ++j) {//把第i个物品放入背包
                if (states[j]==true) states[j+weight[i]] = true;

            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


    public static  void main(String[] args)  throws Throwable {
        int[] items = {2,2,4,6,3};
        int n = 5;
        int w = 9;
        int sum = knapsack(items, 5, 9);
        System.out.println("sum = " + sum);
    }
}
