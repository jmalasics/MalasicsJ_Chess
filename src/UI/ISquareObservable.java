package UI;

/**
 * Created by jmalasics on 6/5/14.
 */
public interface ISquareObservable {

    public void registerObserver(ISquareObserver observer);

    public void removeObserver(ISquareObserver observer);

    public void notifyObservers();

}
