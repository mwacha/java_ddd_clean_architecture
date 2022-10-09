package tk.mwacha.domain.shared.notification;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class NotificationTest {

    @Test
    void should_create_errors() {
        final var notification = new Notification();
        final var error = Error.with("error message", "customer");

        notification.addError(error);

        assertEquals(notification.messages(Optional.of("customer")), "customer: error message,");

        final var error2 = Error.with("error message2", "customer");

        notification.addError(error2);

        assertEquals(notification.messages(Optional.of("customer")), "customer: error message,customer: error message2,");

        final var error3 = Error.with("error message3", "order");

        notification.addError(error3);

        assertEquals(notification.messages(Optional.empty()), "customer: error message,customer: error message2,order: error message3,");

    }

    @Test
    void should_check_notification_has_errors() {
        final var notification = new Notification();
        final var error = Error.with("error message", "customer");

        notification.addError(error);

        assertTrue(notification.hasErrors());
    }

    @Test
    void should_get_all_errors() {
        final var notification = new Notification();
        final var error = Error.with("error message", "customer");

        notification.addError(error);

        assertEquals(notification.getErrors(), List.of(error));
    }


}