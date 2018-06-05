package cn.zjj.tips.base.controller.java8newspec;


import cn.zjj.tips.base.controller.java8newspec.bean.Apple;
import cn.zjj.tips.base.controller.java8newspec.filter.IAppleFilter;
import cn.zjj.tips.base.controller.java8newspec.func.ConsumerDemo;
import cn.zjj.tips.base.controller.java8newspec.func.FunctionDemo;
import cn.zjj.tips.base.controller.java8newspec.func.PredicateDemo;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;


/**
 * @Author: Jack
 * @Date: 2018/6/4 13:21
 * @Description:
 * java8的流式处理极大了简化我们对于集合、数组等结构的操作，让我们可以以函数式的思想去操作
 * 将行为进行 参数化 处理，让函数仅保留模板代码，而把筛选条件抽离出来当做参数传递进来，在 java 8th 之前，
 * 我们通过定义一个过滤器接口来实现
 *
 * 一. 行为参数化
 * 行为参数化简单的说就是将方法的逻辑以参数的形式传递到方法中，方法主体仅包含模板类通用代码，
 * 而一些会随着业务场景而变化的逻辑则以参数的形式传递到方法之中，采用行为参数化可以让程序更加的通用，
 * 以应对频繁变更的需求。
 *
 * 二. Lambda 表达式
 * 2.1 Lambda 表达式的定义与形式
 * lambda 表达式定义为一种 简洁、可传递的匿名函数，
 * lambda 表达式本质上是一个函数，虽然它不属于某个特定的类，
 * 但具备参数列表、函数主体、返回类型，甚至能够抛出异常；其次它是匿名的，
 * lambda 表达式没有具体的函数名称；lambda 表达式可以像参数一样进行传递，
 * 从而简化代码的编写，其格式定义如下：
 * 参数列表 -> 表达式
 * 参数列表 -> {表达式集合}
 */
public class LambdaControllerTest {

    List<Apple> apples = new ArrayList<Apple>(){{
        add(new Apple(new Long("1"), Color.RED, new Float("90"),"广西"));
        add(new Apple(new Long("2"), Color.RED, new Float("190"),"广西"));
    }};

    // 应用过滤器的筛选方法
    public static List<Apple> filterApplesByAppleFilter(List<Apple> apples, IAppleFilter filter) {
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
        List<Apple> filterApples = filterApplesByAppleFilter(apples, new IAppleFilter() {
            @Override
            public boolean accept(Apple apple) {
                // 筛选重量大于100g的红苹果
                return Color.RED.equals(apple.getColor()) && apple.getWeight() > 100;
            }
        });

        System.out.println("test01:");
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

        System.out.println("test02:");
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
     * public interface IAppleFilter {
     *     boolean accept(Apple apple);
     * }
     *IAppleFilter 仅包含一个抽象方法 accept(Apple apple)，依照定义可以将其视为一个函数式接口。在定义时我们为该
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
     * BinaryOperator<T>	(T, T) -> T	    IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator
     * BiPredicate<L, R>    (L, R) -> boolean
     * BiConsumer<T, U>	    (T, U) -> void
     * BiFunction<T, U, R>	(T, U) -> R
     *
     * 其中最典型的三个接口是 Predicate<T>、Consumer<T>，以及 Function<T, R>，其余接口几乎都是对这三个接口的定
     * 制化，下面就这三个接口举例说明其用处，针对接口中提供的逻辑操作默认方法，留到后面介绍接口的 default 方
     * 法时再进行说明。
     */

    /**
     * 利用 Predicate 对 List 集合的元素进行过滤
     */
    private <T> List<T> filter(List<T> numbers, Predicate<T> predicate) {
        Iterator<T> itr = numbers.iterator();
        while (itr.hasNext()) {
            if (!predicate.test(itr.next())) {
                itr.remove();
            }
            itr.next();
        }
        return numbers;
    }

    /**
     * Predicate 的功能类似于上面的 IAppleFilter，利用我们在外部设定的条件对于传入的参数进行校验并返回验证通过,
     * 利用 Predicate 对 List 集合的元素进行过滤
     */
    //@Test
    public void test04(){
        PredicateDemo pd = new PredicateDemo();
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        list = pd.filter(list, (value) -> value % 2 == 0);
        System.out.println("test04:");
        System.out.println(list);
        // 输出：[2, 4, 6]
    }

    /**
     * Consumer 提供了一个 accept 抽象函数，该函数接收参数并依据传递的行为应用传递的参数值，
     * 下面利用 Consumer 遍历字符串集合并转换成小写进行打印
     */
    //@Test
    public void test05(){
        ConsumerDemo cd = new ConsumerDemo();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("I", " ", "Love", " ", "Java", " ", "8th"));
        System.out.println("test05:");
        cd.forEach(list, (value) -> System.out.print(value.toLowerCase()));
        // 输出：i love java 8th
    }

