package bep;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a terminal symbol
 */
public class Terminal extends Symbol
{
    private static Map<Character, Terminal> charToTerminalMap = new HashMap<Character, Terminal>();

    protected Terminal(char c)
    {
        super(Character.toString(c));
    }

    /**
     * There will only be as many instances of the Terminal as there are terminals in the grammar.
     *
     * @param c
     */
    public static Terminal getTerminalFor(char c)
    {
        Terminal terminal = charToTerminalMap.get(c);
        if (terminal == null)
        {
            terminal = new Terminal(c);
            charToTerminalMap.put(c, terminal);
        }
        return charToTerminalMap.get(c);
    }

    protected boolean handleEventInner(Event event)
    {

    }
}
