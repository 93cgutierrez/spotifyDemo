package co.demo.spotifydemo.util;

import android.os.Handler;
import android.widget.SearchView;

public abstract class DelayedOnQueryTextListener implements SearchView.OnQueryTextListener {
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        handler.removeCallbacks(runnable);
        runnable = () -> onDelayerQueryTextChange(s);
        handler.postDelayed(runnable, Parameters.DELAY_ON_QUERY_TEXT_CHANGE);
        return true;
    }

    public abstract void onDelayerQueryTextChange(String query);
}