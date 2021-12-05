package JavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Calculator.Calculate;

/**
 * 学习项目要点
 * 1、关于除法的无穷小数位置问题 √
 * 2、浮点类型计算  直接将弹栈的数据类型改成double √
 * 3、键盘和按钮点击响应  setOnAction()  √
 * 4、控件布局  主要是Button,stage,group,listener等  √
 * 5、除0问题  这个不太好解决  √
 * 6、数字匹配小数  [1-9]\d*\.?\d*用以匹配诸如：1、23、34.0、56.78 之类的非负的整数和浮点数 √
 * 7、运算符多按
 * 8、无理数运算
 * 9、负数运算
 *
 * @author Archer
 */
public class CalculatorFX extends Application {
    private String result = "";
    private TextField calculatorText;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("Calculator");
        stage.setHeight(800);
        stage.setWidth(800);
        stage.setResizable(false);
        setComponent(root);
        stage.getIcons().add(new Image("File:D:\\图片\\Saved Pictures\\sakura.jpg", 256, 256, true, true));

        stage.setScene(scene);

        stage.show();

    }

    /**
     * @param root 容器参数
     */

    public void setComponent(Group root) {
        //输入的显示框
        calculatorText = new TextField("0");
        //设置右对齐
//        calculatorText.setAlignment(Pos.CENTER_RIGHT);
        calculatorText.setFocusTraversable(false);
        calculatorText.setLayoutX(200);
        calculatorText.setLayoutY(100);
        calculatorText.setStyle(
                "-fx-pref-width: 380;"
                        + "-fx-pref-height: 80;"
                        + "-fx-font-size: 40;"

        );
        //创建数字监听对象
        NumberEvent numberEvent = new NumberEvent();
        //创建计算器按钮,设定宽和位置，数字代表
        //1
        Button one = new Button("1");
        one.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        one.setLayoutX(200);
        one.setLayoutY(200);
        one.setOnAction(numberEvent);
        //2
        Button two = new Button("2");
        two.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        two.setLayoutX(300);
        two.setLayoutY(200);
        two.setOnAction(numberEvent);
        //3
        Button three = new Button("3");
        three.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        three.setLayoutX(400);
        three.setLayoutY(200);
        three.setOnAction(numberEvent);
        //4
        Button four = new Button("4");
        four.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        four.setLayoutX(200);
        four.setLayoutY(300);
        four.setOnAction(numberEvent);
        //5
        Button five = new Button("5");
        five.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        five.setLayoutX(300);
        five.setLayoutY(300);
        five.setOnAction(numberEvent);
        //6
        Button six = new Button("6");
        six.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        six.setLayoutX(400);
        six.setLayoutY(300);
        six.setOnAction(numberEvent);
        //7
        Button seven = new Button("7");
        seven.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        seven.setLayoutX(200);
        seven.setLayoutY(400);
        seven.setOnAction(numberEvent);
        //8
        Button eight = new Button("8");
        eight.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        eight.setLayoutX(300);
        eight.setLayoutY(400);
        eight.setOnAction(numberEvent);
        //9
        Button nine = new Button("9");
        nine.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        nine.setLayoutX(400);
        nine.setLayoutY(400);
        nine.setOnAction(numberEvent);
        //0
        Button zero = new Button("0");
        zero.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        zero.setLayoutX(300);
        zero.setLayoutY(500);
        zero.setOnAction(numberEvent);

        //无理数pi
        Button pi = new Button("Π");
        pi.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        pi.setLayoutX(200);
        pi.setLayoutY(500);
        pi.setOnAction(numberEvent);
        pi.setFocusTraversable(false);
        root.getChildren().add(pi);

        //设置运算符监听对象
        LogicEvent logicEvent = new LogicEvent();
        //小数点
        Button point = new Button(".");
        point.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size: 20;"
        );
        point.setFocusTraversable(false);
        point.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //添加小数点进表达式
                result += ".";
                calculatorText.setText(result);
            }
        });
        point.setLayoutX(400);
        point.setLayoutY(500);
        //
        Button add = new Button("+");
        add.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
        );
        add.setFocusTraversable(false);
        add.setOnMouseClicked(logicEvent);
        add.setLayoutX(500);
        add.setLayoutY(200);
        //减号
        Button subtract = new Button("-");
        subtract.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
        );
        subtract.setOnMouseClicked(logicEvent);
        subtract.setFocusTraversable(false);
        subtract.setLayoutX(500);
        subtract.setLayoutY(300);

        Button multiply = new Button("x");
        multiply.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
        );
        multiply.setOnMouseClicked(logicEvent);
        multiply.setLayoutX(500);
        multiply.setLayoutY(400);

        Button devide = new Button("/");
        devide.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
        );
        devide.setOnMouseClicked(logicEvent);
        devide.setLayoutX(500);
        devide.setLayoutY(500);
        //取模
        Button mode = new Button("%");
        mode.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
        );
        mode.setOnMouseClicked(logicEvent);
        mode.setLayoutX(500);
        mode.setLayoutY(600);
        root.getChildren().add(mode);
        //等号
        Button equal = new Button("=");
        equal.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:30;"
                + "-fx-background-color: #1577ee;"
                );
        equal.setLayoutX(400);
        equal.setLayoutY(600);
        equal.setOnAction((ActionEvent event) -> {
            if ("/0".equals(result.substring(result.length() - 2))) {
                result = result.substring(0, result.length() - 3);
                calculatorText.setText("不能除0");
            }
            double out = Calculate.calculate(Calculate.toSuffixExpression(Calculate.toInfixExpression(result)));
            result = String.valueOf(out);
            calculatorText.setText(result);
        });

        //清空键
        Button clear = new Button("C");
        clear.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:20;"
        );
        clear.setFocusTraversable(false);
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                result = "";
                calculatorText.setText("0");
            }
        });
        clear.setLayoutX(200);
        clear.setLayoutY(600);

        //删除键
        Button del = new Button("D");
        del.setStyle("-fx-pref-width: 80;"
                + "-fx-pref-height: 80;"
                + "-fx-font-size:20;"
        );
        del.setFocusTraversable(false);
        del.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                result = result.substring(0, result.length() - 1);
                calculatorText.setText(result);
            }
        });
        del.setLayoutX(300);
        del.setLayoutY(600);


        root.getChildren().addAll(del, clear);
        root.getChildren().add(calculatorText);
        root.getChildren().addAll(add, devide, subtract, multiply, equal);
        root.getChildren().addAll(one, two, three, four, five, six,
                seven, eight, nine, zero, point);
    }

    /**
     * 设置数字输入监听
     */
    class NumberEvent implements EventHandler {
        @Override
        public void handle(Event event) {
            Button b = (Button) event.getSource();
            String bNum = b.getText();
            result += bNum;
            calculatorText.setText(result);

        }
    }

    /**
     * 运算符的监听器
     */
    class LogicEvent implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            //得到按钮
            Button logicBtn = (Button) event.getSource();
            //获取按钮内容
            String logic = logicBtn.getText();
            result += logic;
            calculatorText.setText(result);
        }

    }
    //1+3-6x7/3

    public static void main(String[] args) {
        launch(args);
    }
}
