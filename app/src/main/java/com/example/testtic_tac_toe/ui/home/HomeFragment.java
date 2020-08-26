package com.example.testtic_tac_toe.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtic_tac_toe.R;
import com.example.testtic_tac_toe.api.AppService;
import com.example.testtic_tac_toe.api.model.FlagDTO;
import com.example.testtic_tac_toe.databinding.HomeFragmentBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private HomeFragmentBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewModel(viewModel);

        CookieManager.getInstance().setAcceptCookie(true);
        binding.webView.loadUrl("https://html5test.com/");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        binding.webView.setVisibility(View.GONE);
        binding.textViewError.setVisibility(View.GONE);
        binding.fragmentGame.setVisibility(View.GONE);

        AppService.getInstance()
                .getDataApi()
                .getFlag()
                .enqueue(new Callback<FlagDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<FlagDTO> call, @NonNull Response<FlagDTO> response) {

                        FlagDTO flag = response.body();

                        if (flag.getFlag()) {
                            if (savedInstanceState != null)
                                binding.webView.restoreState(savedInstanceState);
                            binding.webView.setVisibility(View.VISIBLE);
                        } else {
                            binding.fragmentGame.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<FlagDTO> call, @NonNull Throwable t) {
                        binding.textViewError.setText("Error");
                        t.printStackTrace();
                    }
                });
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.webView.saveState(outState);
    }
}