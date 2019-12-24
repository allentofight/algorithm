package com.mahai._001_permutation;

import java.util.Arrays;

public class Solution {

    /**
     * 字典序法
     * @param arr
     * @return boolean 如果还有下一个全排列数，则返回 true, 否则返回 false
     */
    public static boolean next_permutation(int[] arr) {
        int beforeIndex = 0; //记录从右到左寻找第一个左邻小于右邻的数对应的索引
        int currentIndex;
        boolean isAllReverse = true;    // 是否存在从右到左第一个左邻小于右邻的数对应的索引
        // 1. 从右到左（从个位数往高位数）寻找第一个左邻小于右邻的数
        for(currentIndex = arr.length - 1; currentIndex > 0; --currentIndex){
            beforeIndex = currentIndex - 1;
            if(arr[beforeIndex] < arr[currentIndex]){
                isAllReverse = false;
                break;
            }
        }
        //如果不存在，说明这个数已经是字典排序法里的最大值，此时已经找到所有的全排列了,直接打印即可
        if(isAllReverse){
            return  false;
        } else {
            // 2. 再从右往左找第一个比第一步找出的数更大的数
            int firstLargeIndex = 0;
            for(firstLargeIndex = arr.length - 1; firstLargeIndex > beforeIndex; --firstLargeIndex) {
                if (arr[firstLargeIndex] > arr[beforeIndex]) {
                    break;
                }
            }
            // 3. 交换 上述 1, 2 两个步骤中得出的两个数
            swap(arr, beforeIndex, firstLargeIndex);

            // 4. 交换 上述 1, 2 两个步骤中得出的两个数
            Arrays.sort(arr, beforeIndex + 1, arr.length);
            return true;
        }
    }


    /**
     * 递归解法
     * @param arr
     * @param k
     */
    public static void permutation(int[] arr, int k) {
        // 当 k 指向最后一个元素时,递归终止，打印此时的排列排列
        if (k == arr.length - 1) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = k; i < arr.length; i++) {
                // 将 k 与之后的元素 i 依次交换,然后可以认为选中了第 k 位
                swap(arr, k, i);
                // 第 k 位选择完成后，求剩余元素的全排列
                permutation(arr, k+1);
                // 这一步很关键：将 k 与 i 换回来，保证是初始的顺序
                swap(arr, k, i);
            }
        }
    }

    public static void swap (int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        // 递归解法
        permutation(arr, 0);
        System.out.println("=================");
        // 字典序法
        while (next_permutation(arr)) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