    /**
     * Funcation 执行转换操作，输入类型 T 的数据，返回 R 类型的结果，
     * 下面利用 Function 对字符串集合转换成整型集合，并忽略掉不是数值型的字符：
     *
     */
    //@Test
    public void test06(){
        FunctionDemo fd = new FunctionDemo();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("a", "1", "2", "3", "4", "5", "6"));
        List<Integer> result = fd.parse(list, (value) -> Integer.valueOf(value));
        System.out.println("test06:");
        System.out.println(result);
        // 输出：[1, 2, 3, 4, 5, 6]
    }


    /**
     * 类型推断-筛选苹果
     */
    //@Test
    public void test07(){
        //旧代码
       // List<Apple> filterApples = filterApplesByAppleFilter(apples,
         //       (Apple apple) -> Color.RED.equals(apple.getColor()) && apple.getWeight() >= 100);

        // 某些情况下我们甚至可以省略参数类型，编译器会根据上下文正确判断
        List<Apple> filterApples = filterApplesByAppleFilter(apples,
                apple -> Color.RED.equals(apple.getColor()) && apple.getWeight() >= 100);
        System.out.println("test07:");
        System.out.println(filterApples);
    }

    /**
     * 局部变量
     * 在 lambda 中使用了局部变量 weight，不过在 lambda 中使用局部变量还是有很多限制，
     * 学习初期 IDE 可能经常会提示我们 Variable used in lambda expression should be final or effectively final 的错误，
     * 即要求在 lambda 表达式中使用的变量必须 显式声明为 final 或事实上的 final 类型。
     * 为什么要限制我们直接使用外部的局部变量呢？主要原因在于内存模型，我们都知道实例变量在堆上分配的，
     * 而局部变量在栈上进行分配，lambda 表达式运行在一个独立的线程中，了解 JVM 的同学应该都知道栈内存是线程私有的，
     * 所以局部变量也属于线程私有，如果肆意的允许 lambda 表达式引用局部变量，可能会存在局部变量以及所属的线程被回收，
     * 而 lambda 表达式所在的线程却无从知晓，这个时候去访问就会出现错误，
     * 之所以允许引用事实上的 final（没有被声明为 final，但是实际中不存在更改变量值的逻辑），
     * 是因为对于该变量操作的是变量副本，因为变量值不会被更改，所以这份副本始终有效
     */
    //@Test
    public void test08(){
        int weight = 100;
        List<Apple> filterApples = filterApplesByAppleFilter(apples,
                apple -> Color.RED.equals(apple.getColor()) && apple.getWeight() >= weight);
        System.out.println("test08:");
        System.out.println(filterApples);
    }

    /**
     *  三. 方法引用
     *  方法引用可以更近一步的简化代码，有时候这种简化让代码看上去更加直观
     *  // 采用lambda表达式
     *  apples.sort((Apple a, Apple b) -> Float.compare(a.getWeight(), b.getWeight()));
     *  // 采用方法引用
     *  apples.sort(Comparator.comparing(Apple::getWeight));
     *  方法引用通过 :: 将方法隶属和方法自身连接起来，主要分为三类：
     *
     *  1.静态方法
     *  (args) -> ClassName.staticMethod(args)
     *  转换成：
     *  ClassName::staticMethod
     *
     *  2.参数的实例方法
     *  (args) -> args.instanceMethod()
     *  转换成：
     *  lassName::instanceMethod  // ClassName是args的类型
     *
     *  3.外部的实例方法
     *  (args) -> ext.instanceMethod(args)
     *  转换成：
     *  ext::instanceMethod(args)
     */



}
