package deriv;

import javax.management.ObjectName;
import java.util.*;


/**
 * @author:wones
 * @desc:生成表达式
 * @date:2022/9/12 15:06
 */
public class Express {
    public static List<Object> constant(int c) {
        List<Object> c1 = new ArrayList<>();
        c1.add("c");
        List<Object> c2 = new ArrayList<>();
        c2.add(c);
        c1.add(c2);
        return c1;
    }

    public static List<Object> variable(String var) {
        List<Object> c1 = new ArrayList<>();
        c1.add("v");
        List<Object> c2 = new ArrayList<>();
        c2.add(var);
        c1.add(c2);
        return c1;
    }


    private static List<String> parseStr(String str) {
        List<String> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        int len = chars.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            if (Character.isLetterOrDigit(chars[i])) {
                stringBuffer.append(chars[i]);
            } else {
                list.add(stringBuffer.toString());
                list.add(String.valueOf(chars[i]));
                stringBuffer.delete(0, stringBuffer.length());
            }
        }
        if (stringBuffer.length() != 0) {
            list.add(stringBuffer.toString());
        }
        return list;
    }

    private static List<String> reveStr(List<String> list) {
        Collections.reverse(list);
        return list;
    }

    private static List<Object> midOrderToPreOrderParse(String str) {
        Stack<String> stack = new Stack<>();
        List<Object> list = new ArrayList<>();
        List<String> elements = reveStr(parseStr(str));
        for (String e : elements) {
            if (e.equals("")) {
                continue;
            } else {
                if (e.charAt(0) >= '0' && e.charAt(0) <= '9') {
                    list.add(Express.constant(Integer.parseInt(e)));
                } else if (e.equals(")")) {
                    stack.push(e);
                } else if (e.equals("(")) {
                    while (true) {
                        String c = stack.pop();
                        if (c.equals(")")) {
                            break;
                        } else {
                            list.add(c);
                        }
                    }
                } else {
                    while (true) {
                        if (stack.isEmpty()) {
                            stack.push(e);
                            break;
                        }
                        String c = stack.peek();
                        if (stack.peek().equals(")") || ((c.equals("+") || c.equals("-")) || (e.equals("*") || e.equals("/")))) {
                            stack.push(e);
                            break;
                        }
                        list.add(stack.pop());
                    }
                }

            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }

    private static List<Character> midOrderToPerOrder(String str) {
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        char[] elements = str.toCharArray();
        int len = elements.length;
        for (int i = len - 1; i >= 0; i--) {
            char e = elements[i];
            if (Character.isLetterOrDigit(e)) {
                list.add(e);
            } else if (e == ')') {
                stack.push(e);
            } else if (e == '(') {
                while (true) {
                    Character c = stack.pop();
                    if (c == ')') {
                        break;
                    } else {
                        list.add(c);
                    }
                }
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push(e);
                        break;
                    }
                    Character c = stack.peek();
                    if (stack.peek() == ')' || ((c == '+' || c == '-') || (e == '*' || e == '/'))) {
                        stack.push(e);
                        break;
                    }
                    list.add(stack.pop());
                }
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }

    private static int proTest(Character a1) {
        if (a1.equals('*') || a1.equals('/')) {
            return 1;
        } else if (a1.equals('+') || a1.equals('-')) {
            return 0;
        } else {//只能是括号了
            return 2;
        }
    }

    private static boolean isNumber(String c) {
        if (c.charAt(0) >= '0' && c.charAt(0) <= '9') {
            return true;
        }
        return false;
    }

    private static boolean isLetter(String c) {
        if (c.charAt(0) >= 'a' && c.charAt(0) <= 'z' || c.charAt(0) >= 'A' && c.charAt(0) <= 'Z') {
            return true;
        }
        return false;
    }

    private static List<Object> midOrderToPostOrderParse(String str) {
        List<Object> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        List<String> elements = parseStr(str);
        for (String e : elements) {
            if (e.equals("")) {
                continue;
            } else {
                if (isNumber(e)) {
                    list.add(Express.constant(Integer.parseInt(e)));
                } else if (isLetter(e)) {
                    list.add(Express.variable(e));
                } else if (e.equals("(")) {
                    stack.push(e);
                } else if (e.equals(")")) {
                    while (!stack.isEmpty()) {
                        String s = stack.pop();
                        if (s.equals("(")) {
                            break;
                        }
                        list.add(s);
                    }
                } else {
                    if (stack.isEmpty()) {
                        stack.push(e);
                    } else {
                        String s = stack.peek();
                        while (!stack.isEmpty() && proTest(s.charAt(0)) >= proTest(e.charAt(0))) {
                            String a = stack.peek();
                            if (!a.equals("(")) {
                                list.add(stack.pop());
                            } else {
                                break;
                            }
                        }
                        stack.push(e);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }


    private static List<Character> midOrderToPostOrder(String str) {
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        char[] elements = str.toCharArray();
        for (char e : elements) {
            if (Character.isLetterOrDigit(e)) {
                list.add(e);
            } else if (e == '(') {
                stack.push(e);
            } else if (e == ')') {
                while (!stack.isEmpty()) {
                    Character c = stack.pop();
                    if (c == '(') {
                        break;
                    }
                    list.add(c);
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(e);
                } else {
                    Character c = stack.peek();
                    while (!stack.isEmpty() && proTest(c) >= proTest(e)) {
                        Character a = stack.peek();
                        if (a != '(') {
                            list.add(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(e);
                }
            }
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public static List<Object> genExpression(String expstr) {
        Stack<Object> stack = new Stack<>();
        List<Object> parseList = midOrderToPostOrderParse(expstr);
        for (Object obj : parseList) {
            if (obj instanceof String) {
                Object a2 = stack.pop();
                Object a1 = stack.pop();
                List<Object> exp = new ArrayList<>();
                exp.add(obj);
                exp.add(a1);
                exp.add(a2);
                stack.push(exp);
            } else {
                stack.push(obj);
            }
        }
        if (!stack.isEmpty()) {
            return (List<Object>) stack.pop();
        } else {
            return null;
        }
    }

    private static List<Object> result = new ArrayList<>();

    private static List<Object> genExpand(List<Object> exp) {
        genExpandAction(exp);
        return result;
    }

    private static void genExpandAction(List<Object> exp) {
        if (exp.get(0) instanceof List) {
            genExpandAction((List<Object>) exp.get(0));
        } else if (exp.get(0).equals("c") || exp.get(0).equals("v")) {
            result.add(((List) exp.get(1)).get(0));
        } else {
            result.add(exp.get(0));
            genExpandAction((List<Object>) exp.get(1));
            genExpandAction((List<Object>) exp.get(2));
        }
    }

    private static boolean isOperator(Object obj) {
        if (obj instanceof String && (obj.equals("-") || obj.equals("+") || obj.equals("*"))) {
            return true;
        }
        return false;
    }

    private static boolean isN(Object obj) {
        if (!(obj instanceof String)) {
            return true;
        }
        return false;
    }

    private static boolean isV(Object obj) {
        if (obj instanceof String && !(obj.equals("-") || obj.equals("+") || obj.equals("*"))) {
            return true;
        }
        return false;
    }

    public static String ExpressionToString(List<Object> exp) {
        List<Object> list = genExpand(exp);
        Stack<Object> stack = new Stack<>();
        int len = list.size();

        for (int i = len - 1; i >= 0; --i) {
            if (isOperator(list.get(i))) {
                StringBuffer stringBuffer = new StringBuffer();
                String op = (String) list.get(i);
                Object a1 = stack.pop();
                Object a2 = stack.pop();
                stringBuffer.append("(");
                stringBuffer.append(a1);
                stringBuffer.append(op);
                stringBuffer.append(a2);
                stringBuffer.append(")");
                stack.push(stringBuffer);


            } else {
                stack.push(new StringBuffer().append(list.get(i)));
            }
        }
        if(!stack.isEmpty()){
            StringBuffer stringBuffer = (StringBuffer) stack.pop();
            return stringBuffer.toString();
        }
        return null;

    }

}
