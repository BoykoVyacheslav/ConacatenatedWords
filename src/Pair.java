class Pair<T> {

    private T first;
    private T second;

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    // return first element
    T first() {
        return first;
    }

    // return second element
    T second() {
        return second;
    }
}