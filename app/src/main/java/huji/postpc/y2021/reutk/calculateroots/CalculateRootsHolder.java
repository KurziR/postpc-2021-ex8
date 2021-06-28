package huji.postpc.y2021.reutk.calculateroots;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static huji.postpc.y2021.reutk.calculateroots.Calculation.Status.DONE;
import static huji.postpc.y2021.reutk.calculateroots.Calculation.Status.IN_PROGRESS;

public class CalculateRootsHolder extends RecyclerView.ViewHolder {

    View gray_box;
    TextView explanation;
    ProgressBar progress;
    Button delete;
    Button cancel;

    public List<Calculation> numbers;
    private final SharedPreferences sp;

    private final MutableLiveData<List<Calculation>> listLiveDataMutable;
    public final LiveData<List<Calculation>> listLiveDataPublic;

    public CalculateRootsHolder(@NonNull View itemView, Context context) {
        super(itemView);
        gray_box = itemView.findViewById(R.id.gray_box);
        explanation = itemView.findViewById(R.id.calc_explanation);
        progress = itemView.findViewById(R.id.calc_progress);
        delete = itemView.findViewById(R.id.delete_button);
        cancel = itemView.findViewById(R.id.cancel_button);

        listLiveDataMutable = new MutableLiveData<>();
        listLiveDataPublic = listLiveDataMutable;

        this.sp = context.getSharedPreferences("local_db_todo", Context.MODE_PRIVATE);
        initializeFromSp();
    }

    private void initializeFromSp() {
        Set<String> keys = sp.getAll().keySet();
        for (String key:keys) {
            if (sp.getString(key, null) == null){
                return;
            }
            Calculation calc = Calculation.stringToCalc(sp.getString(key, null));
            numbers.add(0, calc);
            Collections.sort(numbers);
            listLiveDataMutable.setValue(numbers);
        }
    }

    public void addNewItem(long number, String num) {
        Calculation calc = new Calculation(number);
        numbers.add(0, calc); // add the item in the beginning of the list
        Collections.sort(numbers);
        String calcToString = calc.calcToString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(num, calcToString);
        editor.apply();
        listLiveDataMutable.setValue(this.numbers);
    }

    public void markItemDone(Calculation calc) {
        calc.setStatus(DONE);
        Collections.sort(numbers);
        updateList(calc);
    }

    public void markItemInProgress(Calculation calc) {
        calc.setStatus(IN_PROGRESS);
        Collections.sort(numbers);
        updateList(calc);
    }

    public void deleteItem(Calculation calc) {
        numbers.remove(calc);
        Collections.sort(numbers);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(calc.getRequestId());
        editor.apply();
        listLiveDataMutable.setValue(this.numbers);
    }


    public void updateList (Calculation calc) {
        String toDoToString = calc.calcToString();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(calc.getRequestId(), toDoToString);
        editor.apply();
        listLiveDataMutable.setValue(this.numbers);
    }

    public void calcSucceeded(long org_num, long root1, long root2) {
    }
}

