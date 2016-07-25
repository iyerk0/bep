# bep
This is an implementation for a Better Earley Parser which is used to parse Context Free Grammars

## Why one more earley parser?
When I looked at the production rules for a context free grammar (CFG), it looked like an ideal candidate for an event based handler. 
The traditional algorithm for parsing is called the [Earley Parser](https://en.wikipedia.org/wiki/Earley_parser) EP. EP can be quite dense to understand. From a Software Engineering perspective, a CFG can be parsed using the [Event Observer pattern](https://en.wikipedia.org/wiki/Observer_pattern).
 Are the two equivalent? We will find out as we go along!


