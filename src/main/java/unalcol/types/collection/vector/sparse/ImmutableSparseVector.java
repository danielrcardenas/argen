/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector.sparse;

import unalcol.clone.Clone;
import unalcol.service.ServiceCore;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.SortedVector;

import java.util.Iterator;

/**
 * @author jgomez
 */
public class ImmutableSparseVector<T> implements ArrayCollection<T> {
    static {
        ServiceCore.set(ImmutableSparseVector.class, Clone.class, new ImmutableSparseVectorCloneService<Object>());
    }

    protected SortedVector<SparseElement<T>> vector;
    protected SparseElement<T> loc = new SparseElement<T>(0, null);

    public ImmutableSparseVector(SortedVector<SparseElement<T>> vector) {
        this.vector = vector; // new SortedVector(new SparseElementOrder());
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        loc.index = index;
        index = vector.findIndex(loc);
        return vector.get(index).value();
    }

    @Override
    public Iterator<T> iterator() {
        return new SparseVectorIterator<>(0, this);
    }

    public Iterator<SparseElement<T>> sparse_elements() {
        return vector.iterator();
    }

    public int findIndex(T data) {
        int k = 0;
        while (k < size() && !data.equals(vector.get(k).value())) {
            k++;
        }
        return (k == size()) ? -1 : k;
    }

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public int size() {
        return vector.size();
    }

    public SortedVector<SparseElement<T>> sparseVector() {
        return vector;
    }
}
