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
    protected List<Symbol> rhs = new LinkedList<Symbol>();
    /**
     * The list of events which have statisfied this rule so far
     */
    private LinkedList<Event> symbolHistory = new LinkedList<Event>();
    protected Symbol lhs;

    public enum Status
    {
        INACTIVE, ACTIVE, SUCCESS, FAILED
    }

    /**
     * The Rule's symbol which is the LHS of the rule
     */
    protected Status status = Status.INACTIVE;
    /**
     * denotes the start and the end of the input sequence which this NonTerminal matches
     */
    protected int start, end, inputPos = -1;

    public Rule(Symbol lhs, List<Symbol> rhs)
    {
        if (lhs == null)
        {
            throw new IllegalArgumentException("Must provide a valid value for Symbol name");
        }
        this.lhs = lhs;
        this.end = start;
        this.inputPos = start;
        this.status = Status.ACTIVE;
        if ((rhs == null) || rhs.isEmpty())
        {
            throw new IllegalArgumentException("List of symbols cannot be null or empty");
        }
        Collections.copy(this.rhs, rhs);
    }

    private Rule(Symbol lhs, List<Symbol> rhs, int start)
    {
        this(lhs, rhs);
        if ((start < 0))
        {
            throw new IllegalArgumentException("Start cannot be less than 0");
        }
        this.start = start;
    }

    protected boolean canHandle(Event event)
    {
        return (!isNull(event) && event.getSymbol().getClass().isAssignableFrom(getClass()) && (inputPos + 1) == event.getStartPosition());
    }

    protected boolean handleEvent(Event event)
    {
        boolean eventAbsorbed = false;
        Symbol nextSymbol = rhs.get(inputPos + 1);
        if (event.getSymbol().equals(nextSymbol))
        {
            inputPos++;
            if (rhs.size() <= (inputPos + 1))
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

    public static class RuleBuilder
    {
        protected Symbol lhs;
        protected List<Symbol> rhs = new LinkedList<>();
        protected int start;

        public RuleBuilder addLhs(Symbol symbol)
        {
            if (symbol != null)
            {
                this.lhs = symbol;
            }
            return this;
        }

        public RuleBuilder addLhs(String nonTerminal)
        {
            this.lhs = Symbol.getSymbolInstanceFor("T", NonTerminal.class);
            return this;
        }

        public RuleBuilder addRhsFragment(String symbol, Class<? extends Symbol> symbolClass)
        {
            this.rhs.add(Symbol.getSymbolInstanceFor("T", symbolClass));
            return this;
        }

        public RuleBuilder addRhsSequence(List<Symbol> symbolSequence)
        {
            if (symbolSequence != null)
            {
                for (Symbol symbol : symbolSequence)
                {
                    this.rhs.add(symbol);
                }
            }
            return this;
        }

        public RuleBuilder addStart(int start)
        {
            this.start = start;
            return this;
        }

        public Rule build()
        {
            Rule rule = new Rule(this.lhs, this.rhs, this.start);
            return rule;
        }
    }
}
