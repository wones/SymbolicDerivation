package deriv;

import java.util.List;

/**
 * @author:wones
 * @desc:求导约束
 * @date:2022/9/12 13:36
 */
public interface Constraint {
    public boolean isNumber(List<Object> exp);
    public boolean isVariable(List<Object> exp);
    public boolean isSameVariable(String v1,String v2);
    public boolean isSum(List<Object> exp);
    public List<Object> addend(List<Object> exp);
    public List<Object> augend(List<Object> exp);
    public List<Object> makeSum(List<Object> a1,List<Object> a2);

    public boolean isProduct(List<Object> exp);
    public List<Object> multiplier(List<Object> exp);
    public List<Object> multiplicand(List<Object> exp);
    public List<Object> makeProduct(List<Object> m1,List<Object> m2);
}
