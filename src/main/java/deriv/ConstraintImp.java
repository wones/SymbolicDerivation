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
    public boolean isSub(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("-")){
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
        if(a1.get(0).equals("c") && a2.get(0).equals("c")){
            if(a1.get(0).equals("c")){
                if((int) ((List)a1.get(1)).get(0) == 0){
                    res.add(a2);
                    return res;
                }
            }
            if(a2.get(0).equals("c")){
                if((int) ((List)a2.get(1)).get(0) == 0){
                    res.add(a1);
                    return res;
                }
            }
            int sum = (int) ((List)a1.get(1)).get(0) + (int) ((List)a2.get(1)).get(0);
            res.add(Express.constant(sum));
            return res;
        }

        String type = new String("+");
        res.add(type);
        res.add(a1);
        res.add(a2);
        return res;
    }

    @Override
    public List<Object> makeSub(List<Object> a1, List<Object> a2) {
        List<Object> res = new ArrayList<>();
        if(a1.get(0).equals("c") && a2.get(0).equals("c")){
            if(a1.get(0).equals("c")){
                if((int) ((List)a1.get(1)).get(0) == 0){
                    res.add(a2);
                    return res;
                }
            }
            if(a2.get(0).equals("c")){
                if((int) ((List)a2.get(1)).get(0) == 0){
                    res.add(a1);
                    return res;
                }
            }
            int sum = (int) ((List)a1.get(1)).get(0) - (int) ((List)a2.get(1)).get(0);
            res.add(Express.constant(sum));
            return res;
        }

        String type = new String("-");
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
    public boolean isDiv(List<Object> exp) {
        String s = (String) exp.get(0);
        if(s.equals("/")){
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
        if(m1.get(0).equals("c") || m2.get(0).equals("c")){
            if(m1.get(0).equals("c") && m2.get(0).equals("c")){
                int product = (int) ((List)m1.get(1)).get(0) * (int) ((List)m2.get(1)).get(0);
                res.add(Express.constant(product));
                return res;
            } else if(m1.get(0).equals("c")){
                if((int) ((List)m1.get(1)).get(0) == 0){
                    res.add(Express.constant(0));
                    return res;
                }else if((int) ((List)m1.get(1)).get(0) == 1){
                    res.add(m2);
                    return res;
                }
            }else if(m2.get(0).equals("c")){
                if((int) ((List)m2.get(1)).get(0) == 0){
                    res.add(Express.constant(0));
                    return res;
                }else if((int) ((List)m2.get(1)).get(0) == 1){
                    res.add(m1);
                    return res;
                }
            }
        }

        String type = new String("*");
        res.add(type);
        res.add(m1);
        res.add(m2);
        return res;
    }

    @Override
    public List<Object> makeDiv(List<Object> m1, List<Object> m2) {
        List<Object> res = new ArrayList<>();
        if(m1.get(0).equals("c") || m2.get(0).equals("c")){
            if(m1.get(0).equals("c") && m2.get(0).equals("c")){
                int product = (int) ((List)m1.get(1)).get(0) / (int) ((List)m2.get(1)).get(0);
                res.add(Express.constant(product));
                return res;
            } else if(m1.get(0).equals("c")){
                if((int) ((List)m1.get(1)).get(0) == 0){
                    res.add(Express.constant(0));
                    return res;
                }
            }
        }

        String type = new String("/");
        res.add(type);
        res.add(m1);
        res.add(m2);
        return res;
    }
}
