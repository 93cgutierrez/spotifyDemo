package co.demo.spotifydemo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import co.demo.spotifydemo.R;
import co.demo.spotifydemo.databinding.DialogLayoutBinding;

public class DialogUtil {

    public static final AlertDialog showErrorDialog(Activity context,
                                                    boolean cancelable,
                                                    View.OnClickListener listener,
                                                    boolean isInternetError) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Get the layout inflater
        LayoutInflater inflater = context.getLayoutInflater();
        // Inflar y establecer el layout para el dialogo
        // Pasar nulo como vista principal porque va en el diseño del diálogo
        View v = inflater.inflate(R.layout.dialog_layout, null);
        TextView tv_title_dialog = v.findViewById(R.id.tv_title_dialog);
        ImageView iv_icon_dialog = v.findViewById(R.id.iv_icon_dialog);
        TextView tv_message_dialog = v.findViewById(R.id.tv_message_dialog);
        Button btn_ok_dialog = v.findViewById(R.id.btn_ok_dialog);
        ImageView iv_close_dialog = v.findViewById(R.id.iv_close_dialog);
        builder.setView(v);
        builder.setCancelable(cancelable);
        alertDialog = builder.create();
        if(isInternetError){
            iv_icon_dialog.setImageResource(R.drawable.ic_baseline_network_check_24);
        } else {
            iv_icon_dialog.setImageResource(R.drawable.ic_baseline_error_24);
        }
        if(isInternetError) {
            tv_title_dialog.setText(R.string.title_network_error_dialog);
        } else {
            tv_title_dialog.setText(R.string.title_general_error_dialog);
        }
        tv_title_dialog.setVisibility(View.VISIBLE);
        if(isInternetError) {
            tv_message_dialog.setText(R.string.msg_network_error);
        } else {
            tv_message_dialog.setText(R.string.message_general_error_dialog);
        }
        tv_message_dialog.setVisibility(View.VISIBLE);
        if(isInternetError && listener != null) {
            btn_ok_dialog.setVisibility(View.VISIBLE);
            btn_ok_dialog.setText(R.string.title_btn_retry);
            btn_ok_dialog.setOnClickListener(listener);
        } else {
            btn_ok_dialog.setVisibility(View.INVISIBLE);
            btn_ok_dialog.setText("");
        }
        iv_close_dialog.setOnClickListener(v1 -> alertDialog.dismiss());
        if(!alertDialog.isShowing()) {
            alertDialog.show();
        }
        return alertDialog;
    }
}
