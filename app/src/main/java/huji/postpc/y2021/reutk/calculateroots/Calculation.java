package huji.postpc.y2021.reutk.calculateroots;


import java.io.Serializable;


public class Calculation implements Comparable<Calculation>, Serializable {

    enum Status {
        IN_PROGRESS,
        DONE
    }

    int progress = 0;
    long numToCalc;

    Status status;

    long[] roots;

    public Calculation(long number) {
        this.numToCalc = number;
        this.status = Status.IN_PROGRESS;
        this.roots = new long[2];
    }

    public long getNemToCalc() {
        return this.numToCalc;
    }

    public long[] getRoots() {
        return this.roots;
    }

    public void setRoots(long[] roots) {
        this.roots = roots;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


    public Status  getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(Calculation o) {
        if (this.status == Status.IN_PROGRESS && o.status == Status.DONE) {
            return -1;
        } else if (this.status == Status.DONE && o.status == Status.IN_PROGRESS) {
            return 1;
        }
        return (int) (numToCalc - o.numToCalc);
    }
}
