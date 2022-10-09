package tk.mwacha.domain.shared;

import lombok.Getter;
import tk.mwacha.domain.shared.notification.Notification;

import java.util.UUID;

@Getter
public class AbstractEntity {


    protected UUID id;
    protected Notification notification;

    protected AbstractEntity() {
        this.notification = new Notification();
    }


}
