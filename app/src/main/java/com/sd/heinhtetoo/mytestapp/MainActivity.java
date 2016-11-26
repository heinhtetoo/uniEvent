package com.sd.heinhtetoo.mytestapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.sd.heinhtetoo.mytestapp.data.Event;
import com.sd.heinhtetoo.mytestapp.data.Model.EventModelImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import de.greenrobot.event.EventBus;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity implements EventContract.eventView,NavigationView.OnNavigationItemSelectedListener,UserController {
    ArrayList<Event> events;
    private RecyclerView recyclerViewEvent;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPodAccountControl vpAccountControl;

    private CallbackManager mCallbackManager;
    private AccessTokenTracker mAccessTokenTracker;

    private String username;
    private String email;
    private String profileURL;
    private String profileCoverURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Menu leftMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        vpAccountControl = (ViewPodAccountControl) navigationView.getHeaderView(0);
        vpAccountControl.setUserController(this);

        mCallbackManager = CallbackManager.Factory.create();
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken == null){
                    Log.d(UniEventApp.TAG,"Logout from Facebook");
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() != null){
            username = Profile.getCurrentProfile().getFirstName() +""+ Profile.getCurrentProfile().getMiddleName()+""+Profile.getCurrentProfile().getLastName();
            profileURL = Profile.getCurrentProfile().getProfilePictureUri(500,500).toString();
            setUserProfile();
            isUserLogin = true;

        }
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        processFacebookInfo(loginResult);
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

        recyclerViewEvent = (RecyclerView) findViewById(R.id.view_event);


        EventContract.eventPresenter eventPresenter = new EventPresenter(EventModelImpl.getInstance(), this);

        eventPresenter.initPresenter();


    }

    private void processFacebookInfo(LoginResult loginResult) {

        final AccessToken accessToken = loginResult.getAccessToken();


        FacebookUtils.getInstance().requestFacebookLoginUser(accessToken, new FacebookUtils.FacebookGetLoginUserCallback() {
            @Override
            public void onSuccess(final JSONObject facebookLoginUser) {
                FacebookUtils.getInstance().requestFacebookProfilePhoto(accessToken, new FacebookUtils.FacebookGetPictureCallback() {
                    @Override
                    public void onSuccess(final String profilePhotoUrl) {
                        FacebookUtils.getInstance().requestFacebookCoverPhoto(accessToken, new FacebookUtils.FacebookGetPictureCallback() {
                            @Override
                            public void onSuccess(final String coverPhotoUrl) {

                                onLoginWithFacebook(facebookLoginUser, profilePhotoUrl, coverPhotoUrl);


                            }
                        });
                    }
                });
            }
        });

    }

    private void onLoginWithFacebook(JSONObject facebookLoginUser, String imageUrl, String coverImageUrl ){

        try {

            if (facebookLoginUser.has("name")){
                username = facebookLoginUser.getString("name");
            }

            if (facebookLoginUser.has("email")){
                email = facebookLoginUser.getString("email");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        profileURL = imageUrl;
        profileCoverURL = coverImageUrl;

        //Glide.with(ivProfile.getContext()).load(imageUrl).centerCrop().crossFade().error(R.mipmap.ic_launcher).into(ivProfile);
        //Glide.with(ivProfileCover.getContext()).load(coverImageUrl).centerCrop().crossFade().error(R.mipmap.ic_launcher).into(ivProfileCover);
       setUserProfile();
        isUserLogin = true;
    }

    private void setUserProfile() {
        EventBus.getDefault().postSticky(new UserVO(username,email,profileURL,profileCoverURL));
        DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
        EventBus.getDefault().post(event);
    }

    private void connectToFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(FacebookUtils.FACEBOOK_LOGIN_PERMISSIONS));
        } else {
            LoginManager.getInstance().logOut();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAccessTokenTracker.startTracking();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAccessTokenTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public void showList(RealmList<Event> eList) {
        final EventsAdapter adapter = new EventsAdapter(this, eList);

        recyclerViewEvent.setAdapter(adapter);
        recyclerViewEvent.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void hideList() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();

        return true;
    }

    boolean isUserLogin = false;
    @Override
    public void onTapLogin() {
        if(!isUserLogin) {
            connectToFacebook();

        }
    }

    @Override
    public void onTapLogout() {
        if(isUserLogin) {
            EventBus.clearCaches();
            DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
            EventBus.getDefault().post(event);

            isUserLogin = false;
        }
    }
}
