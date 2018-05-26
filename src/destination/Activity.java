package destination;

public interface Activity<T> {

    T getType();

    T getName();

    T getLocation();

    T getTypeSpecific();
}
