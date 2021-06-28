package huji.postpc.y2021.reutk.calculateroots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MainActivity extends AppCompatActivity {

    private CalculateRootsHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerCalcRootsList = findViewById(R.id.CalcListView);
        holder = new CalculateRootsHolder(recyclerCalcRootsList, this);

        CalculateRootsAdapter adapter = new CalculateRootsAdapter();
        recyclerCalcRootsList.setAdapter(adapter);
        recyclerCalcRootsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        Button buttonCalcRoots = findViewById(R.id.buttonCalculateRoots);
        EditText numToCalc = findViewById(R.id.numToCalc);

        CalculateRootsHolder data = new CalculateRootsHolder(recyclerCalcRootsList, this);


        buttonCalcRoots.setOnClickListener(v -> {
            String userInputString = numToCalc.getText().toString();
            if (!isValidInput(userInputString)) {
                Toast toast = Toast.makeText(this, "Please insert a positive number", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            long userInputLong = Long.parseLong(userInputString);
            Calculation calc = new Calculation(userInputLong);
            adapter.addCalc(calc);
//            data.addNewItem(userInputLong, userInputString);
            numToCalc.setText(""); // cleanup text in edit-text
//            requireNonNull(recyclerCalcRootsList.getAdapter()).notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        });

        WorkManager workManager = WorkManager.getInstance(this);
        MyRootsApp app = MyRootsApp.getInstance();

//        LiveData<List<WorkInfo>> all_works_live_data = workManager.getWorkInfosLiveData();

//        all_works_live_data.observe(this, new Observer<List<WorkInfo>>() {
//            @Override
//            public void onChanged(List<WorkInfo> workInfos) {
//                for (WorkInfo workInfo : workInfos) {
//                    if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
//                        Data outputData = workInfo.getOutputData();
//                        holder.calcSucceeded(outputData.getLong("numToCalc", -1),
//                                outputData.getLongArray("roots");
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        float progress = workInfo.getProgress().getFloat("progress_percent", (float) 0);
//                        Calculation calc = holder.getItem(workInfo.getProgress().getLong("org_num", -1));
//                        if (calc != null) {
//                            calc.setCalculationProgress(progress);
//                            adapter.notifyItemChanged(calc);
//                        }
//                    }
//                }
//            }
//        });
    }

    private boolean isValidInput(String userInputString) {
        return userInputString.length() > 0 && Long.parseLong(userInputString) > 0;
    }
}


