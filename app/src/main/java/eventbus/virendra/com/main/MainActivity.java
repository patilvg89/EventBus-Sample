package eventbus.virendra.com.main;

import android.content.Intent;
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
        // Basically, you post an event as sticky with .postSticky() on your Activity B and when Activity A is registered again,
        // it will automatically receive that sticky event.
        EventBus.getDefault().postSticky(new LoginEvent(user));
        startActivity(new Intent(this, Main2Activity.class));
    }

    @OnClick(R.id.button2)
    void onLogoutButtonClick() {
        // Clear user data and  call below lines
        User user = new User(1, "Virendra", "Pune,India");
        // Basically, you post an event as sticky with .postSticky() on your Activity B and when Activity A is registered again,
        // it will automatically receive that sticky event.
        EventBus.getDefault().postSticky(new LogOutEvent(user));
        startActivity(new Intent(this, Main2Activity.class));
    }

    // get Event in same activity/Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent event) {
        User user = event.getUser();
        Toast.makeText(this, "Logged in user in MainActivity= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
    }

    // get Event in same activity/Fragment
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LogOutEvent event) {
        User user = event.getUser();
        Toast.makeText(this, "Logged out user in MainActivity= " + user.getName() + " - " + user.getAddress(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
