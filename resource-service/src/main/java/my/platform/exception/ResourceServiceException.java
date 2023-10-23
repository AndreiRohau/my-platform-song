package my.platform.exception;

import org.springframework.http.HttpStatus;

public class ResourceServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public static ResourceServiceException init400() {
        return new ResourceServiceException("400 – Validation failed or request body is invalid MP3", HttpStatus.BAD_REQUEST);
    }

    public static ResourceServiceException init404() {
        return new ResourceServiceException("404 – The resource with the specified id does not exist", HttpStatus.NOT_FOUND);
    }

    public static ResourceServiceException init500() {
        return new ResourceServiceException("500 – An internal server error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResourceServiceException init502() {
        return new ResourceServiceException("502 – An internal server error has occurred - Bad Gateway - GATEWAY NOT FOUND", HttpStatus.BAD_GATEWAY);
    }

    public static boolean isExceptionOfCode(Exception e, int httpStatusCode) {
        return e.getClass() == ResourceServiceException.class
                && ((ResourceServiceException) e).getHttpStatusValue() == httpStatusCode;
    }

    public ResourceServiceException(String message, HttpStatus httpStatus) {
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
