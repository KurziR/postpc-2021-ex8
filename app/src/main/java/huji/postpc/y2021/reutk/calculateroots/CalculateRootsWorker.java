package huji.postpc.y2021.reutk.calculateroots;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static java.lang.Math.sqrt;

public class CalculateRootsWorker extends Worker {

    public CalculateRootsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        long numToCalc = getInputData().getLong("numToCalc",-1);
        long[] roots = getInputData().getLongArray("roots");
        long sqrtNum = (long) sqrt(numToCalc);
        for(int i = 2; i < sqrtNum; i++) {
            if(numToCalc % i == 0) {
                roots[0] = i;
                roots[1] = (numToCalc / i);
                return Result.success();
            }
        }
        roots[0] = numToCalc;
        return Result.success();
    }
}
