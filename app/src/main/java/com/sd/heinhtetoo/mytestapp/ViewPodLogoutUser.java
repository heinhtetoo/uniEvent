package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


/**
 * Created by user on 9/10/2016.
 */
public class ViewPodLogoutUser extends RelativeLayout implements ViewController,View.OnClickListener {

    private Button btnLogin;

    private UserController mController;

    public ViewPodLogoutUser(Context context) {
        super(context);
    }

    public ViewPodLogoutUser(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodLogoutUser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    public void onTapLogin() {
        mController.onTapLogin();
    }

    @Override
    public void setController(BaseController controller) {
        mController = (UserController) controller;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_login){
            onTapLogin();
        }
    }
}
