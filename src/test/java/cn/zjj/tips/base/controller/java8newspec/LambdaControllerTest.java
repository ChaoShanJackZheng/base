package cn.zjj.tips.base.controller.java8newspec;


import cn.zjj.tips.base.controller.java8newspec.bean.Apple;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jack
 * @Date: 2018/6/4 13:21
 * @Description:
 * java8的流式处理极大了简化我们对于集合、数组等结构的操作，让我们可以以函数式的思想去操作
 * 将行为进行 参数化 处理，让函数仅保留模板代码，而把筛选条件抽离出来当做参数传递进来，在 java 8th 之前，
 * 我们通过定义一个过滤器接口来实现，lambda 表达式定义为一种 简洁、可传递的匿名函数，
 * lambda 表达式本质上是一个函数，虽然它不属于某个特定的类，
 * 但具备参数列表、函数主体、返回类型，甚至能够抛出异常；其次它是匿名的，
 * lambda 表达式没有具体的函数名称；lambda 表达式可以像参数一样进行传递，
 * 从而简化代码的编写，其格式定义如下：
 * 参数列表 -> 表达式
 * 参数列表 -> {表达式集合}
 */
public class LambdaControllerTest {

    List<Apple> apples = new ArrayList<Apple>(){{
        add(new Apple(new Long("1"), Color.RED, new Float("110"),"广西"));
        add(new Apple(new Long("2"), Color.RED, new Float("190"),"广西"));
    }};

    // 过滤器
    public interface AppleFilter {
        boolean accept(Apple apple);
    }

    // 应用过滤器的筛选方法
    public static List<Apple> filterApplesByAppleFilter(List<Apple> apples, AppleFilter filter) {
        List<Apple> filterApples = new ArrayList<>();
        for (final Apple apple : apples) {
            if (filter.accept(apple)) {
                filterApples.add(apple);
            }
        }
        return filterApples;
    }

    /**
     * 行为参数化方式采用匿名类实现
     * @return
     */
    //@Test
    public void test01(){

        // 筛选苹果
        List<Apple> filterApples = filterApplesByAppleFilter(apples, new AppleFilter() {
            @Override
            public boolean accept(Apple apple) {
                // 筛选重量大于100g的红苹果
                return Color.RED.equals(apple.getColor()) && apple.getWeight() > 100;
            }
        });

        System.out.println(filterApples);

    }

    /**
     * 通过 lambda 表达式进行简化
     * @return
     */
    //@Test
    public void test02(){

        // 筛选苹果
        List<Apple> filterApples = filterApplesByAppleFilter(apples,
                (Apple apple) -> Color.RED.equals(apple.getColor()) && apple.getWeight() >= 100);

        System.out.println(filterApples);

    }

    /**
     * 通过 lambda 表达式进行简化
     * @return
     * 需要注意 lambda 表达式隐含了 return 关键字，所以在单个的表达式中，我们无需显式的写 return 关键字，但是
     * 当表达式是一个语句集合的时候则需要显式添加 return 关键字，并用花括号 {} 将多个表达式包围起来
     */
    //@Test
    public void test03(){

        // 1. 返回给定字符串的长度（隐含return语句）
        //(String s) -> s.length()

        // 2. 始终返回42的无参方法（隐含return语句）
          //  () -> 42

        // 3. 包含多行表达式，需用花括号括起来，并显示添加return
            /*(int x, int y) -> {
                int z = x * y;
                return x + z;
            }*/

    }

    /**
     * 基于函数式接口使用 lambda 表达式
     *lambda 表达式的使用需要借助于 函数式接口，也就是说只有函数式接口出现地方，我们才可以将其用 lambda 表
     *达式进行简化。那么什么是函数接口？函数接口的定义如下：
     *
     * 函数式接口定义为仅含有一个抽象方法的接口
     *
     * 按照这个定义，我们可以确定一个接口如果声明了两个或两个以上的方法就不叫函数式接口，需要注意一点的是
     * java 8th 为接口的定义引入了默认的方法，我们可以用 default 关键字在接口中定义具备方法体的方法，
     * 如果一个接口存在多个默认方法，但是仍然仅含有一个抽象方法，那么这个接口也符合函数式接口的定义。
     *
     */

    /**
     * 自定义函数式接口
     *
     * @FunctionalInterface
     * public interface AppleFilter {
     *     boolean accept(Apple apple);
     * }
     *AppleFilter 仅包含一个抽象方法 accept(Apple apple)，依照定义可以将其视为一个函数式接口。在定义时我们为该
     * 接口添加了 @FunctionalInterface 注解，用于标记该接口是一个函数式接口，不过该注解是可选的，当添加了该注
     * 解之后，编译器会限制了该接口只允许有一个抽象方法，否则报错，所以推荐为函数式接口添加该注解。
     */


    /**
     *  jdk 自带的函数式接口
     *
     * jdk 为 lambda 表达式已经内置了丰富的函数式接口
     * 函数式接口	        函数描述符	    原始类型特化
     * Predicate<T>	        T -> boolean	IntPredicate, LongPredicate, DoublePredicate
     * Consumer<T>	        T -> void	    IntConsumer, LongConsumer, DoubleConsumer
     * Function<T, R>	    T -> R	        IntFunction<R>, IntToDoubleFunction, IntToLongFunction<R>, LongFuncation
     * Supplier<T>	        () -> T	        BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier
     * UnaryOperator<T>	    T -> T	        IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator
     * BinaryOperator<T>	T, T) -> T	    IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator
     * BiPredicate<L, R>    (L, R) -> boolean
     * BiConsumer<T, U>	    (T, U) -> void
     * BiFunction<T, U, R>	(T, U) -> R
     *
     * 其中最典型的三个接口是 Predicate<T>、Consumer<T>，以及 Function<T, R>，其余接口几乎都是对这三个接口的定
     * 制化，下面就这三个接口举例说明其用处，针对接口中提供的逻辑操作默认方法，留到后面介绍接口的 default 方
     * 法时再进行说明。
     */
}
