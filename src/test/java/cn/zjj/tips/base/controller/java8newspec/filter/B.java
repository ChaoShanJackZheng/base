package cn.zjj.tips.base.controller.java8newspec.filter;

/**
 * @Author: Jack
 * @Date: 2018/6/5 19:01
 * @Description:
 */
public interface B extends  A{

    /**
     * 默认方法定义
     */
    default void method() {
        System.out.println("B's default method!");
    }

}
