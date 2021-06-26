package huji.postpc.y2021.reutk.calculateroots;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CalculateRootsWorker extends Worker {

    public CalculateRootsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        for(int i = 2; i < numberToCalculateRootsFor; i++) {
            if (numberToCalculateRootsForc % i == 0) {
                long root1 = i;
                long root2 = (numberToCalculateRootsFor / i);
                rootIntent.setAction("found_roots");
                rootIntent.putExtra("original_number", numberToCalculateRootsFor);
                rootIntent.putExtra("root1", root1);
                rootIntent.putExtra("root2", root2);
                sendBroadcast(rootIntent);
                return Result.success(i);
            }
        }
    }
}
