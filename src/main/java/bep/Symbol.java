package bep;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiyer on 7/24/16.
 * A class that represents a Terminal or Non-Terminal Symbol
 */
public abstract class Symbol
{
    private static Map<String, Symbol> symbolCollection = new HashMap<>();

    public String getName()
    {
        return name;
    }

    private final String name;

    public static Symbol getSymbolInstanceFor(String name, Class<? extends Symbol> symbolClass)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Must provide a valid value for Symbol name");
        }
        Symbol symbol = symbolCollection.get(name);
        if (symbol == null)
        {
            try
            {
                Constructor<? extends Symbol> symbolConstructor = symbolClass.getConstructor(String.class);
                symbol = symbolConstructor.newInstance(name);
                symbolCollection.put(name, symbol);
            }
            catch (Exception e)
            {
                e.printStackTrace(); //TODO print to logs instead
            }
        }
        if (!symbol.getClass().isAssignableFrom(symbolClass))
            throw new IllegalArgumentException(
                String.format("There already exists an instance with symbol: %s. Requested type: %s, Actual type: %s", symbol.getName(),
                    symbolClass, symbol.getClass().getName()));
        return symbol;
    }

    protected Symbol(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object symbol)
    {
        if (symbol == null) return false;
        if (symbol.getClass().isAssignableFrom(getClass())) return true;
        if (this == symbol) return true;
        if (name.equals(((Symbol) symbol).name)) return true;
        return false;
    }
}
