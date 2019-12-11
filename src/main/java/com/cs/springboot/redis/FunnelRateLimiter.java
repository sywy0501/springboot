package com.cs.springboot.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ChuShi
 * @date: 2019/11/28 10:35 上午
 * @desc: 漏斗限流算法
 */
public class FunnelRateLimiter {

    static class Funnel{
        /**
         * 漏斗容量
         */
        int capacity;
        /**
         * 漏斗流水速率
         */
        float leakingRate;
        /**
         * 漏斗剩余空间
         */
        int leftQuota;
        /**
         * 上一次进水时间
         */
        long leakingTs;

        public Funnel(int capacity,float leakingRate){
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace(){
            long nowTs = System.currentTimeMillis();
            //距离上一次进水经过的时间
            long deltaTs = nowTs - leakingTs;
            //可以腾出的空间数
            int deltaQuota = (int) (deltaTs * leakingRate);
            //时间间隔太长，整数数字过大溢出
            if (deltaQuota<0){
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            //腾出空间太小，最小单位是1
            if (deltaQuota<1){
                return;
            }

            this.leftQuota+=deltaQuota;
            this.leakingTs = nowTs;
            //剩余空间不能高于容量
            if (this.leftQuota>this.capacity){
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota){
            makeSpace();
            if (this.leftQuota>=quota){
                this.leftQuota-=quota;
                return true;
            }
            return false;
        }
        //所有的漏斗
        private Map<String,Funnel> funnels = new HashMap<>();
        /**
         * @author: ChuShi
         * @date: 2019/11/28 2:57 下午
         * @param userId
         * @param actionKey
         * @param capacity 漏斗容量
         * @param leakingRate 漏嘴流水速率
         * @return: boolean
         * @desc: isActionAllowed
         */
        public boolean isActionAllowed(String userId,String actionKey,int capacity,float leakingRate){
            String key = String.format("%s:%s",userId,actionKey);
            Funnel funnel = funnels.get(key);
            if (funnel==null){
                funnel = new Funnel(capacity,leakingRate);
                funnels.put(key,funnel);
            }
            return funnel.watering(1);
        }
    }

}
