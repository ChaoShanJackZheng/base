package cn.zjj.tips.base.controller.java8newspec;

import cn.zjj.tips.base.controller.java8newspec.bean.User;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * @Author: Jack
 * @Date: 2018/6/5 10:20
 * @Description:
 * Optional不是对null关键字的一种替代，而是对于null判定提供了一种更加优雅的实现
 *
 * 很多场景下不是开发人员没有具体的处理策略，而是根本没有意识到空指针异常的存在。
 * 当异常真的发生的时候，处理策略也很简单，在存在异常的地方添加一个 if 语句判定即可，
 * 但是这样的应对策略会让我们的程序出现越来越多的 null 判定。
 * 一个良好的程序设计应该让代码中尽量少出现 null 关键字，
 * 而 8th 所提供的 Optional 类则在减少 NullPointException 的同时，也提升了代码的美观度。
 * 但首先我们需要明确的是它并 不是对 null 关键字的替代策略，而是对于 null 判定提供了一种更加优雅的实现，
 * 从而尽可能地避免 NullPointException
 */
public class OptionalControllerTest {

    private int demoM01(String str){
        //旧写法
      /*  if(null == str) { // 空指针判定
            return 0;
        }
        return str.length();*/
        return Optional.ofNullable(str).map(String::length).orElse(0);
    }

    /**
     * 调用 str.length() 方法直观感受一下
     */
   // @Test
    public void test01(){
        System.out.println("test01");
        System.out.println(demoM01(null));
    }

    /**
     *
     * 一. 基本使用
     * 1.1 Optional 对象的创建
     *
     */
    //@Test
    public void test02(){
        System.out.println("test02");
        String str = null;
        //创建空对象-调用 empty() 方法创建了一个空的 Optional<String> 对象型
        Optional<String> optStr = Optional.empty();

        //创建对象：不允许为空-Optional 提供了方法 of() 用于创建非空对象，
        // 该方法要求传入的参数不能为空，
        // 否则抛 NullPointException
        //Optional<String> optStr1 = Optional.of(str);// 当str为null的时候，将抛出NullPointException

        //创建对象：允许为空
        //如果不能确定传入的参数是否存在 null 值的可能性，
        // 则可以用 Optional 的 ofNullable() 方法创建对象，如果入参为 null 则创建一个空对象
        Optional<String> optStr3 = Optional.ofNullable(str);  // 如果str是null，则创建一个空对象

    }

    /**
     * 1.2 流式数据处理
     * 流式数据处理也是 8th 给我们带来的一个重量级新特性，让我们对集合的操作变得更加简洁和高效,
     * Optional 类也提供了两个基本的流失处理：映射和过滤
     */

    /**
     * 1.2.1 映射：map 与 flatMap
     *
     */
    //@Test
    public void test03(){
        System.out.println("test03");
        User user= new User("小明", 20);
        Optional<User> optUser = Optional.ofNullable(user);

        //映射是将输入转换成另外一种形式的输出的操作，比如前面例子中我们输入字符串，而输出的是字符串的长度，
        // 这就是一种映射，我们利用方法 map() 进行实现。假设我们希望获得一个人的姓名
        String name = Optional.ofNullable(user).map(User::getName).orElse("no name");
        System.out.println(name);

        //这样当入参 user 不为空的时候则返回其 name，否则返回 no name。如我我们希望通过上面方式得到 phone
        // 或 email，利用上面的方式则行不通了，因为 map 之后返回的是 Optional，我们把这种称为 Optional 嵌套，
        // 我们必须再 map 一次才能拿到我们想要的结果：
        long phone = optUser.map(User::getPhone).map(Optional::get).orElse(-1L);
        System.out.println(phone);

        //其实这个时候更好的方式是利用 flatMap，一步拿到我们想要的结果,如果获取不到值则会抛出NullPointException：
        long phone2 = optUser.flatMap(User::getPhone).orElse(-1L);
        //System.out.println(phone2);

    }

    /**
     * 1.2.2 过滤：fliter
     * filiter，顾名思义是过滤的操作，我们可以将过滤操作做为参数传递给该方法以实现过滤目的，
     * 假如我们希望筛选 18 周岁以上的成年人，则可以实现如下：
     */
    //@Test
    public void test04(){
        System.out.println("test04");

        User user=null;
        Optional<User> optUser = Optional.ofNullable(user);
        optUser.filter(u -> u.getAge() >= 18).ifPresent(u -> System.out.println("Adult:" + u));
    }

    /**
     * 1.3 默认行为
     * 默认行为是当 Optional 在不满足条件时所执行的操作，比如在上面的例子中我们使用的 orElse() 就是一个默认操作，
     * 用于在 Optional 对象为空时执行特定操作，当然也有一些默认操作是当满足条件的对象存在时执行的操作。
     *
     * 1.3.1 get()
     * get 方法用于获取变量的值，但是当变量不存在时则会抛出 NoSuchElementException，
     * 所以如果不确定变量是否存在则不建议使用
     * 1.3.2 orElse(T other)
     * 当 Optional 的变量不满足给定条件时，则执行 orElse，比如前面当 str 为 null 时返回 0。
     * 1.3.3 orElseGet(Supplier<? extends X> expectionSupplier)
     */
   // @Test
    public void test05(){
        System.out.println("test04");
        //User user= new User(new Long("1"), "小明", 20, Optional.of(new Long("13717111111")),Optional.of("xiaoming@qq.com"));
        User user= new User("小明", 20);
        Optional<User> optUser = Optional.ofNullable(user);
        long phone = optUser.map(User::getPhone).map(Optional::get).orElseGet(() -> {
            // do something here
            return -1L;
        });
    }

    /**
     *
     * 二. 注意事项
     * Optional 是一个 final 类且未实现任何接口，所以当我们在利用该类包装定义类的属性的时候，
     * 如果我们定义的类有序列化的需求，那么因为 Optional 没有实现 Serializable 接口，这个时候执行序列化操作就会有问题：
     * public class User implements Serializable {
     *      private long id;
     *      private String name;
     *      private int age;
     *      private Optional<Long> phone;  // 不能序列化
     *      private Optional<String> email;  // 不能序列化
     *  }
     *  不过我们可以采用如下替换策略 Optinal：
     *
     *  private long phone;
     *
     *  public Optional<Long> getPhone() {
     *      return Optional.ofNullable(this.phone);
     *      }
     *
     *  看来 Optional 类在设计的时候就没有考虑将它作为类的字段使用
     *  最后提醒一点，Optional 好用但不能滥用，在设计一个接口方法时是否采取 Optional 类型返回需要斟酌，一味的
     *  使用会让代码变得比较啰嗦，反而破坏了代码的简洁性。
     *
     */

}
