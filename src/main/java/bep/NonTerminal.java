package bep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a non-terminal symbol. It contains sequence of terminal and non-terminal symbols
 */
public class NonTerminal extends Symbol
{
    private final List<Symbol> symbolSequence = new ArrayList<Symbol>();

    public NonTerminal(List<Symbol> symbolSequence)
    {
        super();
        Collections.copy(this.symbolSequence, symbolSequence);
    }

    public void handleEventInner(Event event)
    {

    }
}
