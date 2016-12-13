package eventbus.virendra.event;

import eventbus.virendra.model.User;

/**
 * Created by virendra
 */

public class LoginEvent {
    private User user;

    public LoginEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
