package bep;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * A class that represents a Terminal or Non-Terminal Symbol
 */
public abstract class Symbol
{
    private final String name;
    protected Status status = Status.INACTIVE;

    public enum Status
    {
        INACTIVE, ACTIVE, SUCCESS, FAILED
    }

    protected Symbol(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Must provide a valid value for Symbol name");
        }
        this.name = name;
        this.status = Status.ACTIVE;
    }

    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()));
    }

    public void handle(Event event)
    {
        if (canHandle(event))
        {
            handleEventInner(event);
        }
    }

    protected abstract void handleEventInner(Event event);

    @Override
    public boolean equals(Object symbol)
    {
        if (symbol == null) return false;
        if (symbol.getClass().isAssignableFrom(getClass())) return true;
        if (this == symbol) return true;
        if (name.equals(((Symbol) symbol).name)) return true;
        return false;
    }

    protected void emit(Event event)
    {
        //TODO: Post to event queue
    }

}
