package GameLogic;

/**
 * Created by jmalasics on 6/6/14.
 */
public interface ITeamObservable {

    public void registerObserver(ITeamObserver observer);

    public void removeObserver(ITeamObserver observer);

    public void notifyObservers();

}
