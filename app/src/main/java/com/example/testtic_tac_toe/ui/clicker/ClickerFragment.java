package com.example.testtic_tac_toe.ui.clicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtic_tac_toe.R;
import com.example.testtic_tac_toe.databinding.ClickerFragmentBinding;

public class ClickerFragment extends Fragment {

    private ClickerViewModel viewModel;
    private ClickerFragmentBinding binding;

    public static ClickerFragment newInstance() {
        return new ClickerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.clicker_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(ClickerViewModel.class);
        viewModel.getIdImage().observe(getViewLifecycleOwner(), it -> {
            if (it == 1) {
                binding.imageView.setImageResource(R.drawable.image_apple);
            } else {
                binding.imageView.setImageResource(R.drawable.image_bomb);
            }
        });

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}