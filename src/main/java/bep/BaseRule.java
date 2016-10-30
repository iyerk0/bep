package bep;

import java.util.List;

import static bep.util.ValidationUtils.isNull;

/**
 * These are the BaseRules which define the Grammar of the language.
 * Depending on the events they receive, the generate the "Rules in motion" rules
 * Created by kiyer on 10/29/16.
 */
public class BaseRule extends Rule
{
    private BaseRule(Symbol lhs, List<Symbol> rhs)
    {
        super(lhs, rhs);
    }

    @Override
    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()));
    }

    @Override
    protected boolean handleEvent(Event event)
    {
        boolean eventAbsorbed = super.handleEvent(event);
        if (eventAbsorbed)
        {
            addRule(new RuleBuilder().addLhs(lhs).addRhsSequence(rhs));
        }
    }

    public static class BaseRuleBuilder extends RuleBuilder
    {
        @Override
        public BaseRule build()
        {
            return new BaseRule(this.lhs, this.rhs);
        }
    }
}
