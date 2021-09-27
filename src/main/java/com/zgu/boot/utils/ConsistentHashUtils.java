package com.zgu.boot.utils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性 Hash 算法
 */
public class ConsistentHashUtils {

    /**
     * 待添加入Hash环的服务器列表
     */
    public static String[] ips = {
            "192.168.0.1", "192.168.0.110", "192.168.0.145", "192.168.0.198", "192.168.0.201"
    };

    /**
     * key 表示服务器的 hash 值，value 表示 ip
     */
    private static final SortedMap<Integer, String> sortedMap = new TreeMap<>();

    // 程序初始化，将所有的服务器放入sortedMap中
    static {
        for (String ip : ips) {
            int hash = getHash(ip);
            System.out.println("[" + ip + "]加入map中, 其Hash值为" + hash);
            sortedMap.put(hash, ip);
        }
    }

    /**
     * 得到路由到的节点
     * @param key
     * @return
     */
    private static String getServer(String key) {
        //得到该key的hash值
        int hash = getHash(key);
        //得到大于该Hash值的所有Map，这里用有排序功能的sortedMap，有很多api很方便
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = sortedMap.firstKey();
            //返回对应的服务器
            return sortedMap.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            return subMap.get(i);
        }
    }

    /**
     * 使用FNV1_32_HASH算法计算Hash值(网上找的标准算法)
     * @param str
     * @return
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    public static void main(String[] args) {
        String[] keys = {"1", "2", "3", "医生", "护士", "患者"};
        for (String key : keys) {
            System.out.println("[" + key + "]的hash值为" + getHash(key)
                    + ", 被路由到结点[" + getServer(key) + "]");
        }

    }

}
