package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import de.greenrobot.event.EventBus;

/**
 * Created by user on 9/10/2016.
 */
public class ViewPodAccountControl extends FrameLayout {

    private String username;
    private String email;
    private String profileURL;
    private String profileCoverURL;

    private ViewPodLoginUser vpLoginUser;

    private ViewPodLogoutUser vpLogoutUser;

    public ViewPodAccountControl(Context context) {
        super(context);
    }

    public ViewPodAccountControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodAccountControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        vpLoginUser = (ViewPodLoginUser) findViewById(R.id.vp_login_user);
        vpLogoutUser = (ViewPodLogoutUser) findViewById(R.id.vp_logout_user);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public void onEvent(UserVO data){
        username = data.getUsername();
        email = data.getEmail();
        profileURL = data.getProfile();
    }

    boolean isUserLogin = false;
    private void refreshUserLoginStatus() {
        isUserLogin = isUserLogin ? false : true;
        vpLogoutUser.setVisibility(isUserLogin ? View.GONE : View.VISIBLE);
        vpLoginUser.setVisibility(isUserLogin ? View.VISIBLE : View.GONE);

        if(isUserLogin)
            vpLoginUser.setData(username,email,profileURL);

    }

    public void setUserController(UserController userController) {
        vpLogoutUser.setController(userController);
        vpLoginUser.setController(userController);
    }

    public void onEventMainThread(DataEvent.RefreshUserLoginStatusEvent event) {
        refreshUserLoginStatus();
    }
}
