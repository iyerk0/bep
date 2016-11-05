package bep;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kiyer on 10/16/16.
 */
public class GrammarProcessor
{
    private Queue<Event> eventQueue;
    private List<Rule> baseRules;

    public void addRule(Rule rule)
    {
        assert (rule != null);
        baseRules.add(rule);
    }

    public GrammarProcessor()
    {
        baseRules = new LinkedList<>();
    }

    public static void main(String[] args)
    {
        GrammarProcessor grammarProcessor = new GrammarProcessor();
        initializeRules(grammarProcessor);
    }

    private static void initializeRules(GrammarProcessor grammarProcessor)
    {
        /*
         Will test using following grammar used for simple arithmetic expressions:
         <P> ::= <S>      -- the start rule, rule #1
         <S> ::= <S> "+" <M> | <M> -- rule #2
         <M> ::= <M> "*" <T> | <T> -- rule #3
         <T> ::= "0" | "1" | "2" | "3" | "4" -- rule #4
         */

//      add rule #4
        int lastNum = 4;
        for (int i = 0; i <= lastNum; i++)
        {
            Character numberChar = Character.forDigit(i, 10);
            grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("T").addRhsFragment(numberChar.toString(), Terminal.class).addProcessor(grammarProcessor).build());
        }
//      add rule #3
        grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("M").addRhsFragment("T", NonTerminal.class).addProcessor(grammarProcessor).build());
        grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("M").addRhsFragment("M", NonTerminal.class).addRhsFragment("*", Terminal.class).addRhsFragment("T", NonTerminal.class).addProcessor(grammarProcessor).build());

//      add rule #2
        grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("S").addRhsFragment("M", NonTerminal.class).addProcessor(grammarProcessor).build());
        grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("S").addRhsFragment("S", NonTerminal.class).addRhsFragment("+", Terminal.class).addRhsFragment("M", NonTerminal.class).addProcessor(grammarProcessor).build());

//      add rule #1
        grammarProcessor.addRule(new BaseRule.RuleBuilder().addLhs("P").addRhsFragment("S", NonTerminal.class).addProcessor(grammarProcessor).build());


    }
}
