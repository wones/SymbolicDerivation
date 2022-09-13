package deriv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wones
 * @desc:实现求导
 * @date:2022/9/12 14:14
 */
public class Deriv extends ConstraintImp {

    public List<Object> deriv(List<Object> exp, String var) {
        List<Object> res = new ArrayList<>();
        if (isNumber(exp)) {
//           List<Object> tmp = new ArrayList<>();
//           tmp.add("c");
//           List<Object> t = new ArrayList<>();
//           t.add(0);
//           tmp.add(t);
//           return tmp;
            return Express.constant(0);
        } else if (isVariable(exp)) {
            if (isSameVariable((String) (((List) exp.get(1)).get(0)), var)) {
//                List<Object> tmp = new ArrayList<>();
//                tmp.add("c");
//                List<Object> t = new ArrayList<>();
//                t.add(1);
//                tmp.add(t);
//                return tmp;
                return Express.constant(1);
            } else {
//                List<Object> tmp = new ArrayList<>();
//                tmp.add("c");
//                List<Object> t = new ArrayList<>();
//                t.add(0);
//                tmp.add(t);
//                return tmp;
                return Express.constant(0);
            }
        } else if (isSum(exp)) {
            res.add(makeSum(deriv(addend(exp), var), deriv(augend(exp), var)));
        } else if (isProduct(exp)) {
            res.add(makeSum(
                    makeProduct(multiplier(exp), deriv(multiplicand(exp), var)),
                    makeProduct(deriv(multiplier(exp), var), multiplicand(exp))));
        } else if (isSub(exp)) {
            res.add(makeSub(deriv(addend(exp), var), deriv(augend(exp), var)));
        } else {
            new Exception("表达式错误");
        }
        return res;
    }


}
