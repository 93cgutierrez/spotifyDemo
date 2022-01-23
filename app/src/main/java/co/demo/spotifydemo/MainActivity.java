package co.demo.spotifydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blongho.country_data.World;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.Objects;

import co.demo.spotifydemo.databinding.ActivityMainBinding;
import co.demo.spotifydemo.util.Parameters;
import co.demo.spotifydemo.util.UtilPreference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private View viewContext;
    private ActivityMainBinding binding;
    private String mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewContext = binding.getRoot();
        setContentView(viewContext);
        setSupportActionBar(binding.tbToolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        //up level
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.loginFragment, R.id.artistFragment)
                        .build();
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.tbToolbar, navController, appBarConfiguration);
        binding.tbToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            Log.d(TAG, "addOnDestinationChangedListener: " + destination);
/*                if(destination.getId() == R.id.full_screen_destination) {
                toolbar.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                toolbar.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            }*/
        });
        World.init(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);
        if (Parameters.AUTH_TOKEN_REQUEST_CODE == requestCode) {
            mAccessToken = response.getAccessToken();
            UtilPreference.saveToken(this, mAccessToken);
            updateTokenView();
            for (Fragment fragment : Objects.requireNonNull(getSupportFragmentManager().getPrimaryNavigationFragment())
                    .getChildFragmentManager().getFragments())
            {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void updateTokenView() {
        Toast.makeText(this, "token: " + mAccessToken, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        super.onBackPressed();
    }
}