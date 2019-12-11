package model.states;

public interface State {
    public boolean addOnHold();
    public boolean takeFromHold();
    public boolean remove();
    public boolean closeSale();
    public boolean payment();
}
