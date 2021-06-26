package huji.postpc.y2021.reutk.calculateroots;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateRootsAdapter extends RecyclerView.Adapter<CalculateRootsHolder>{


    private final List<Calculation> calcs;

    public CalculateRootsAdapter() {
        this.calcs = new ArrayList<Calculation>();
    }

    @NonNull
    @Override
    public CalculateRootsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calc_row, parent, false);
        return new CalculateRootsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculateRootsHolder holder, int position) {
        Calculation calculation = calcs.get(position);
        if(calculation.getStatus() == Calculation.Status.IN_PROGRESS) {
            holder.progress.setVisibility(View.VISIBLE);
            holder.cancle.setVisibility(View.VISIBLE);
            holder.delete.setVisibility(View.INVISIBLE);

        } else {
            //TODO if calculation.roots.size() == 1 (prime):
            holder.progress.setVisibility(View.INVISIBLE);
            holder.cancle.setVisibility(View.INVISIBLE);
            holder.delete.setVisibility(View.VISIBLE);

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
