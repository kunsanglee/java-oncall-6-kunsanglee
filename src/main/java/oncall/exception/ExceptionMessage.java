package oncall.exception;

public enum ExceptionMessage {
    INVALID_MONTH_DAY_INPUT("월과 요일을 올바르게 입력해주세요."),
    INVALID_MONTH_INPUT("월은 숫자만 입력 가능합니다."),
    INVALID_DAY_INPUT("올바르지 않은 요일입니다."),
    INVALID_DAY_OF_MONTH("해당 월에 시작 요일이 일치하지 않습니다."),
    INVALID_WORKER_COUNTS("비상 근무 사원을 최소 5명 이상 최대 35명 사이로 입력해주세요."),
    INVALID_NICKNAME_LENGTH("사원의 닉네임은 최대 5글자까지 가능합니다."),
    INVALID_NICKNAME_CHARACTER("사원의 닉네임은 한글만 가능합니다."),
    DUPLICATED_NICKNAME("한 근무에 사원이 중복될 수 없습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
