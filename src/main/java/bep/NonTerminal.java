package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a non-terminal symbol. It contains sequence of terminal and non-terminal symbols
 */
public class NonTerminal extends Symbol
{
    private final LinkedList<Symbol> symbolSequence = new LinkedList<Symbol>();
    private final ListIterator<Symbol> iterator;

    /**
     * denotes the start and the end of the input sequence which this NonTerminal matches
     */
    int start, end, inputPos = 0;

    public NonTerminal(String name, List<Symbol> symbolSequence)
    {
        super(name);
        Collections.copy(this.symbolSequence, symbolSequence);
        iterator = symbolSequence.listIterator();
    }

    @Override
    protected boolean canHandle(Event event)
    {
        return super.canHandle(event) && (status == Status.ACTIVE) && (inputPos + 1) == event.getStartPosition();
    }

    @Override
    protected void handleEventInner(Event event)
    {
        Symbol nextSymbol = iterator.next();
        if (event.getSymbol().equals(nextSymbol))
        {
            if (!iterator.hasNext())
            {
                end = inputPos;
                status = Status.SUCCESS;
                Event newEvent = new Event(this, start, end);
                emit(event);
            }
        }
    }
}
