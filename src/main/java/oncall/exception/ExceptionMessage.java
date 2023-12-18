package oncall.exception;

public enum ExceptionMessage {
    INVALID_MONTH_DAY("월과 요일을 , 구분자로 입력해야합니다."),
    NOT_NUMBER("숫자만 입력 가능합니다."),
    INVALID_MONTH("월은 1~12 사이의 숫자만 입력 가능합니다."),
    NOT_FOUND_DAY("요일을 찾을 수 없습니다."),
    ;

    private static final String ERROR = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR + message;
    }

    public String getMessage() {
        return message;
    }
}
