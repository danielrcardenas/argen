package unalcol.optimization.integer;

import unalcol.random.raw.RawGenerator;
import unalcol.search.space.Space;
import unalcol.types.integer.array.IntArray;

public class IntHyperCube extends Space<int[]> {
    protected int[] min;
    protected int[] max;

    public IntHyperCube(int n, int min, int max) {
        this(IntArray.create(n, min), IntArray.create(n, max));
    }

    public IntHyperCube(int[] min, int[] max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean feasible(int[] x) {
        int i = 0;
        while (i < x.length && min[i] <= x[i] && x[i] <= max[i]) {
            i++;
        }
        return (i == x.length);
    }

    @Override
    public double feasibility(int[] x) {
        double d = 0.0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < min[i]) {
                d += x[i] - min[i];
            } else {
                if (x[i] > max[i]) {
                    d += max[i] - x[i];
                }
            }
        }
        return d == 0.0 ? 1.0 : d;
    }

    @Override
    public int[] repair(int[] x) {
        x = x.clone();
        for (int i = 0; i < x.length; i++) {
            if (x[i] < min[i]) {
                x[i] = min[i];
            } else {
                if (x[i] > max[i]) {
                    x[i] = max[i];
                }
            }
        }
        return x;
    }

    @Override
    public int[] get() {
        RawGenerator g = RawGenerator.get(this);
        int[] x = new int[min.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = min[i] + g.integer(max[i] - min[i] + 1);
        }
        return x;
    }

}
