
package com.example.nein37.fragmenthandlerdemo;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class ToFragment extends BaseHandlerFragment {

    public static final String BUNDLE_TOAST_text = "toast_text";
    public static final int WHAT_POP_BACKSTACK = 42;

    public ToFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_to, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2秒後に前のFragmentに戻る
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // このタイミングでFragment操作を行うと落ちる
                        sendMessage(WHAT_POP_BACKSTACK);
                    }
                }, 2000);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(BUNDLE_TOAST_text)) {
            // 指定文字列がある場合、Toastを表示
            Toast.makeText(getActivity(), getArguments().getString(BUNDLE_TOAST_text),
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void processMessage(Message message) {
        switch (message.what) {
            case WHAT_POP_BACKSTACK:
                // bundleも取り出す
                getFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }
}
