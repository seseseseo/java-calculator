package example.calculatorLv1;

import java.util.Scanner;

public class App {
    //Lv1. 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            // 양의 정수(0 포함)를 입력받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            // 사칙연산 기호(➕,➖,✖️,➗)를 입력받기
            System.out.println("사칙연산 기호 입력하기: ");
            // sc를 사용해 사칙연산 기호를 전달받을 수 있음
            char op = sc.next().charAt(0);

            int result = 0;
            boolean isValid = true;

            switch (op) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                    } else {
                        result = num1 / num2;
                    }
                default:
                    System.out.println("연산 기호를 올바르게 입력하세요");
                    isValid = false;
            }
            if(isValid) {
                System.out.println("결과: " + result);
            }

            sc.nextLine();
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String exitCmd = sc.nextLine();
            if (exitCmd.equals("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
        }

    }
}