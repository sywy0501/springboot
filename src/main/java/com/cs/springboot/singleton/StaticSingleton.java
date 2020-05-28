package com.cs.springboot.singleton;

/**
 * @author: ChuShi
 * @date: 2020/01/08 3:55 下午
 * @desc:
 */
public class StaticSingleton {
    private static class SingletonHandler{
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }

    private StaticSingleton(){}

    public static StaticSingleton getInstance(){
        return SingletonHandler.INSTANCE;
    }
}
