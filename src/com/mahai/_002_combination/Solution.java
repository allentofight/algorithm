package com.mahai._002_permutation;

import java.util.Arrays;

public class Solution {

    /**
     * 获取 select 中元素为 1  的个数
     * @param select
     * @return
     */
    public static int selectedNum(int[] select) {
        int selectNum = 0;      // 已被选中的元素个数
        for (int i = 0;i < select.length; i++) {
            if (select[i] == 1) {
                selectNum++;
            }
        }
        return selectNum;
    }

    public static final int COMBINATION_CNT = 5;        // 组合中需要被选中的个数
    public static void combination(int[] arr, int k, int[] select) {
        int selectNum = selectedNum(select);
        if (selectNum == COMBINATION_CNT) {
            int j;
            for (j = 0; j < select.length; j++) {
                if (select[j] == 1) {
                    System.out.print(arr[j]);
                }
            }
            System.out.print("\n");
        } else {

            if (k >= arr.length) {
                return;
            }

            // 第 k 位被选中
            select[k] = 1;
            // 则从第 k+1 位选择 COMBINATION_CNT - selectNum 个元素
            combination(arr, k+1, select);

            // 第 k 位未被选中
            select[k] = 0;
            // 则从第 k+1 位选择 COMBINATION_CNT - selectNum 个元素
            combination(arr, k+1, select);
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int[] select = {0,0,0,0,0,0,0,0,0};
        // 一开始从 0 开始选 组合数
        combination(arr, 0, select);
    }
}
