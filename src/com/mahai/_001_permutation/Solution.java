package com.mahai._001_permutation;

import java.util.Arrays;

public class Solution {

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
        permutation(arr, 0);
    }
}
