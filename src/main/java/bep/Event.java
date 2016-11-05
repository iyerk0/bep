package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * An event which represents a rule being satisfied
 */
public class Event
{
    private final Symbol lhs;
    private final List<Symbol> rhs = new LinkedList<>();
    private int startPosition = -1;
    private int endPosition = -1;

    public Symbol getLhs()
    {
        return lhs;
    }

    public int getStartPosition()
    {
        return startPosition;
    }

    public int getEndPosition()
    {
        return endPosition;
    }


    public Event(NonTerminal lhs, List<Symbol> rhs, int startPosition, int endPosition)
    {
        if (isNull(lhs)) throw new IllegalArgumentException("LHS cannot be null");
        if ((rhs == null) || rhs.isEmpty()) throw new IllegalArgumentException("RHS cannot be a null or empty list");
        if (startPosition < 0 || endPosition < 0)
            throw new IllegalArgumentException("start and end position should be 0 or greater");
        this.lhs = lhs;
        Collections.copy(this.rhs, rhs);
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
