package example.calculatorLv2;

import java.util.ArrayList;
import java.util.List;

public class CalculatorLv2 {
    // 연산 결과를 담을 리스트(캡슐화)
    private List<Integer> results;

    public CalculatorLv2() {
        results = new ArrayList<>();
    }

    public int calculate(int num1, int num2, char operator) {
        int result = 0;
        boolean isValid = true;
        switch (operator) {
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
                    System.out.println("나눗셈에서 분모에 0이 들어갈 수 없습니다. ");
                    isValid = false;
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                isValid = false;
                System.out.println("올바른 연산 기호를 입력하세요. ");
        }
        if (isValid) {
            results.add(result);
        }
        return result;
    }
    //원본 리스트를 직접 반환하지 않고 반환함
    public List<Integer> getResults() {
        return new ArrayList<>(results);
    }

    public void removeResult() {
        if (!results.isEmpty()) {
            results.remove(results.size() - 1);
        } else {
            System.out.println("저장된 결과 없음 ");
        }
    }

    //연산 기록을 보는 메서드
    public void printResults() {
        if(results.isEmpty()) {
            System.out.println("저장된 연산 기록이 없습니다. ");
        }else {
            System.out.println("연산 기록: "+results);
        }
    }

    //연산 기록 삭제
    public void clearResults() {
        if(results.isEmpty()) {
            System.out.println("삭제할 연산 기록이 없습니다.");
        }
        else {
            results.clear();
            System.out.println("연산 기록이 삭제되었습니다. " + results);
        }
    }

}
