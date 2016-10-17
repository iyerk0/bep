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

    public NonTerminal(List<Symbol> symbolSequence)
    {
        super();
        Collections.copy(this.symbolSequence, symbolSequence);
        iterator = symbolSequence.listIterator();
    }

    public void handleEventInner(Event event)
    {

    }
}
