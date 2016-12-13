package eventbus.virendra.com.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;
import eventbus.virendra.event.LogOutEvent;
import eventbus.virendra.event.LoginEvent;
import eventbus.virendra.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.button1)
    void onLoginButtonClick() {
        // call to retrofit / volley
        // onSuccess make a call to below lines
        User user = new User(1, "Virendra", "Pune,India");
        EventBus.getDefault().post(new LoginEvent(user));
    }

    @OnClick(R.id.button2)
    void onLogoutButtonClick() {
        // Clear user data and  call below lines
        User user = new User(1, "Virendra", "Pune,India");
        EventBus.getDefault().post(new LogOutEvent(user));
    }

    // get Event in same activity/Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent event) {
        User user = event.getUser();
        Toast.makeText(this, "Logged in user= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
    }

    // get Event in same activity/Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LogOutEvent event) {
        User user = event.getUser();
        Toast.makeText(this, "Logged out user= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
