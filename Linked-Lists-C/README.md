# Linked Lists C

Tracery uses context‑free grammars to store information about how
to expand rules. A Tracery grammar is a set of keys, each of which has some set of expansions that can replace it. The line beverage:tea|coffee|cola|milk means that 
the symbol beverage can be replaced with any of those
four options. A symbol is replaced whenever you see it in hashtags. This rule 
  >#name# drank a glass of #beverage#  
 
will have the name and beverage symbols replaced with expansions associated with those symbols. A linked list is used to list the potential expansions of certain rules.

This assignment was done in my undergraduate studies, specifically Professor Michael Mateas's cmps12b.
Tracery is a simple text‑expansion language made by a TAs as a homework assignment for one of
Prof. Mateas's previous courses.