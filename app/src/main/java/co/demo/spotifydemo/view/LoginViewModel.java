package co.demo.spotifydemo.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.model.data.Artist;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.util.SingleLiveEvent;
import co.demo.spotifydemo.util.UtilPreference;
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

    //data token
    private SingleLiveEvent<String> tokenResult = new SingleLiveEvent<>();

    public LiveData<String> getTokenResult() {
        if (tokenResult == null) {
            tokenResult = new SingleLiveEvent<>();
        }
        return tokenResult;
    }

    //Error
    private SingleLiveEvent<String> onMessageError = new SingleLiveEvent<>();

    public LiveData<String> getOnMessageError() {
        if (onMessageError == null) {
            onMessageError = new SingleLiveEvent<>();
        }
        return onMessageError;
    }


    public void onAuthorizeClick(FragmentActivity activity) {
        isViewLoading.postValue(true);
        final AuthorizationRequest request = getAuthenticationRequest(AuthorizationResponse.Type.TOKEN);
        AuthorizationClient.openLoginActivity(activity, Parameters.AUTH_TOKEN_REQUEST_CODE, request);
    }

    public void onClearCredentialsClick(Context context) {
        isViewLoading.postValue(true);
        UtilPreference.clearPreferences(context);
        AuthorizationClient.clearCookies(context);
        isViewLoading.postValue(false);
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

    public void validateFlowToken(FragmentActivity context, int resultCode, Intent data) {
        isViewLoading.postValue(false);
        final AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);
        if (Parameters.AUTH_TOKEN_REQUEST_CODE == resultCode && response != null && response.getAccessToken() != null) {
            tokenResult.postValue(response.getAccessToken());
        } else {
            onMessageError.postValue(context.getString(R.string.msg_error_token_permission));
        }
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}