package com.api.rest.dto;

public enum ResultCode {

    /**
     * 성공적으로 수행됨
     */
    SUCCESSFUL(0),
    /**
     * 사용자가 DB에 중복된 id 값을 사용 요청함
     */
    DUPLICATE_KEY(-1),
    /**
     * 업데이트된 행이 없음
     */
    NO_UPDATED_ROWS(-2),
    /**
     * 사용자가 잘못된 데이터를 요청함 (constraint 제약 조건 등을 깨트리는 요청)
     */
    BAD_DATA_REQUEST(-3),
    /**
     * 데이터베이스 SQL 실행 과정에서 알 수 없는 오류가 발생
     */
    UNKNOWN_DATABASE_ERROR(-98),
    /**
     * 알 수 없는 오류가 발생함
     */
    UNKNOWN_ERROR(-99);

    private final int value;

    ResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
