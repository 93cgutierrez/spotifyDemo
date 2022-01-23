package co.demo.spotifydemo.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.List;

import co.demo.spotifydemo.MainActivity;
import co.demo.spotifydemo.databinding.FragmentLoginBinding;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.util.UtilPreference;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getCanonicalName();
    private final CompositeDisposable disposables = new CompositeDisposable();

    //loading
    private MutableLiveData<Boolean> isViewLoading = new MutableLiveData<>();
    private Artist artist;

    public LiveData<Boolean> isViewLoading() {
        if (isViewLoading == null) {
            isViewLoading = new MutableLiveData<>();
        }
        return isViewLoading;
    }

    //data list<Artist>
    private MutableLiveData<Artist> artistResult = new MutableLiveData<>();

    public LiveData<Artist> getArtist() {
        if (artistResult == null) {
            artistResult = new MutableLiveData<>();
        }
        return artistResult;
    }

    //empty
    private MutableLiveData<Boolean> isEmptyArtistList = new MutableLiveData<>();

    public LiveData<Boolean> isEmptyArtistList() {
        if (isEmptyArtistList == null) {
            isEmptyArtistList = new MutableLiveData<>();
        }
        return isEmptyArtistList;
    }

    //Error
    private MutableLiveData<String> onMessageError = new MutableLiveData<>();

    public LiveData<String> getOnMessageError() {
        if (onMessageError == null) {
            onMessageError = new MutableLiveData<>();
        }
        return onMessageError;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public void onAuthorizeClick(FragmentActivity activity) {
        final AuthorizationRequest request = getAuthenticationRequest(AuthorizationResponse.Type.TOKEN);
        AuthorizationClient
                //.openLoginInBrowser(activity, request);
                .openLoginActivity(activity, Parameters.AUTH_TOKEN_REQUEST_CODE, request);
    }

    public void onClearCredentialsClick(Context context) {
        UtilPreference.clearPreferences(context);
        AuthorizationClient.clearCookies(context);
    }


    private AuthorizationRequest getAuthenticationRequest(AuthorizationResponse.Type type) {
        return new AuthorizationRequest.Builder(Parameters.CLIENT_ID, type, getRedirectUri().toString())
                .setShowDialog(true)
                .setScopes(new String[]{})
                .build();
    }

    private Uri getRedirectUri() {
        return Uri.parse(Parameters.REDIRECT_URI);
    }
}