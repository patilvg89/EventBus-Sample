package eventbus.virendra.com.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import eventbus.virendra.event.LogOutEvent;
import eventbus.virendra.event.LoginEvent;
import eventbus.virendra.model.User;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onEvent(LoginEvent loginEvent) {
        //As you saw, the last sticky event gets delivered automatically to matching subscribers when they register.
        // But sometimes it may be more convenient to manually check on sticky events.
        // Also it may be necessary to remove (consume) sticky events so that they won’t be delivered anymore
        // "Consume" the sticky event
        //The method removeStickyEvent is overloaded: when you pass in the class,
        // it will return the previously held sticky event.
        EventBus.getDefault().removeStickyEvent(loginEvent);
        // Now do something with it

        final User user = loginEvent.getUser();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main2Activity.this, "Logged in user in Main2Activity= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // get Event in same activity/Fragment
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(LogOutEvent event) {
        User user = event.getUser();
        Toast.makeText(this, "Logged out user in Main2Activity= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
