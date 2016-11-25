package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by user on 9/10/2016.
 */
public class ViewPodAccountControl extends FrameLayout {

    private String username;
    private String email;
    private String profileURL;
    private String profileCoverURL;

    @BindView(R.id.vp_login_user)
    ViewPodLoginUser vpLoginUser;

    @BindView(R.id.vp_logout_user)
    ViewPodLogoutUser vpLogoutUser;

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
        ButterKnife.bind(this, this);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public void onEvent(UserVO data){
        username = data.getUsername();
        email = data.getEmail();
        profileURL = data.getProfile();
        profileCoverURL = data.getProfileCover();
    }

    boolean isUserLogin = false;
    private void refreshUserLoginStatus() {
        isUserLogin = isUserLogin ? false : true;
        vpLogoutUser.setVisibility(isUserLogin ? View.GONE : View.VISIBLE);
        vpLoginUser.setVisibility(isUserLogin ? View.VISIBLE : View.GONE);

        if(isUserLogin)
            vpLoginUser.setData(username,email,profileURL,profileCoverURL);

    }

    public void setUserController(UserController userController) {
        vpLogoutUser.setController(userController);
        vpLoginUser.setController(userController);
    }

    public void onEventMainThread(DataEvent.RefreshUserLoginStatusEvent event) {
        refreshUserLoginStatus();
    }
}
