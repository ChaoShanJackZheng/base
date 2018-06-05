package cn.zjj.tips.base.controller.java8newspec.func;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: Jack
 * @Date: 2018/6/4 18:53
 * @Description:
 */
public class PredicateDemo {
    public  <T> List<T> filter(List<T> numbers, Predicate<T> predicate) {
        Iterator<T> itr = numbers.iterator();
        while (itr.hasNext()) {
            if (!predicate.test(itr.next())) {
                itr.remove();
            }
            itr.next();
        }
        return numbers;
    }
}
