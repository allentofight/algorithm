package com.mahai._008_duplicate_interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 2/25/20
 * Time: 11:38 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {
    // 区间类，包括起始值和终止值
    private static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 递归求解
     * @param intervals
     * @return
     */
    private static Integer removeDuplicateIntervalsByRecursive(Interval[] intervals) {
        // 将区间按起始点由小到大进行排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        // 首次遍历从 -1,0 开始
        return removeSubDuplicate(-1, 0, intervals);
    }

    // 保存中间结果
    private static HashMap<String, Integer> map = new HashMap();
    private static Integer removeSubDuplicate(int pre, int cur, Interval[] intervals) {

        String key = pre + "," + cur;
        if (map.get(key) != null) {
            return map.get(key);
        }

        if (cur == intervals.length) {
            return 0;
        }

        int notRemove = Integer.MAX_VALUE;
        if (pre == -1 || intervals[pre].end <= intervals[cur].start) {
            notRemove = removeSubDuplicate(cur, cur+1, intervals);
        }

        int remove = removeSubDuplicate(pre, cur+1, intervals) + 1;
        int result = Math.min(notRemove, remove);
        map.put(key, result);
        // 取两者的较小值
        return result;
    }

    /**
     * 判断两区间是否重叠
     */
    private static boolean isOverlapping(Interval i, Interval j) {
        return j.end > i.start;
    }

    /**
     * 动态规划求解
     */
    private static Integer removeSubDuplicateWithDP(Interval[] intervals) {
        // 将区间起始点由小到大进行排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

        int[] dp = new int[intervals.length];
        Arrays.fill(dp, 0);
        dp[0]  = 1;    // 将 dp[0] 置为 1， 因为就算所有的区间都重叠，则连续不重叠区间到少也为 1

        int result = 1;
        for (int i = 1; i < intervals.length; i ++) {
            int max = 0;
            for (int j = 0; j < i; j ++) {
                if (!isOverlapping(intervals[i], intervals[j])) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
        }
        return intervals.length - dp[intervals.length - 1];
    }

    /**
     * 贪心算法求解
     */
    private static Integer removeSubDuplicateWithGreedy(Interval[] intervals) {
        // 将区间终点由小到大进行排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));

        int cur = 0;            // 设置第一个为当前区间

        int count = 1;      // 最大不重叠区间数,最小为1

        for (int i = 1; i < intervals.length; i++) {
            // 不重叠
            if (intervals[cur].end < intervals[i].start) {
                cur = i;
                count++;
            }
        }
        // 总区间个数减去最大不重叠区间数即最小被移除重叠区间
        return intervals.length - count;
    }


    public static  void main(String[] args) {
        // 初始化区间
        Interval[] intervals = {
                new Interval(1, 2),
                new Interval(3, 5),
                new Interval(4, 7),
                new Interval(8, 10),
                new Interval(9, 11)
        };
        int result = removeSubDuplicateWithGreedy(intervals);
        System.out.println("result = " + result);
    }

}