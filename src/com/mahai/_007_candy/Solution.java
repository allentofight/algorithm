package com.mahai._007_candy;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 2/23/20
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {
    /**
     *  获取能分配给小孩的符合条件的最多糖果数
     */
    private static int dispatchCandy(int[] gList, int[] sList) {
        Arrays.sort(gList);     // 小孩对糖果的需求从小到大排列
        Arrays.sort(sList);     // 糖果大小从小到大排列

        int maximumCandyNum = 0;
        for (int i = 0; i < gList.length; i++) {
            for (int j = 0; j < sList.length; j++) {
                // 选择最接近小孩需求的糖果，以便让更大的糖果满足需求更大的小孩
                if (gList[i] <= sList[j]) {
                    maximumCandyNum++;
                    // 糖果被选中，将其置为-1，代表无效了
                    sList[j] = -1;
                    // 糖果已选中，跳出
                    break;
                }
            }
        }
        return maximumCandyNum;
    }

    public static  void main(String[] args) {
        // 小孩对糖果的需求
        int[] gList = {1,2,4,6};
        // 糖果实际大小
        int[] sList = {1,2,7,3};
        int result = dispatchCandy(gList, sList);
        System.out.println("result = " + result);
    }
}