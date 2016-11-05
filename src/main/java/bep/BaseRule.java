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

    private BaseRule(NonTerminal lhs, List<Symbol> rhs, GrammarProcessor processor)
    {
        super(lhs, rhs, processor);
    }

    @Override
    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getLhs().getClass().isAssignableFrom(getClass()));
    }

    @Override
    protected boolean handleEvent(Event event)
    {
        boolean eventAbsorbed = super.handleEvent(event);
        if (eventAbsorbed)
        {
            addRule(new RuleBuilder().addLhs(lhs).addRhsSequence(rhs).addStart(1).build());
        }
        inputPos = start;
        return eventAbsorbed;
    }

    private void addRule(Rule rule)
    {
        processor.addRule(rule);
    }
}
