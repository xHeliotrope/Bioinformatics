Bioinformatics
==============
A program to build genetic sequence-based phylogenies from small groups of organisms. Currently only scores and generates trees from four sequences, hoping to add variably sized scoring and tree building in the future.

Can Translate DNA to Amino Acids.

Still refining the scoring procedure, but currently using a rough implementation of the [Needleman-Wunsch Algorithm](http://en.wikipedia.org/wiki/Needleman-Wunsch_algorithm).

All sequences were obtained from [NIH Databases](http://www.ncbi.nlm.nih.gov/protein)

Sample Outputs:
<pre><code>
For Estrogen Sequences:

            ___Columbia Livia (Rock Dove)
       ____|   
      |    |___Torgo Stracheliotus (Lappet-Faced Vulture)
 _____|        
      |     ___Danio Rerio (Zebra Fish)
      |____|   
           |___Rutilus Rutilus (Common Roach[Fish])

Similarity score between top sequence pair: 4644
Similarity score between bottom sequence pair: 3734


Alignment Scores for the Phylogeny: 

4644 Columbia Livia (Rock Dove) and Torgo Stracheliotus (Lappet-Faced Vulture)
3734 Danio Rerio (Zebra Fish) and Rutilus Rutilus (Common Roach[Fish])
1538 Columbia Livia (Rock Dove) and Rutilus Rutilus (Common Roach[Fish])
1532 Rutilus Rutilus (Common Roach[Fish]) and Torgo Stracheliotus (Lappet-Faced Vulture)
1520 Danio Rerio (Zebra Fish) and Torgo Stracheliotus (Lappet-Faced Vulture)
1512 Columbia Livia (Rock Dove) and Danio Rerio (Zebra Fish)
</code></pre>


