package oncall.exception;

public enum ExceptionMessage {
    INVALID_MONTH_DAY_INPUT("월과 요일을 올바르게 입력해주세요."),
    INVALID_MONTH_INPUT("월은 숫자만 입력 가능합니다."),
    INVALID_DAY_INPUT("올바르지 않은 요일입니다."),
    INVALID_DAY_OF_MONTH("해당 월에 시작 요일이 일치하지 않습니다."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
