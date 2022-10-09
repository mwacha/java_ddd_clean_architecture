package tk.mwacha.domain.shared.exception;

import tk.mwacha.domain.shared.notification.Error;

import java.util.List;

public class ErrorException extends RuntimeException {

    public ErrorException(final List<Error> errors) {
        super(errors.stream().map(error -> error.context().concat(": ").concat(error.message().concat(","))).toString());
    }
}
