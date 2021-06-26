package huji.postpc.y2021.reutk.calculateroots;

import java.util.List;

public class Calculation implements Comparable<Calculation> {

    @Override
    public int compareTo(Calculation o) {
        if (this.status == Status.IN_PROGRESS && o.status == Status.DONE) {
            return -1;
        } else if (this.status == Status.DONE && o.status == Status.IN_PROGRESS) {
            return 1;
        }
        return (int) (numToCalc - o.numToCalc);
    }

    enum Status {
        IN_PROGRESS,
        DONE
    }

    int progress = 0;
    long numToCalc;

    Status status = Status.IN_PROGRESS;

    int[] roots = new int[2];

    public Calculation(long number) {
        numToCalc = number;
    }

    public Status  getStatus() {
        return this.status;
    }


}
