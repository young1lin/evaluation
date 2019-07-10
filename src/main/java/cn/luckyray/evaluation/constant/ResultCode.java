package cn.luckyray.evaluation.constant;

/**
 *
 * 对于 API 的返回状态码
 * @author 杨逸林
 * @version 1.0
 * @date 2019/7/3 10:36
 **/

public enum  ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "success"),
    SYSTEM_EXCEPTION(500,"系统异常"),
    FAILURE(-1,"咋的，失败了"),
    NOT_EXIST_WINDOW_NUM(-2,"不存在该窗口"),
    USER_ID_IS_EMPTY(-3,"用户名为空"),
    WINDOW_NUM_IS_EMPTY(-4,"窗口名为空"),
    USER_IS_NOT_EXIST(-5,"不存在该用户"),
    SERIAL_NUM_IS_EMPTY(-6,"流水号为空"),
    DATABASE_IS_NOT_CONNECTED(-7,"数据库不可连接")
    ;

        private Integer code;

        private String message;

        ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer code() {
            return this.code;
        }

        public String message() {
            return this.message;
        }

        public static String getMessage(String name) {
            for (ResultCode item : ResultCode.values()) {
                if (item.name().equals(name)) {
                    return item.message;
                }
            }
            return name;
        }

        public static Integer getCode(String name) {
            for (ResultCode item : ResultCode.values()) {
                if (item.name().equals(name)) {
                    return item.code;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.name();
        }
}
