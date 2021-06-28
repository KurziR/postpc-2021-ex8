package huji.postpc.y2021.reutk.calculateroots;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CalculateRootsHolder extends RecyclerView.ViewHolder {

    View gray_box;
    TextView explanation;
    ProgressBar progress;
    Button delete;
    Button cancel;


    public CalculateRootsHolder(@NonNull View itemView, Context context) {
        super(itemView);
        gray_box = itemView.findViewById(R.id.gray_box);
        explanation = itemView.findViewById(R.id.calc_explanation);
        progress = itemView.findViewById(R.id.calc_progress);
        delete = itemView.findViewById(R.id.delete_button);
        cancel = itemView.findViewById(R.id.cancel_button);
    }
}

