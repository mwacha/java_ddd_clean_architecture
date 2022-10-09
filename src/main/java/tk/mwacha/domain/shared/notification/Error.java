package tk.mwacha.domain.shared.notification;

public record Error(String message, String context) {

    public static Error with(final String message, final String context) {
        return new Error(message, context);
    }
}
