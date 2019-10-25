package com.cs.springboot.spring.aop;

/**
 * @author: cs
 * @date: 2019/09/05 3:52 下午
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        //创建methodInvocation实现类
        MethodInvocation logTask = ()-> System.out.println("log task start");
        HelloServiceImpl helloService = new HelloServiceImpl();
        //创建一个Advice
        Advice beforeAdvice = new BeforeAdvice(helloService,logTask);
        //为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SpringAOP.getProxy(helloService,beforeAdvice);
        helloServiceImplProxy.sayHelloWorld();

    }
}
