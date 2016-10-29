package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static bep.util.ValidationUtils.isNull;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a Rule of the Grammar in a certain state
 */
public class Rule
{
    /**
     * The list of symbols (terminals and Non-terminals) representing the RHS of the rule
     */
    private final LinkedList<Symbol> symbolSequence = new LinkedList<Symbol>();
    private final LinkedList<Event> symbolHistory = new LinkedList<Event>();

    public enum Status
    {
        INACTIVE, ACTIVE, SUCCESS, FAILED
    }

    /**
     * The Rule's symbol which is the LHS of the rule
     */
    private final Symbol name;
    protected Rule.Status status = Rule.Status.INACTIVE;
    /**
     * denotes the start and the end of the input sequence which this NonTerminal matches
     */
    int start, end, inputPos = -1;

    protected Symbol.Status status = Symbol.Status.INACTIVE;

    public Rule(String name, int start, List<Symbol> symbolSequence)
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
        this.status = Symbol.Status.ACTIVE;
        if ((symbolSequence == null) || symbolSequence.isEmpty())
        {
            throw new IllegalArgumentException("List of symbols cannot be null or empty");
        }
        Collections.copy(this.symbolSequence, symbolSequence);
    }

    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()));
    }

    protected boolean handleEvent(Event event)
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
