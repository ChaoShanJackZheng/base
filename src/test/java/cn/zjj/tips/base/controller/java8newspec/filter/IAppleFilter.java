package cn.zjj.tips.base.controller.java8newspec.filter;

import cn.zjj.tips.base.controller.java8newspec.bean.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jack
 * @Date: 2018/6/5 09:34
 * @Description:
 */
public interface IAppleFilter {
    boolean accept(Apple apple);

}
