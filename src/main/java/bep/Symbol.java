package bep;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * A class that represents a Terminal or Non-Terminal Symbol
 */
public abstract class Symbol implements EventHandler
{
    private final String name;
    protected Status status = Status.INACTIVE;
    /**
     * denotes the start and the end of the input sequence which this NonTerminal matches
     */
    int start, end, inputPos = -1;

    public enum Status
    {
        INACTIVE, ACTIVE, SUCCESS, FAILED
    }

    protected Symbol(String name, int start)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Must provide a valid value for Symbol name");
        }
        if ((start < 0))
        {
            throw new IllegalArgumentException("Start cannot be less than 0");
        }
        this.name = name;
        this.start = start;
        this.end = start;
        this.inputPos = start;
        this.status = Status.ACTIVE;
    }

    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()));
    }

    public boolean handle(Event event)
    {
        if (canHandle(event))
        {
            return handleEventInner(event);
        }
        return false;
    }

    /**
     * returns  true if this particular Symbol/Rule was able to "absorb" the Symbol wrapped by the event
     *
     * @param event
     * @return
     */
    protected abstract boolean handleEventInner(Event event);

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
