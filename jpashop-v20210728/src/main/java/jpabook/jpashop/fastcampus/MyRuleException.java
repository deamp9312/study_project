package jpabook.jpashop.fastcampus;

public class MyRuleException extends Exception{

    public MyRuleException() {
        super("My custom rule exception occurred");
    }

    public MyRuleException(String message) {
        super(message);
    }

    public MyRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    // 다른 생성자나 메서드 등을 필요에 따라 추가할 수 있습니다.

    // 예제에서 사용할 수 있는 메서드
    public void logDetails() {
        System.err.println("Custom exception details: " + getMessage());
        if (getCause() != null) {
            System.err.println("Caused by: " + getCause());
        }
        // 필요한 경우 더 많은 로깅 로직을 추가할 수 있습니다.
    }
}
