package oncall.exception;

public enum ExceptionMessage {
    INVALID_MONTH_DAY("월과 요일을 , 구분자로 입력해야합니다."),
    NOT_NUMBER("숫자만 입력 가능합니다."),
    INVALID_MONTH("월은 1~12 사이의 숫자만 입력 가능합니다."),
    NOT_FOUND_DAY("요일을 찾을 수 없습니다."),
    DUPLICATED_WORKER("중복된 사원 닉네임이 있습니다."),
    INVALID_DATE("잘못된 날짜입니다."),
    INVALID_WORKER_SIZE("근무 명단은 5명 이상 35명 이하의 사원만 입력 가능합니다."),
    INVALID_WORKER_NAME_CHARACTER("사원 닉네임에는 한글만 입력할 수 있습니다."),
    INVALID_WORKER_NAME_LENGTH("사원의 이름은 최대 5자까지 입력 가능합니다."),
    NOT_CONTAINS_ALL_EACH_WORKER("순서를 제외한 평일, 휴일 근무순번에 포함된 근무자 구성원이 같아야 합니다."),
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
