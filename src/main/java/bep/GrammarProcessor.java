package bep;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kiyer on 10/16/16.
 */
public class GrammarProcessor
{
    private Queue eventQueue;
    private List<EventHandler> eventHandlers;

    public void addHandler(RuleHandler ruleHandler)
    {
        assert (ruleHandler != null);
        eventHandlers.add(ruleHandler);
    }

    public GrammarProcessor()
    {
        List<EventHandler> eventHandlers = new LinkedList<EventHandler>();
    }

    public static void main(String[] args)
    {
        GrammarProcessor grammarProcessor = new GrammarProcessor();
        initializeEventHandlers(grammarProcessor);
    }

    private static void initializeEventHandlers(GrammarProcessor grammarProcessor)
    {
        /*
         Will test using following grammar used for simple arithmetic expressions:
         <P> ::= <S>      -- the start rule, rule #1
         <S> ::= <S> "+" <M> | <M> -- rule #2
         <M> ::= <M> "*" <T> | <T> -- rule #3
         <T> ::= "1" | "2" | "3" | "4" -- rule #4
         */

//        add rule #4
        int lastNum = 4;
        for (int i = 0; i <= lastNum; i++)
        {
            Character numberChar = Character.forDigit(i, 10);
            Symbol charSymbol = new Terminal(numberChar);
            RuleHandler ruleHandler = new RuleHandler("T", Collections.singletonList(charSymbol));
            grammarProcessor.addHandler(ruleHandler);
        }
//        add rule #3
        Symbol mSymbol = new NonTerminal()
    }
}
