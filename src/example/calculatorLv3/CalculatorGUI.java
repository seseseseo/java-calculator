package example.calculatorLv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private final JTextField  display; //현재 계산 결과
    private final JLabel previousResult; // 이전 계산 결과
    private final ArithmeticCalculator<Double> calculator;
    private Double num1 = null;
    private OperatorType operator = null;
    private boolean isNewInput = true;

    public CalculatorGUI() {
        setTitle("계산기");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //x버튼을 누르면 애플리케이션 종료
        setLocationRelativeTo(null);   // 화면의 가운데에 띄우게 해주는 메소드


        // 현재 + 이전을 포함한 패널 생성
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        // 패널에 마진 추가해보고 싶었다. 맘에 안든다!
        //displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0 ));


        // 이전 결과를 표시할 라벨
        previousResult = new JLabel(" ");
        previousResult.setFont(new Font("Arial", Font.PLAIN, 20));
        previousResult.setHorizontalAlignment(SwingConstants.RIGHT);
        displayPanel.add(previousResult, BorderLayout.NORTH);

        //결과창
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 50));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        displayPanel.add(display, BorderLayout.SOUTH);

        add(displayPanel, BorderLayout.NORTH);

        //버튼 패널
        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(new GridLayout(5,4,5,5));

        String[] buttons = {
                "%", "c", "Back", "=",
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "±", "0", ".", "+",
        };
        for(String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPannel.add(button);
        }
        add(buttonPannel, BorderLayout.CENTER);

        calculator = new ArithmeticCalculator<>();//초기화
    }
    // 기본 기능 : 숫자 입력, 연산자 입력, 계산 수행, 전체 초기화
    // 버튼 클릭에 대한 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) { // 숫자 버튼
            if (isNewInput) {
                display.setText(command);  // 새 입력으로 시작
                previousResult.setText(command);
                isNewInput = false; // 새 입력이 아님
            } else {
                display.setText(display.getText() + command); // 기존 텍스트에 추가

            }
        } else if (command.equals(".")){
            if(!display.getText().contains(".")){
                display.setText(display.getText() + ".");
                isNewInput = false;
            }
        }
        else if ("÷×-+".contains(command)) { // 연산자 입력
        // 연산자가 눌렸을 때 이전 숫자와 연산자를 저장
            if (num1 != null && operator != null && !isNewInput) {
                double num2 = Double.parseDouble(display.getText());
                double result = calculator.calculate(num1, num2, operator);  // 계산 수행
                display.setText(formatResult(result)); //
                previousResult.setText(formatExpression(num1, operator, num2, result)); // 이전 결과 저장
                num1 = result; // 연속 계산을 위해 계산된 결과를 num1에 저장
            } else {
                num1 = Double.parseDouble(display.getText()); // 첫 번째 숫자 저장

            }
            operator = OperatorType.fromString(command); // 연산자 저장
            isNewInput = true; // 새로운 입력을 받기 위해 상태 변경
        } else if (command.equals("=")) { // "=" 버튼
            if (num1 != null && operator != null) {
                double num2 = Double.parseDouble(display.getText());
                double result = calculator.calculate(num1, num2, operator); // 계산 수행
                display.setText(formatResult(result));
                previousResult.setText(formatExpression(num1, operator, num2, result)); // 이전 결과 저장
                num1 = result; // 결과를 num1에 저장 (연속 계산)
                operator = null; // 연산자 초기화

            } else {
                // 연산자가 없을 경우 바로 계산 시작
                num1 = Double.parseDouble(display.getText());
            } isNewInput = true; // 새로운 입력을 받기 위해 상태 변경
        } else if (command.equals("Back")) { // Back 버튼
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        } else if (command.equals("c")) { // 초기화 버튼
            display.setText("");
            previousResult.setText(" ");
            num1 = null;
            operator = null;
            isNewInput = true;
        } else if(command.equals("%")){
            String text = display.getText();
            if(!text.isEmpty()){
                double value = Double.parseDouble(text);
                display.setText(String.valueOf(value / 100));
            }
        }else if(command.equals("±")){
            String text = display.getText();
            if(!text.isEmpty()){
                double value = Double.parseDouble(text);
                display.setText(formatResult(-value));
            }
        }
    }

    private String formatExpression(Double num1, OperatorType operator, double num2, double result) {
        return formatResult(num1) + " " + operatorToString(operator) + " " + formatResult(num2)+ " = " + formatResult(result);
    }


    private String operatorToString(OperatorType operator) {
        return switch (operator) {
            case ADD -> "+";
            case SUBTRACT -> " - ";
            case MULTIPLY -> "*";
            case DIVIDE -> " / ";
            case PERCENT -> " %";
            default -> throw new IllegalArgumentException("알 수 없는 연산자");
        };
    }

    private String formatResult(double result) {
        if (result == (int) result) {  // 정수인지 확인
            return String.valueOf((int) result); // 정수로 출력
        } else {
            return String.valueOf(result); // 소수로 출력
        }
    }

}




