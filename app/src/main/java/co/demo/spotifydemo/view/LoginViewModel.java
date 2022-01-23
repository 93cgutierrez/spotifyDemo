package co.demo.spotifydemo.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.List;

import co.demo.spotifydemo.MainActivity;
import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.FragmentLoginBinding;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.util.SingleLiveEvent;
import co.demo.spotifydemo.util.UtilPreference;
import co.demo.spotifydemo.viewmodel.ArtistViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getCanonicalName();
    private final CompositeDisposable disposables = new CompositeDisposable();

    //loading
    private SingleLiveEvent<Boolean> isViewLoading = new SingleLiveEvent<>();
    private Artist artist;
    private NavController navController;

    public LiveData<Boolean> isViewLoading() {
        if (isViewLoading == null) {
            isViewLoading = new SingleLiveEvent<>();
        }
        return isViewLoading;
    }

    //data list<Artist>
    private SingleLiveEvent<Artist> artistResult = new SingleLiveEvent<>();

    public LiveData<Artist> getArtist() {
        if (artistResult == null) {
            artistResult = new SingleLiveEvent<>();
        }
        return artistResult;
    }

    //empty
    private SingleLiveEvent<Boolean> isEmptyArtistList = new SingleLiveEvent<>();

    public LiveData<Boolean> isEmptyArtistList() {
        if (isEmptyArtistList == null) {
            isEmptyArtistList = new SingleLiveEvent<>();
        }
        return isEmptyArtistList;
    }

    //Error
    private SingleLiveEvent<String> onMessageError = new SingleLiveEvent<>();

    public LiveData<String> getOnMessageError() {
        if (onMessageError == null) {
            onMessageError = new SingleLiveEvent<>();
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

    public void validateToken(FragmentActivity context, View viewContext) {
        if(UtilPreference.getToken(context) != null) {
            goToArtistFragment(viewContext);
        }
    }

    public void goToArtistFragment(View viewContext) {
        //ir a pantalla principal
        if (navController == null) {
            navController = Navigation.findNavController(viewContext);
        }
        navController.navigate(R.id.action_loginFragment_to_artistFragment);
    }
}