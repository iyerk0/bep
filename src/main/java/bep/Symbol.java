package bep;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * A class that represents a Terminal or Non-Terminal Symbol
 */
public abstract class Symbol
{
    private Status status = Status.INACTIVE;

    public enum Status
    {
        INACTIVE, ACTIVE, SUCCESS, FAILED
    }

    protected Symbol()
    {
        this.status = Status.ACTIVE;
    }

    protected abstract void handleEventInner(Event event);

    public void handleEvent(Event event)
    {
        if (!canHandle(event)) return;
        handleEventInner(event);
    }

    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()));
    }

    protected void emit(Event event)
    {
        //TODO: Post to event queue
    }

}
