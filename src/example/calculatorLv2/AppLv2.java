package example.calculatorLv2;

import java.util.Scanner;

public class AppLv2 {
    //Lv1. 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculatorLv2 calc = new CalculatorLv2();

        while (true){
            System.out.print("첫 번째 숫자를 입력하세요:");
            int num1 = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요:");
            int num2 = sc.nextInt();

            System.out.print("사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            int result = calc.calculate(num1, num2, operator);
            System.out.println("결과: " + result);

            System.out.println("이전 결과를 삭제하시겠습니까? (yes 입력 시 출력, 그 외 계속 진행): " );
            String deleteCommand = sc.next();
            if (deleteCommand.equalsIgnoreCase("yes")){
                calc.removeResult();
                System.out.print("이전 연산결과가 삭제되었습니다. ");
            }
            sc.nextLine();

            System.out.print("연산 기록을 보시겠습니까? (yes 입력 시 출력, 그 외 계속 진행): ");
            String printCommand = sc.next();
            if (printCommand.equalsIgnoreCase("yes")) {
                calc.printResults();
            }

            System.out.println("연산 기록을 삭제하겠습니까? (yes 입력 시 출력, 그 외 계속 진행): ");
            String clearCommand = sc.next();
            if(clearCommand.equalsIgnoreCase("yes")) {
                calc.clearResults();
                System.out.println("연산 기록이 삭제되었습니다.");
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료): ");
            String exitCmd = sc.nextLine();
            if (exitCmd.equalsIgnoreCase("exit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
        }
        sc.close();
    }
}