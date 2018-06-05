package cn.zjj.tips.base.controller.java8newspec;

import cn.zjj.tips.base.controller.java8newspec.filter.A;
import cn.zjj.tips.base.controller.java8newspec.filter.B;
import org.testng.annotations.Test;

/**
 * @Author: Jack
 * @Date: 2018/6/5 18:34
 * @Description:
 *  从 java 8th 开始，接口不只是一个只能声明方法的地方，我们还可以在声明方法时，给方法一个默认的实现，
 *  我们称之为默认接口方法，这样所有实现该接口的子类都可以持有该方法的默认实现
 */
public class DefaultInterMethodController implements A,B {


    /**
     * 一. 默认接口方法的定义
     * 默认接口方法的定义很简单，只要在接口的方法定义前添加一个 default 关键字即可，如下：
     * public interface A {
     *     default void method() {
     *         System.out.println("This is a default method!");
     *     }
     * }
     *
     * 当我们定义了一个默认接口方法之后，所有实现该接口的子类都间接持有了该方法。或许你会和我一样觉得接口和抽象类
     * 越来越像了，确实，不过它们之间还是有如下差别：
     *
     * 1、一个类只能继承一个类，但是可以实现多个接口
     * 2、抽象类可以定义变量，而接口却不能
     *
     * 除了上面提及到的区别，接口方法还具有如下优点：
     * 1、对于一些不是每个子类都需要的方法，我们给它一个默认实现，从而避免子类中的无意义实现（一般我们都会直接
     * throw new UnsupportedException()）
     * 2、默认方法为 java 的多重继承机制提供了新途径（虽然我们只能继承一个类，但是我们可以实现多个接口啊，现在接
     * 口也可以定义默认方法了）
     *
     */

    /**
     * 二. 冲突及其解决方法
     * 一个类可以实现多个接口，当一个类实现了多个接口，而这些接口中存在两个或两个以上方法签名相同的默认方法
     * 时就会产生冲突，8th 定义如下三条原则以解决冲突：
     * 1、类或父类中显式声明的方法，其优先级高于所有的默认方法
     * 2、如果 1 规则失效，则选择与当前类距离最近的具有具体实现的默认方法
     * 3、如果 2 规则也失效，则需要显式指定接口
     */
    @Test
    public void test01(){
        //此处因为接口 B 相对于 A 距离 C 更近，同时 B 的 method 是一个具体的默认实现，依据规则 2，
        // 所以此处实际上调用的是接口 B 的默认方法。
        new DefaultInterMethodController().method();
        // 输出：B's default method!
    }


    /**
     * 例2
     * public class D implements A {
     * }
     *
     * public class C extends D implements A, B {
     *     public static void main(String[] args) {
     *         new C().method();
     *     }
     * }
     * // 输出：B's default method!
     * 在原有接口 A 和 B 的基础上添加了一个实现接口 A 的类 D，然后类 C 继承于 D，并实现 A 和 B 接口，此处
     * 虽然 C 离 D 更近，但因为 D 的具体实现在 A 中，所以 B 中的默认方法还是距离最近的默认实现，依据规则 2，
     * 此处实际上调用的是 B 的默认方法
     */


    /**
     * 例3
     * // A接口不变
     *
     * public interface B {
     *  default void method() {
     *  System.out.println("B's default method!");
     * }
     *
     * public class C implements A, B {
     *   @Override
     *   public void method() {
     *       // 必须显式指定
     *       B.super.method();
     * }
     * public static void main(String[] args) {
     *     new C().method();
     * }
     *
     * 例 3 中接口 B 不再继承自接口 A，所以此时 C 中调用默认方法 method() 距离接口 A 和 B 的具体实现距离相同，
     * 编译器无法确定，所以报错，此时需要显式指定：B.super.method()。
     */
}
