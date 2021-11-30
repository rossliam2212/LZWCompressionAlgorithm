# LZW Compression Algorithm

Simple LZW Compression Encoding program written using Java based on the [Algorithm](https://github.com/rossliam2212/LZWCompressionAlgorithm/blob/master/Encoding%20Algorithm.txt) below.

## Encoding Algorithm
- At the start, the dictionary contains all possible roots and P is empty.

- Let C be the next character in the charstream.

- Is the new string P+C present in the dictionary?
  - If yes, P = P+C (extend the string P with C
  - If no, then:
    - Output the code word which represnts P to the codestream
    - Add the string P+C to the dictionary
    - Set P = C (P only contains C now)
   
- Check if there ate more characters in the stream.
  - If yes, go back to the second step.
  - If no, then output the code representing P to the codestrema and end.

## Encoding Example:

Note - There are two errors:
- The last entry in the encoding table will always be incorrect. 
- The codestream will always be short one code.

### Input:

![image](https://user-images.githubusercontent.com/73957889/144081434-fd6e697e-1a04-430c-9b09-fa48afa84bdb.png)

#### Charstream for 16x16 image above: 

AAAAAAAABBBBACCAAAAAAAABDDDBBCAAAAAAAABDDDBBDBAAAAAAABDBBBCCDBBAAAACBDDBBCCBDDCAAABDDDBBDBBDDBCAABDDBBBBBEEEBBCACDBBBBBBBEFEBCAACDBBBBBBEFCFEAAACDBBBBBBEFCFEAAACBBBBBBEFFCFFEAACGGBBBEFFFFFFFEACGGGBBEFFFCFFFEAACGGCEFFFFFFFFFEAACCCEEEEEEEEEEEAAAAAAAAAAAAAAAA

Where:
```
White       -> A -> (1)
Grey        -> B -> (2)
Dark Grey   -> C -> (3)
Light Grey  -> D -> (4)
Orange      -> E -> (5)
Yellow      -> F -> (6)
Red         -> G -> (7)
```

### Output:
```
Root Dictionary:
-----------
| A | (1) |
| B | (2) |
| C | (3) |
| D | (4) |
| E | (5) |
| F | (6) |
| G | (7) |
-----------

Full Extended Dictionary: 
----------------------
| AA         | ( 8 ) | 
| AAA        | ( 9 ) | 
| AAAA       | ( 10 ) | 
| AAB        | ( 11 ) | 
| BB         | ( 12 ) | 
| BBB        | ( 13 ) | 
| BA         | ( 14 ) | 
| AC         | ( 15 ) | 
| CC         | ( 16 ) | 
| CA         | ( 17 ) | 
| AAAAA      | ( 18 ) | 
| AAAAB      | ( 19 ) | 
| BD         | ( 20 ) | 
| DD         | ( 21 ) | 
| DDB        | ( 22 ) | 
| BBC        | ( 23 ) | 
| CAA        | ( 24 ) | 
| AAAAAA     | ( 25 ) | 
| AABD       | ( 26 ) | 
| DDD        | ( 27 ) | 
| DB         | ( 28 ) | 
| BBD        | ( 29 ) | 
| DBA        | ( 30 ) | 
| AAAAAAA    | ( 31 ) | 
| AB         | ( 32 ) | 
| BDB        | ( 33 ) | 
| BBBC       | ( 34 ) | 
| CCD        | ( 35 ) | 
| DBB        | ( 36 ) | 
| BAA        | ( 37 ) | 
| AAAC       | ( 38 ) | 
| CB         | ( 39 ) | 
| BDD        | ( 40 ) | 
| DBBC       | ( 41 ) | 
| CCB        | ( 42 ) | 
| BDDC       | ( 43 ) | 
| CAAA       | ( 44 ) | 
| ABD        | ( 45 ) | 
| DDDB       | ( 46 ) | 
| BBDB       | ( 47 ) | 
| BBDD       | ( 48 ) | 
| DBC        | ( 49 ) | 
| CAAB       | ( 50 ) | 
| BDDB       | ( 51 ) | 
| BBBB       | ( 52 ) | 
| BBE        | ( 53 ) | 
| EE         | ( 54 ) | 
| EEB        | ( 55 ) | 
| BBCA       | ( 56 ) | 
| ACD        | ( 57 ) | 
| DBBB       | ( 58 ) | 
| BBBBB      | ( 59 ) | 
| BE         | ( 60 ) | 
| EF         | ( 61 ) | 
| FE         | ( 62 ) | 
| EB         | ( 63 ) | 
| BC         | ( 64 ) | 
| CAAC       | ( 65 ) | 
| CD         | ( 66 ) | 
| DBBBB      | ( 67 ) | 
| BBBE       | ( 68 ) | 
| EFC        | ( 69 ) | 
| CF         | ( 70 ) | 
| FEA        | ( 71 ) | 
| AAACD      | ( 72 ) | 
| DBBBBB     | ( 73 ) | 
| BBEF       | ( 74 ) | 
| FC         | ( 75 ) | 
| CFE        | ( 76 ) | 
| EA         | ( 77 ) | 
| AAACB      | ( 78 ) | 
| BBBBBB     | ( 79 ) | 
| BEF        | ( 80 ) | 
| FF         | ( 81 ) | 
| FCF        | ( 82 ) | 
| FFE        | ( 83 ) | 
| EAA        | ( 84 ) | 
| ACG        | ( 85 ) | 
| GG         | ( 86 ) | 
| GB         | ( 87 ) | 
| BBBEF      | ( 88 ) | 
| FFF        | ( 89 ) | 
| FFFF       | ( 90 ) | 
| FFEA       | ( 91 ) | 
| ACGG       | ( 92 ) | 
| GGB        | ( 93 ) | 
| BBEFF      | ( 94 ) | 
| FFC        | ( 95 ) | 
| CFF        | ( 96 ) | 
| FFEAA      | ( 97 ) | 
| ACGGC      | ( 98 ) | 
| CE         | ( 99 ) | 
| EFF        | ( 100 ) | 
| FFFFF      | ( 101 ) | 
| FFFFE      | ( 102 ) | 
| EAAC       | ( 103 ) | 
| CCC        | ( 104 ) | 
| CEE        | ( 105 ) | 
| EEE        | ( 106 ) | 
| EEEE       | ( 107 ) | 
| EEEEE      | ( 108 ) | 
| EAAA       | ( 109 ) | 
| AAAAAAAA   | ( 110 ) | 
-----------------------

Codestream: 
1 8 9 8 2 12 2 1 3 3 10 10 2 4 21 12 17 18 11 21 4 12 28 25 1 20 13 16 28 14 9 3 20 36 16 40 24 32 2
7 29 29 28 24 40 13 12 5 54 23 15 36 52 2 5 6 5 2 24 3 58 13 61 3 62 38 67 53 6 70 5 38 59 60 6 75 8
1 77 15 7 7 68 81 89 83 85 86 74 81 70 91 92 3 61 90 90 84 16 99 54 106 107 84 31 

Efficiency: 
0.56640625 ≃ 57% reduction.
```
