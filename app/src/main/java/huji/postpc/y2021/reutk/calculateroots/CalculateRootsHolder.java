package huji.postpc.y2021.reutk.calculateroots;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalculateRootsHolder extends RecyclerView.ViewHolder{

    TextView explanation;
    ProgressBar progress;
    Button delete;
    Button cancle;

    public CalculateRootsHolder(@NonNull View itemView) {
        super(itemView);
        explanation = itemView.findViewById(R.id.todo_text);
        progress = itemView.findViewById(R.id.checkBox);
        delete = itemView.findViewById(R.id.remove_button);
        cancle = itemView.findViewById(R.id.update_button);
        }
}

