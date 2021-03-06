package de.pp.copyhelper;

/**
 * @param <T>
 * @param <U> Representing a Pair of two Objects.
 * @author Philipp Pfeiffer
 */
public class Pair<T, U> {

    private T firstElement;
    private U secondElement;

    public Pair(T firstElement, U secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public T getFirst() {
        return this.firstElement;
    }

    public U getSecond() {
        return this.secondElement;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass().isAssignableFrom(this.getClass())
                && ((Pair<T, U>) o).getFirst().equals(this.getFirst())
                && ((Pair<T, U>) o).getSecond().equals(this.getSecond());
    }

    @Override
    public int hashCode() {
        int hash = 321;
        hash = 41 * hash + (firstElement == null ? 0 : firstElement.toString().hashCode());
        hash = 41 * hash + (secondElement == null ? 0 : secondElement.toString().hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "First Element: " + firstElement.toString() + " | Second Element: " + secondElement.toString();
    }

}
