package Calculator;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * @author Archer
 */
public class Calculate {

    /**
     * @param list 分割表达式数组
     * @return 计算结果
     */
    public static double calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String item : list) {
            //用正则表达式匹配
            if (item.matches("[1-9]\\d*\\.?\\d*") || "Π".equals(item)) {
                //如果是数字就直接俄放进栈
                stack.push(item);
            } else {
                //如果是运算符,先弹出两个数再算，然后放回去
                double num2 = Double.parseDouble(stack.pop());
                double num1 = Double.parseDouble(stack.pop());
                double temp = 0;

                if ("x".equals(item)) {
                    temp = num1 * num2;
                } else if ("/".equals(item)) {
                    try{
                        temp = num1 / num2;
                    }catch (EmptyStackException e){
                        System.out.println(e.toString());
                    }
                } else if ("-".equals(item)) {
                    temp = num1 - num2;
                } else if ("+".equals(item)) {
                    temp = num1 + num2;
                } else if("%".equals(item)) {
                    temp = num1 % num2;
                }else {
                    throw new RuntimeException("不是运算符");
                }
                //计算完吧temp放回栈内
                stack.push("" + temp);
            }
        }


        return Double.parseDouble(stack.pop());

    }

    public static List<String> toSuffixExpression(List<String> ls) {
        //符号栈
        Stack<String> s1 = new Stack<String>();
        //存储后缀表达式的中间结果
        List<String> s2 = new ArrayList<String>();

        for (String item : ls) {
            //如果是数字直接加到s2里
            if (item.matches("[1-9]\\d*\\.?\\d*") || "Π".equals(item)) {
                s2.add(item);
            } else if ("(".equals(item)) {
                //左括号先加到s1里
                s1.push(item);
            } else if (")".equals(item)) {
                //遇到右括号，弹出s1里的符号到s2，直到遇到"("
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //将"(" 弹出s1消除小括号
                s1.pop();
            } else {
                //对应运算符情况
                //当item的优先级小于s1栈顶，s1栈顶运算符弹出加到s2里
                while (s1.size() != 0 && getValue(item) <= getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);

            }
        }
        //将s1里的运算符依次弹出加到s2里
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    /**
     * 中缀表达式字符串转list
     *
     * @param s 传入中缀表达式
     * @return 返回中缀表达式对应的列表
     */
    public static List<String> toInfixExpression(String s) {
        List<String> ls = new ArrayList<String>();
        //指针遍历中缀表达式字符串
        int i = 0;
        //多位数的拼接
        String str;
        //遍历字符放到c
        char c;
        do {
            //如果是非数字直接加进列表
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //，如果是数字要判断多位数情况,先将str置为""
                str = "";
                //还要考虑小数情况，小数点.的ASCII码是46,pi的ASCII是252
                while (i < s.length() && (((c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) || (c = s.charAt(i)) == 46||(c = s.charAt(i)) == 252)) {
                    str += c;
                    i++;
                }
                //拼接完成，放入列表里
                ls.add(str);
            }
        } while (i < s.length());

        return ls;
    }

    /**
     * @param operation 输入运算符
     * @return 返回优先级数字
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "x":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            case "%":
                result = 2;
                break;
            default:
                break;
        }
        return result;
    }
}
