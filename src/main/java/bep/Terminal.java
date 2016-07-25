package bep;

/**
 * Created by kiyer on 7/24/16.
 * This class represents a terminal symbol
 */
public class Terminal extends Symbol
{
    public char getData()
    {
        return data;
    }

    private char data;

    public Terminal(char data)
    {
        super();
        this.data = data;
    }

    public void handleEventInner(Event event)
    {

        Symbol eventSymbol = event.getSymbol();
//        if(equals((Terminal)eventSymbol))
//            emit
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (!(other instanceof Terminal)) return false;
        return ((Terminal) other).getData() == data;

    }
}
