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

```
AAAAAAAABBBBACCAAAAAAAABDDDBBCAAAAAAAABDDDBBDBAAAAAAABDBBBCCDBBAAAACBDDBBCCBDDCAAABDDDBBDBBDDBCAABDDBBBBBEEEBBCACDBBBBBBBEFEBCAACDBBBBBBEFCFEAAACDBBBBBBEFCFEAAACBBBBBBEFFCFFEAACGGBBBEFFFFFFFEACGGGBBEFFFCFFFEAACGGCEFFFFFFFFFEAACCCEEEEEEEEEEEAAAAAAAAAAAAAAAA
```

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

Encoding Table:
------------------------------------------------
| n     | P          | C     | Ext. Dictionary | 
------------------------------------------------
| 2     | A          | A     | AA              | 
| 3     | A          | A     | AA              | 
| 3     | AA         | A     | AAA             | 
| 4     | A          | A     | AA              | 
| 4     | AA         | A     | AAA             | 
| 4     | AAA        | A     | AAAA            | 
| 5     | A          | A     | AA              | 
| 5     | AA         | B     | AAB             | 
| 6     | B          | B     | BB              | 
| 7     | B          | B     | BB              | 
| 7     | BB         | B     | BBB             | 
| 8     | B          | A     | BA              | 
| 9     | A          | C     | AC              | 
| 10    | C          | C     | CC              | 
| 11    | C          | A     | CA              | 
| 12    | A          | A     | AA              | 
| 12    | AA         | A     | AAA             | 
| 12    | AAA        | A     | AAAA            | 
| 12    | AAAA       | A     | AAAAA           | 
| 13    | A          | A     | AA              | 
| 13    | AA         | A     | AAA             | 
| 13    | AAA        | A     | AAAA            | 
| 13    | AAAA       | B     | AAAAB           | 
| 14    | B          | D     | BD              | 
| 15    | D          | D     | DD              | 
| 16    | D          | D     | DD              | 
| 16    | DD         | B     | DDB             | 
| 17    | B          | B     | BB              | 
| 17    | BB         | C     | BBC             | 
| 18    | C          | A     | CA              | 
| 18    | CA         | A     | CAA             | 
| 19    | A          | A     | AA              | 
| 19    | AA         | A     | AAA             | 
| 19    | AAA        | A     | AAAA            | 
| 19    | AAAA       | A     | AAAAA           | 
| 19    | AAAAA      | A     | AAAAAA          | 
| 20    | A          | A     | AA              | 
| 20    | AA         | B     | AAB             | 
| 20    | AAB        | D     | AABD            | 
| 21    | D          | D     | DD              | 
| 21    | DD         | D     | DDD             | 
| 22    | D          | B     | DB              | 
| 23    | B          | B     | BB              | 
| 23    | BB         | D     | BBD             | 
| 24    | D          | B     | DB              | 
| 24    | DB         | A     | DBA             | 
| 25    | A          | A     | AA              | 
| 25    | AA         | A     | AAA             | 
| 25    | AAA        | A     | AAAA            | 
| 25    | AAAA       | A     | AAAAA           | 
| 25    | AAAAA      | A     | AAAAAA          | 
| 25    | AAAAAA     | A     | AAAAAAA         | 
| 26    | A          | B     | AB              | 
| 27    | B          | D     | BD              | 
| 27    | BD         | B     | BDB             | 
| 28    | B          | B     | BB              | 
| 28    | BB         | B     | BBB             | 
| 28    | BBB        | C     | BBBC            | 
| 29    | C          | C     | CC              | 
| 29    | CC         | D     | CCD             | 
| 30    | D          | B     | DB              | 
| 30    | DB         | B     | DBB             | 
| 31    | B          | A     | BA              | 
| 31    | BA         | A     | BAA             | 
| 32    | A          | A     | AA              | 
| 32    | AA         | A     | AAA             | 
| 32    | AAA        | C     | AAAC            | 
| 33    | C          | B     | CB              | 
| 34    | B          | D     | BD              | 
| 34    | BD         | D     | BDD             | 
| 35    | D          | B     | DB              | 
| 35    | DB         | B     | DBB             | 
| 35    | DBB        | C     | DBBC            | 
| 36    | C          | C     | CC              | 
| 36    | CC         | B     | CCB             | 
| 37    | B          | D     | BD              | 
| 37    | BD         | D     | BDD             | 
| 37    | BDD        | C     | BDDC            | 
| 38    | C          | A     | CA              | 
| 38    | CA         | A     | CAA             | 
| 38    | CAA        | A     | CAAA            | 
| 39    | A          | B     | AB              | 
| 39    | AB         | D     | ABD             | 
| 40    | D          | D     | DD              | 
| 40    | DD         | D     | DDD             | 
| 40    | DDD        | B     | DDDB            | 
| 41    | B          | B     | BB              | 
| 41    | BB         | D     | BBD             | 
| 41    | BBD        | B     | BBDB            | 
| 42    | B          | B     | BB              | 
| 42    | BB         | D     | BBD             | 
| 42    | BBD        | D     | BBDD            | 
| 43    | D          | B     | DB              | 
| 43    | DB         | C     | DBC             | 
| 44    | C          | A     | CA              | 
| 44    | CA         | A     | CAA             | 
| 44    | CAA        | B     | CAAB            | 
| 45    | B          | D     | BD              | 
| 45    | BD         | D     | BDD             | 
| 45    | BDD        | B     | BDDB            | 
| 46    | B          | B     | BB              | 
| 46    | BB         | B     | BBB             | 
| 46    | BBB        | B     | BBBB            | 
| 47    | B          | B     | BB              | 
| 47    | BB         | E     | BBE             | 
| 48    | E          | E     | EE              | 
| 49    | E          | E     | EE              | 
| 49    | EE         | B     | EEB             | 
| 50    | B          | B     | BB              | 
| 50    | BB         | C     | BBC             | 
| 50    | BBC        | A     | BBCA            | 
| 51    | A          | C     | AC              | 
| 51    | AC         | D     | ACD             | 
| 52    | D          | B     | DB              | 
| 52    | DB         | B     | DBB             | 
| 52    | DBB        | B     | DBBB            | 
| 53    | B          | B     | BB              | 
| 53    | BB         | B     | BBB             | 
| 53    | BBB        | B     | BBBB            | 
| 53    | BBBB       | B     | BBBBB           | 
| 54    | B          | E     | BE              | 
| 55    | E          | F     | EF              | 
| 56    | F          | E     | FE              | 
| 57    | E          | B     | EB              | 
| 58    | B          | C     | BC              | 
| 59    | C          | A     | CA              | 
| 59    | CA         | A     | CAA             | 
| 59    | CAA        | C     | CAAC            | 
| 60    | C          | D     | CD              | 
| 61    | D          | B     | DB              | 
| 61    | DB         | B     | DBB             | 
| 61    | DBB        | B     | DBBB            | 
| 61    | DBBB       | B     | DBBBB           | 
| 62    | B          | B     | BB              | 
| 62    | BB         | B     | BBB             | 
| 62    | BBB        | E     | BBBE            | 
| 63    | E          | F     | EF              | 
| 63    | EF         | C     | EFC             | 
| 64    | C          | F     | CF              | 
| 65    | F          | E     | FE              | 
| 65    | FE         | A     | FEA             | 
| 66    | A          | A     | AA              | 
| 66    | AA         | A     | AAA             | 
| 66    | AAA        | C     | AAAC            | 
| 66    | AAAC       | D     | AAACD           | 
| 67    | D          | B     | DB              | 
| 67    | DB         | B     | DBB             | 
| 67    | DBB        | B     | DBBB            | 
| 67    | DBBB       | B     | DBBBB           | 
| 67    | DBBBB      | B     | DBBBBB          | 
| 68    | B          | B     | BB              | 
| 68    | BB         | E     | BBE             | 
| 68    | BBE        | F     | BBEF            | 
| 69    | F          | C     | FC              | 
| 70    | C          | F     | CF              | 
| 70    | CF         | E     | CFE             | 
| 71    | E          | A     | EA              | 
| 72    | A          | A     | AA              | 
| 72    | AA         | A     | AAA             | 
| 72    | AAA        | C     | AAAC            | 
| 72    | AAAC       | B     | AAACB           | 
| 73    | B          | B     | BB              | 
| 73    | BB         | B     | BBB             | 
| 73    | BBB        | B     | BBBB            | 
| 73    | BBBB       | B     | BBBBB           | 
| 73    | BBBBB      | B     | BBBBBB          | 
| 74    | B          | E     | BE              | 
| 74    | BE         | F     | BEF             | 
| 75    | F          | F     | FF              | 
| 76    | F          | C     | FC              | 
| 76    | FC         | F     | FCF             | 
| 77    | F          | F     | FF              | 
| 77    | FF         | E     | FFE             | 
| 78    | E          | A     | EA              | 
| 78    | EA         | A     | EAA             | 
| 79    | A          | C     | AC              | 
| 79    | AC         | G     | ACG             | 
| 80    | G          | G     | GG              | 
| 81    | G          | B     | GB              | 
| 82    | B          | B     | BB              | 
| 82    | BB         | B     | BBB             | 
| 82    | BBB        | E     | BBBE            | 
| 82    | BBBE       | F     | BBBEF           | 
| 83    | F          | F     | FF              | 
| 83    | FF         | F     | FFF             | 
| 84    | F          | F     | FF              | 
| 84    | FF         | F     | FFF             | 
| 84    | FFF        | F     | FFFF            | 
| 85    | F          | F     | FF              | 
| 85    | FF         | E     | FFE             | 
| 85    | FFE        | A     | FFEA            | 
| 86    | A          | C     | AC              | 
| 86    | AC         | G     | ACG             | 
| 86    | ACG        | G     | ACGG            | 
| 87    | G          | G     | GG              | 
| 87    | GG         | B     | GGB             | 
| 88    | B          | B     | BB              | 
| 88    | BB         | E     | BBE             | 
| 88    | BBE        | F     | BBEF            | 
| 88    | BBEF       | F     | BBEFF           | 
| 89    | F          | F     | FF              | 
| 89    | FF         | C     | FFC             | 
| 90    | C          | F     | CF              | 
| 90    | CF         | F     | CFF             | 
| 91    | F          | F     | FF              | 
| 91    | FF         | E     | FFE             | 
| 91    | FFE        | A     | FFEA            | 
| 91    | FFEA       | A     | FFEAA           | 
| 92    | A          | C     | AC              | 
| 92    | AC         | G     | ACG             | 
| 92    | ACG        | G     | ACGG            | 
| 92    | ACGG       | C     | ACGGC           | 
| 93    | C          | E     | CE              | 
| 94    | E          | F     | EF              | 
| 94    | EF         | F     | EFF             | 
| 95    | F          | F     | FF              | 
| 95    | FF         | F     | FFF             | 
| 95    | FFF        | F     | FFFF            | 
| 95    | FFFF       | F     | FFFFF           | 
| 96    | F          | F     | FF              | 
| 96    | FF         | F     | FFF             | 
| 96    | FFF        | F     | FFFF            | 
| 96    | FFFF       | E     | FFFFE           | 
| 97    | E          | A     | EA              | 
| 97    | EA         | A     | EAA             | 
| 97    | EAA        | C     | EAAC            | 
| 98    | C          | C     | CC              | 
| 98    | CC         | C     | CCC             | 
| 99    | C          | E     | CE              | 
| 99    | CE         | E     | CEE             | 
| 100   | E          | E     | EE              | 
| 100   | EE         | E     | EEE             | 
| 101   | E          | E     | EE              | 
| 101   | EE         | E     | EEE             | 
| 101   | EEE        | E     | EEEE            | 
| 102   | E          | E     | EE              | 
| 102   | EE         | E     | EEE             | 
| 102   | EEE        | E     | EEEE            | 
| 102   | EEEE       | E     | EEEEE           | 
| 103   | E          | A     | EA              | 
| 103   | EA         | A     | EAA             | 
| 103   | EAA        | A     | EAAA            | 
| 104   | A          | A     | AA              | 
| 104   | AA         | A     | AAA             | 
| 104   | AAA        | A     | AAAA            | 
| 104   | AAAA       | A     | AAAAA           | 
| 104   | AAAAA      | A     | AAAAAA          | 
| 104   | AAAAAA     | A     | AAAAAAA         | 
| 104   | AAAAAAA    | A     | AAAAAAAA        | 
| 105   | A          | A     | AA              | 
| 105   | AA         | A     | AAA             | 
| 105   | AAA        | A     | AAAA            | 
| 105   | AAAA       | A     | AAAAA           | 
| 105   | AAAAA      | A     | AAAAAA          | 
| 105   | AAAAAA     | A     | AAAAAAA         | 
| 105   | AAAAAAA    | A     | AAAAAAAA        | 
------------------------------------------------

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
