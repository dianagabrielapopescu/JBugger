package ro.msg.edu.jbugs.exception;

/**
 * Document me.
 *
 * @author msg systems AG; UserModel Name.
 * @since 19.1.2
 */
public class BusinessException extends Exception {

    private String errorCode;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
