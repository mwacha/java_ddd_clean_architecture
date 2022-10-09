package tk.mwacha.domain.shared.notification;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@EqualsAndHashCode
public class Notification {

    private List<Error> errors = new ArrayList<>();

    public void addError(final Error error) {
        this.errors.add(error);
    }


    public String messages(final Optional context) {
        final var message = new StringBuilder();
        this.errors.stream().filter(error -> !context.isPresent() || error.context() == context.get()).forEach(error -> {
            message.append(error.context()).append(": ").append(error.message()).append(",");
        });

        return message.toString();
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }
}
