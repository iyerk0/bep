package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a non-terminal symbol. It contains sequence of terminal and non-terminal symbols
 */
public class NonTerminal extends Symbol
{
    private final LinkedList<Symbol> symbolSequence = new LinkedList<Symbol>();


    public NonTerminal(String name, int start, List<Symbol> symbolSequence)
    {
        super(name, start);
        if ((symbolSequence == null) || symbolSequence.isEmpty())
        {
            throw new IllegalArgumentException("List of symbols cannot be null or empty");
        }
        Collections.copy(this.symbolSequence, symbolSequence);
    }

    @Override
    protected boolean canHandle(Event event)
    {
        return super.canHandle(event) && (status == Status.ACTIVE) && (inputPos + 1) == event.getStartPosition();
    }

    @Override
    protected boolean handleEventInner(Event event)
    {
        boolean eventAbsorbed = false;
        Symbol nextSymbol = symbolSequence.get(inputPos + 1);
        if (event.getSymbol().equals(nextSymbol))
        {
            inputPos++;
            if (symbolSequence.size() <= (inputPos + 1))
            {
                end = inputPos;
                status = Status.SUCCESS;
                Event newEvent = new Event(this, start, end);
                emit(event);
            }
            eventAbsorbed = true;
        }
        else
        {
            eventAbsorbed = false;
        }
        return eventAbsorbed;
    }
}
