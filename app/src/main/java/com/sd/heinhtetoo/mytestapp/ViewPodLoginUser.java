package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


/**
 * Created by user on 9/10/2016.
 */
public class ViewPodLoginUser extends RelativeLayout implements ViewController,View.OnClickListener {

    private ImageView ivProfile;

    private TextView tvName;

    private TextView tvEmail;

    private UserController mController;

    private Button btnLogout;

    public ViewPodLoginUser(Context context) {
        super(context);
    }

    public ViewPodLoginUser(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodLoginUser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivProfile = (ImageView) findViewById(R.id.iv_profile);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void setController(BaseController controller) {
        mController = (UserController) controller;
    }

    public void setData(String username,String email,String profileURL) {

        Glide.with(getContext())
                .load(profileURL)
                .asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.dummy_avatar)
                .error(R.drawable.dummy_avatar)
                .into(new BitmapImageViewTarget(ivProfile) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(ivProfile.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivProfile.setImageDrawable(circularBitmapDrawable);
            }
        });


        if(username != null ){
        tvName.setText(username);
        }
        if(email != null)
        {
            tvEmail.setText(email);
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_logout) {
            onTapLogout();
        }
    }

    public void onTapLogout (){
        mController.onTapLogout();
    }
}
