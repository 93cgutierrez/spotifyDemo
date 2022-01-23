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

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.LoginFragmentBinding;
import co.demo.spotifydemo.util.PreferenceUtil;
import co.demo.spotifydemo.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getCanonicalName();
    private LoginFragmentBinding binding;
    private View viewContext;
    private LoginViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding
                .inflate(inflater, container, false);
        return binding.getRoot();
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
        if (binding != null && viewContext != null) {
            initUI();
            subscribeEvents();
        }
    }

    private void initUI() {
        mViewModel.validateToken(requireActivity(), viewContext);
        binding.btnAuthorize.setOnClickListener(v -> mViewModel.onAuthorizeClick(requireActivity()));
    }

    private void subscribeEvents() {
        mViewModel.isViewLoading().observe(getViewLifecycleOwner(), this::isLoadingObserver);
        mViewModel.getOnMessageError().observe(getViewLifecycleOwner(), this::getOnMessageErrorObserver);
        mViewModel.getTokenResult().observe(getViewLifecycleOwner(), this::getTokenObserver);
    }

    private void getTokenObserver(String token) {
        PreferenceUtil.saveToken(requireActivity(),token);
        Toast.makeText(requireActivity(), R.string.msg_token_ok, Toast.LENGTH_SHORT).show();
        mViewModel.goToArtistFragment(viewContext);
    }

    private void getOnMessageErrorObserver(String errorMessage) {
        Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_LONG).show();
    }

    private void isLoadingObserver(Boolean showLoading) {
            binding.cpiLoading.setVisibility(showLoading ? View.VISIBLE :  View.GONE);
            binding.tvAutorizedText.setVisibility(showLoading ? View.GONE : View.VISIBLE);
            binding.btnAuthorize.setEnabled(!showLoading);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.validateFlowToken(requireActivity(), requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}