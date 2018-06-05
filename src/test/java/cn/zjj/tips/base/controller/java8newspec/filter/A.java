package cn.zjj.tips.base.controller.java8newspec.filter;

/**
 * @Author: Jack
 * @Date: 2018/6/5 18:37
 * @Description:
 */
public interface A {
    /**
     * 默认方法定义
     */
    default void method() {
        System.out.println("This is a default method!");
    }

}
