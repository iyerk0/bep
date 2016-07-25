# bep
This is an implementation for a Better Earley Parser which is used to parse Context Free Grammars

## Why one more earley parser?
When I looked at the production rules for a context free grammar (CFG), it looked like an ideal candidate for an event based handler. 
The traditional algorithm for parsing is called the [Earley Parser](https://en.wikipedia.org/wiki/Earley_parser) EP. EP can be quite dense to understand. From a Software Engineering perspective, a CFG can be parsed using the [Event Observer pattern](https://en.wikipedia.org/wiki/Observer_pattern).
 Are the two equivalent? We will find out as we go along!

## Design discussion
The aim is to support [EBNF](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_Form) grammars eventually. The syntax for EBNF is defined [here](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_Form#Examples).
Initially we will implement the following :

1. EBNF is composed of Symbols. 
1. Each symbol is either a Terminal or Non-Terminal.
1. A handler will be associated with each Symbol which will emit that symbol if a certain input sequence represents that symbol
1. A Terminal handler will accept or reject and event right away
1. A Non-terminal handler will contain a sequence of Terminal and Non-Terminal Handlers
1. The Symbol sequence will be emitted as events

An Event will encapsulate the following information
* The symbol
* The start position in the input sequence
* The End position in the input sequence

Each Handler will have the following structure:
```Java
//pseudo-codish Java
class Handler{
    public int startPosition=-1;
    public int endPosition=-1;
    public status Status=INACTIVE
    public enum Status{
    INACTIVE,ACTIVE,SUCCESS,FAILED
    }
    public void handleEvent(Event event){...}
    
    public Status getStatus(){
    }
}
```

In addition to accepting events, Handlers will be able to emit events as well whenever they reach the SUCCESS state. The states will transition as follows:
 * All handlers will initially be in the `INACTIVE` state
 * Once set in ACTIVE status they will listen to all events streaming through
 * Upon receiving a certain sequence of events a handler will determine if it has reached the completed state. If so, it will emit the Symbol which it was handling
 