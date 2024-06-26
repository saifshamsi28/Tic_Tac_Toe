package com.example.tic_tac_toe;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog {
    private Dialog dialog;
    private Context context;
    private TextView title, congratulate, winnerName,winnerTag;
    private MainActivity2 mainActivity2;

    public CustomDialog(Context context, MainActivity2 mainActivity2) {
        this.context = context;
        this.mainActivity2 = mainActivity2;
    }

    public void show(String winner) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.getWindow().setBackgroundDrawable(null);
        dialog.setCancelable(true);

        title = dialog.findViewById(R.id.custom_title);
        congratulate = dialog.findViewById(R.id.congratulationTextview);
        winnerName = dialog.findViewById(R.id.winner_name);
        winnerTag=dialog.findViewById(R.id.winner_tag);
        Button closeButton = dialog.findViewById(R.id.custom_button);

        Animation winner_title_anim = AnimationUtils.loadAnimation(dialog.getContext(), R.anim.winner_tag_anim);
        Animation winner_tag =AnimationUtils.loadAnimation(dialog.getContext(), R.anim.winner_title);
        Animation winner_name =AnimationUtils.loadAnimation(dialog.getContext(), R.anim.winner_name);

        if(!winner.contains("drawn")) {
            title.setText("🥳 Winner! 🥳");
            congratulate.setText("Congratulations 🎉🥳");
            winnerName.setText(winner);
            winnerTag.setVisibility(View.VISIBLE);
            title.startAnimation(winner_title_anim);
            congratulate.startAnimation(winner_tag);
            winnerName.startAnimation(winner_name);
            winnerTag.startAnimation(winner_title_anim);
        }else {
            title.setVisibility(View.GONE);
            congratulate.setText("Oops😢");
            winnerName.setText(winner);
            winnerTag.setVisibility(View.GONE);
            congratulate.startAnimation(winner_title_anim);
            winnerName.startAnimation(winner_tag);
            winnerName.startAnimation(winner_name);
        }
        winnerName.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        winnerName.setSingleLine(true);
        winnerName.setSelected(true);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity2.restartGame(v);
                dismiss();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }
        dialog.show();
    }
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
