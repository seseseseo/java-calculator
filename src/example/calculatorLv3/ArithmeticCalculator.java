package example.calculatorLv3;

public class ArithmeticCalculator<T extends Number> {
    public T calculate(T num1, T num2, OperatorType operator) {
        double n1 = num1.doubleValue();
        double n2 = num2.doubleValue();
        double result = switch (operator) {
            case ADD -> n1 + n2;
            case SUBTRACT -> n1 - n2;
            case MULTIPLY -> n1 * n2;
            case DIVIDE -> {
                if (n2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                yield n1 / n2;
            }
            case PERCENT -> n1 * (n2 / 100);
            default -> throw new IllegalArgumentException("알 수 없는 연산자");
        };

        // 결과를 입력 타입 T에 맞게 변환
        return convertToType(result, num1);
    }

    @SuppressWarnings("unchecked")
    private T convertToType(double result, T numType) {
        if (numType instanceof Integer) {
            return (T) Integer.valueOf((int) result); // 정수형 변환
        } else if (numType instanceof Float) {
            return (T) Float.valueOf((float) result); // Float 변환
        } else if (numType instanceof Long) {
            return (T) Long.valueOf((long) result); // Long 변환
        } else {
            return (T) Double.valueOf(result); // 기본적으로 Double 변환
        }
    }
}

