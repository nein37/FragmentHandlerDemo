
package com.example.nein37.fragmenthandlerdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseHandlerFragment extends Fragment {

    public ConcreteTestHandler handler = new ConcreteTestHandler();

    public void sendMessage(int what) {
        this.sendMessage(what, null);
    }

    public void sendMessage(int what, Bundle bundle) {
        Message message = handler.obtainMessage(what);
        if (bundle != null) {
            message.setData(bundle);
        }
        handler.sendMessage(message);
    }

    /**
     * Messageの内容によって処理を行う
     * 
     * @param message
     */
    public abstract void processMessage(Message message);

    @Override
    public void onPause() {
        super.onPause();
        handler.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.setFragment(this);
        handler.resume();
    }

    /**
     * Message Handler class that supports buffering up of messages when the
     * activity is paused i.e. in the background.
     */
    static class ConcreteTestHandler extends PauseHandler {

        /**
         * Activity instance
         */
        protected BaseHandlerFragment fragment;

        final void setFragment(BaseHandlerFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        final protected boolean storeMessage(Message message) {
            // All messages are stored by default
            return true;
        };

        @Override
        final protected void processMessage(Message msg) {
            if (fragment != null) {
                fragment.processMessage(msg);
            }
        }
    }
}
