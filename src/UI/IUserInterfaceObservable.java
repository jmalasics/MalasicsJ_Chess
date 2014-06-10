package UI;

/**
 * Created by jmalasics on 6/6/14.
 */
public interface IUserInterfaceObservable {

    public void registerObserver(IUserInterfaceObserver observer);

    public void removeObserver(IUserInterfaceObserver observer);

    public void notifyObservers();

}
