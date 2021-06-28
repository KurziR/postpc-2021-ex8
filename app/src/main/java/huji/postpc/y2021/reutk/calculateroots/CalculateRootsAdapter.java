package huji.postpc.y2021.reutk.calculateroots;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateRootsAdapter extends RecyclerView.Adapter<CalculateRootsHolder>{


    private final List<Calculation> calcs = new ArrayList<Calculation>();

//    public CalculateRootsAdapter() {
//        this.calcs;
//    }

    @NonNull
    @Override
    public CalculateRootsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calc_row, parent, false);
        return new CalculateRootsHolder(view, view.getContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CalculateRootsHolder holder, int position) {
        Calculation calculation = calcs.get(position);
        holder.gray_box.setVisibility(View.VISIBLE);
        holder.explanation.setVisibility(View.VISIBLE);

        if(calculation.getStatus() == Calculation.Status.IN_PROGRESS) {
            holder.progress.setVisibility(View.VISIBLE);
            holder.cancel.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);
            holder.explanation.setText("Calculating roots for: " + calculation.getNemToCalc() + "...");
        } else {
            holder.progress.setVisibility(View.INVISIBLE);
            holder.cancel.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.VISIBLE);
            // if the number is a prime number
            if(calculation.roots.length == 1) {
                holder.explanation.setText(calculation.getNemToCalc() + " is a prime number!");
            } else {
                holder.explanation.setText("Roots for " + calculation.getNemToCalc() + ": " + calculation.getRoots()[0] + "X " + calculation.getRoots()[1]);
            }
        }
    }

    @Override
    public int getItemCount() {
        return calcs.size();
    }

    public void addCalc(Calculation calc) {
        calcs.add(calc);
        Collections.sort(calcs);
    }
}
