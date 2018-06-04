package cn.zjj.tips.base.controller.java8newspec;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Jack
 * @Date: 2018/6/4 13:15
 * @Description:
 * 流式数据处理
 * java8的流式处理极大了简化我们对于集合、数组等结构的操作，让我们可以以函数式的思想去操作
 *
 *
 */
public class StreamControllerTest {

    List<Integer> nums = new ArrayList<Integer>(){
        { add(1);add(211);add(32);add(343);add(45);add(545);add(233); }
    };

    /**
     * 包含整数的集合中筛选出所有的偶数,并将其封装成为一个新的List
     * stream()操作将集合转换成一个流，filter()执行我们自定义的筛选处理，这里是通过lambda表达式筛选出所有偶数，
     * 最后我们通过collect()对结果进行封装处理，并通过Collectors.toList()指定其封装成为一个List集合返回。
     */
    @Test
    public void test01(){

        //旧写法
       /* List<Integer> evens = new ArrayList<>();
        for (final Integer num : nums) {
            if (num % 2 == 0) {
                evens.add(num);
            }
        }*/

        List<Integer> evens = nums.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println(evens);
    }


}
