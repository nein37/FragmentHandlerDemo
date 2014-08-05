
package com.example.nein37.fragmenthandlerdemo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class FromFragment extends BaseHandlerFragment {

    public static final int WHAT_REPLACE_DIALOG = 37;

    public FromFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_from, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2秒後に画面遷移を行う
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // このタイミングでFragment操作を行うと落ちる
                        Bundle bundle = new Bundle();
                        bundle.putString(ToFragment.BUNDLE_TOAST_text,"hogehogehogehoge");
                        sendMessage(WHAT_REPLACE_DIALOG, bundle);
                    }
                }, 2000);
            }
        });

        return rootView;
    }

    @Override
    public void processMessage(Message message) {
        switch (message.what) {
            case WHAT_REPLACE_DIALOG:
                // bundleも取り出す
                ToFragment fragment = new ToFragment();
                fragment.setArguments(message.getData());
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(getId(), fragment);
                ft.addToBackStack("hoge");
                ft.commit();
                break;
            default:
                break;
        }
    }
}
