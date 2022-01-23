package co.demo.spotifydemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.FragmentLoginBinding;
import co.demo.spotifydemo.util.UtilPreference;

public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getCanonicalName();
    private FragmentLoginBinding binding;
    private View viewContext;
    private LoginViewModel mViewModel;
    private NavController navController;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding
                .inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewContext = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        requireActivity().setTitle("Login");
        if (binding != null && viewContext != null) {
            initUI();
            subscribeEvents();
        }
    }

    private void initUI() {
        mViewModel.validateToken(requireActivity(), viewContext);
        binding.btnAuthorize.setOnClickListener(v -> mViewModel.onAuthorizeClick(requireActivity()));
        binding.btnClearCache.setOnClickListener(v -> mViewModel.onClearCredentialsClick(getContext()));
    }

    private void subscribeEvents() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(requireActivity(), "token: login" , Toast.LENGTH_SHORT).show();
        mViewModel.goToArtistFragment(viewContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}