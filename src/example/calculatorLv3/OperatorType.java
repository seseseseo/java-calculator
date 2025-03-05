package example.calculatorLv3;
import java.util.function.BiFunction;

public enum OperatorType {
    ADD,       // 덧셈 +
    SUBTRACT,  // 뺄셈 -
    MULTIPLY,  // 곱셈 ×
    DIVIDE,    // 나눗셈 ÷
    PERCENT;   // 백분율 %

    // 연산자 이름을 문자열로 반환하는 메소드
    public static OperatorType fromString(String operator) {
        if (operator == null) {
            throw new IllegalArgumentException("연산자 없음");
        }
        return switch (operator) {
            case "+" -> ADD;
            case "-" -> SUBTRACT;
            case "×" ->  // 곱셈 기호
                    MULTIPLY;
            case "÷" ->  // 나눗셈 기호
                    DIVIDE;
            case "%" ->  // 퍼센트 기호
                    PERCENT;
            default -> throw new IllegalArgumentException("알 수 없는 연산자: " + operator);
        };
    }


}
