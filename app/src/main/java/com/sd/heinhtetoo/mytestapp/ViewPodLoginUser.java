package com.sd.heinhtetoo.mytestapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 9/10/2016.
 */
public class ViewPodLoginUser extends RelativeLayout implements ViewController {

    @BindView(R.id.iv_profile_cover)
    ImageView ivProfileCover;

    @BindView(R.id.iv_profile)
    ImageView ivProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    private UserController mController;

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
        ButterKnife.bind(this, this);
    }

    @Override
    public void setController(BaseController controller) {
        mController = (UserController) controller;
    }

    @OnClick(R.id.btn_logout)
    public void onTapLogout(Button btnLogout) {
        mController.onTapLogout();
    }

    public void setData(String username,String email,String profileURL,String profileCoverURL) {

        Glide.with(getContext())
                .load(profileCoverURL)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.drawer_background)
                .error(R.drawable.drawer_background)
                .into(ivProfileCover);

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


        tvName.setText(username);
        tvEmail.setText(email);
    }
}
