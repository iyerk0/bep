package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This represents a base rule which is one of the grammar rules of the given language's grammar
 * Created by kiyer on 10/24/16.
 */
public class RuleHandler implements EventHandler
{
    private final String name;
    private final LinkedList<Symbol> symbolSequence = new LinkedList<Symbol>();

    public RuleHandler(String name, List<Symbol> symbolSequence)
    {
        this.name = name;
        Collections.copy(this.symbolSequence, symbolSequence);
    }

    public boolean handle(Event event)
    {
        boolean eventAbsorbed = false;
        Symbol nextSymbol = symbolSequence.get(0);
        if (event.getSymbol().equals(nextSymbol))
        {
            NonTerminal nonTerminal = new NonTerminal(name, event.getStartPosition(), symbolSequence);
            if (symbolSequence.size() <= 1)
            {

                Event newEvent = new Event(nonTerminal, 0, 0);
                emit(event);
            }
            else
            {
                addHandler(nonTerminal);
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
