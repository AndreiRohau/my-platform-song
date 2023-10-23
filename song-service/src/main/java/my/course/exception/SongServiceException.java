package my.course.exception;

import org.springframework.http.HttpStatus;

public class SongServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public static SongServiceException init400() {
        return new SongServiceException("400 – Song metadata missing validation error", HttpStatus.BAD_REQUEST);
    }

    public static SongServiceException init404() {
        return new SongServiceException("404 – The song metadata with the specified id does not exist", HttpStatus.NOT_FOUND);
    }

    public static SongServiceException init500() {
        return new SongServiceException("500 – An internal server error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static boolean isExceptionOfCode(Exception e, int httpStatusCode) {
        return e.getClass() == SongServiceException.class
                && ((SongServiceException) e).getHttpStatusValue() == httpStatusCode;
    }

    public SongServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getHttpStatusValue() {
        return httpStatus.value();
    }
}
