package deriv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wones
 * @desc:实现求导约束
 * @date:2022/9/12 14:05
 */
public class ConstraintImp implements Constraint{
    @Override
    public boolean isNumber(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("c")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isVariable(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("v")){
            return true;
        }
        return false;
    }

    @Override
    public boolean isSameVariable(String v1, String v2) {
        if(v1.equals(v2)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isSum(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("+")){
            return true;
        }
        return false;
    }

    @Override
    public List<Object> addend(List<Object> exp) {

        return (List<Object>) exp.get(1);
    }

    @Override
    public List<Object> augend(List<Object> exp) {
        return (List<Object>) exp.get(2);
    }

    @Override
    public List<Object> makeSum(List<Object> a1, List<Object> a2) {
        List<Object> res = new ArrayList<>();
        String type = new String("+");
        res.add(type);
        res.add(a1);
        res.add(a2);
        return res;
    }

    @Override
    public boolean isProduct(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("*")){
            return true;
        }
        return false;
    }

    @Override
    public List<Object> multiplier(List<Object> exp) {
        return (List<Object>) exp.get(1);
    }

    @Override
    public List<Object> multiplicand(List<Object> exp) {
        return (List<Object>) exp.get(2);
    }

    @Override
    public List<Object> makeProduct(List<Object> m1, List<Object> m2) {
        List<Object> res = new ArrayList<>();
        String type = new String("*");
        res.add(type);
        res.add(m1);
        res.add(m2);
        return res;
    }
}
