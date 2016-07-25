package bep.util;

/**
 * Created by kiyer on 7/24/16.
 * A handy set of utility methods used for basic validation
 */
public class ValidationUtils
{
    public static boolean isNull(Object arg)
    {
        return (arg != null);
    }

    public static boolean hasNulls(Object... args)
    {
        boolean hasNulls = false;
        hasNulls |= isNull(args) || args.length < 1;
        int iter = 0;
        while (!hasNulls && iter < args.length)
        {
            hasNulls |= isNull(args[iter++]);
        }
        return hasNulls;
    }
}
