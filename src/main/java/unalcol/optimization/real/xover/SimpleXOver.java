package unalcol.optimization.real.xover;

import unalcol.clone.Clone;
import unalcol.random.integer.IntUniform;
import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: SimpleXOver</p>
 * <p>Description:Exchanges the last components of the first individual with
 * the last components of the second individual</p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SimpleXOver extends RealArityTwo {
    protected IntUniform g;
    protected int d = 2;

    /**
     * Default constructor
     */
    public SimpleXOver() {
        g = new IntUniform(d - 1);
    }

    public int pos(int DIM1, int DIM2) {
        int min = Math.min(DIM1, DIM2);
        if (d != min) {
            d = min;
            g = new IntUniform(d - 1);
        }
        return g.generate() + 1;
    }

    /**
     * Apply the 2-ary genetic operator over the individual genomes
     *
     * @param c1 First Individuals genome to be modified by the genetic operator
     * @param c2 Second Individuals genome to be modified by the genetic operator
     * @return Extra information of the genetic operator
     */
    @Override
    public Vector<double[]> apply(double[] c1, double[] c2) {
        try {
            double[] x = (double[]) Clone.create(c1);
            double[] y = (double[]) Clone.create(c2);
            int pos = pos(x.length, y.length);
            double t;
            for (int i = 0; i < pos; i++) {
                t = x[i];
                x[i] = y[i];
                y[i] = t;
            }
            Vector<double[]> v = new Vector<double[]>();
            v.add(x);
            v.add(y);
            return v;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void adapt(double productivity) {
    }
}
