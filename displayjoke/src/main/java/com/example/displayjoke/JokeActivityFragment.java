package com.example.displayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_activity, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);
        TextView jokeTextView = (TextView) root.findViewById(R.id.joke_textview);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

        return root;
    }
}
