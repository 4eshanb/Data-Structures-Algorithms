# Tracery Recursion

Tracery uses context‑free grammars to store information about how
to expand rules. A Tracery grammar is a set of keys, each of which has some set of expansions that can replace it. The line beverage:tea|coffee|cola|milk means that 
the symbol beverage can be replaced with any of those
four options. A symbol is replaced whenever you see it in hashtags. This rule 
  >'#name# drank a glass of #beverage#' 
will have the name and beverage symbols replaced with expansions associated with those symbols. In the case of the
beverage rule above, which has four possible expansions, one will be picked at random. If the replacement rule also has
hashtags in it, we replace those, and if those replacements have hashtags, we keep replacing things until all the
hashtags are gone, recursively.
The program is a simplified version of the actual Tracery.

This assignment was done in my undergraduate studies, specifically Professor Michael Mateas's cmps12b.
Tracery is a simple text‑expansion language made by a TAs as a homework assignment for one of
Prof. Mateas's previous courses.