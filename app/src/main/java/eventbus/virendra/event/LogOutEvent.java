package eventbus.virendra.event;

import eventbus.virendra.model.User;

/**
 * Created by Virendra
 */

public class LogOutEvent {

    private User user;

    public LogOutEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
