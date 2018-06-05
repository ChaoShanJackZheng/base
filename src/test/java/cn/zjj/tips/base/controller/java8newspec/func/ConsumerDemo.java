package cn.zjj.tips.base.controller.java8newspec.func;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: Jack
 * @Date: 2018/6/4 18:58
 * @Description:
 */
public class ConsumerDemo {

    public <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (final T value : list) {
            // 应用行为
            consumer.accept(value);
        }
    }

}
