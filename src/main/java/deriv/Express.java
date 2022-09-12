package deriv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wones
 * @desc:生成表达式
 * @date:2022/9/12 15:06
 */
public class Express {
    public static List<Object> constant(int c){
        List<Object> c1 = new ArrayList<>();
        c1.add("c");
        List<Object> c2 = new ArrayList<>();
        c2.add(c);
        c1.add(c2);
        return c1;
    }
    public static List<Object> variable(String var){
        List<Object> c1 = new ArrayList<>();
        c1.add("v");
        List<Object> c2 = new ArrayList<>();
        c2.add(var);
        c1.add(c2);
        return c1;
    }
}
