package deriv;

import java.util.List;
import java.util.Scanner;

/**
 * @author:wones
 * @desc:测试
 * @date:2022/9/12 12:29
 */
public class Test {
    public static void main(String[] args) {
        Deriv deriv = new Deriv();
        System.out.println("输入函数表达式和变量：");
        Scanner scanner = new Scanner(System.in);
        String[] res = scanner.nextLine().split(" ");
        String exp = res[0];
        String var = res[1];
        String result = Express.ExpressionToString(deriv.deriv(Express.genExpression(exp), var));
        System.out.println("求导结果为：");
        System.out.println(result);
        System.out.println("");


    }
}
