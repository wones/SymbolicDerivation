package deriv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wones
 * @desc:测试
 * @date:2022/9/12 12:29
 */
public class Test {
    public static void main(String[] args) {
        Deriv deriv = new Deriv();
        List<Object> exp = new ArrayList<>();
        exp.add("+");
        exp.add(Express.constant(5));
        List<Object> e1 = new ArrayList<>();
        e1.add("*");
        e1.add(Express.constant(3));
        e1.add(Express.variable("x"));
        List<Object> e2 = new ArrayList<>();
        e2.add("*");
        e2.add(e1);
        e2.add(Express.variable("x"));

        exp.add(e2);

        System.out.println(exp);
        System.out.println(deriv.deriv(exp,"y"));
    }
}
