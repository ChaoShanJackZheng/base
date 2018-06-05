package cn.zjj.tips.base.controller.java8newspec.func;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @Author: Jack
 * @Date: 2018/6/4 19:04
 * @Description:
 */
public class FunctionDemo {

    public List<Integer> parse(List<String> list, Function<String, Integer> function) {
        List<Integer> result = new ArrayList<>();
        for (final String value : list) {
            // 应用数据转换
            if (NumberUtils.isDigits(value)) result.add(function.apply(value));
        }
        return result;
    }

}
