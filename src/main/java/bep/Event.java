package bep;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * An event which represents a symbol being emitted
 */
public class Event
{
    private final Symbol symbol;
    private int startPosition = -1;
    private int endPosition = -1;

    public Symbol getSymbol()
    {
        return symbol;
    }

    public int getStartPosition()
    {
        return startPosition;
    }

    public int getEndPosition()
    {
        return endPosition;
    }


    public Event(Symbol symbol, int startPosition, int endPosition)
    {
        if (isNull(symbol)) throw new IllegalArgumentException("Symbol cannot be null");
        if (startPosition < 0 || endPosition < 0)
            throw new IllegalArgumentException("start and end position should be 0 or greater");
        this.symbol = symbol;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
