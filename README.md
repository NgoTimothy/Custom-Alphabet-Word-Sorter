# Custom-Alphabet-Word-Sorter
This program takes two files inputs.  The first file taken is the new custom alphabet.  Any character can be taken as long as it is only one character (including space and tab).  Also the order that characters are listed in are also the order for the alphabet.  See below for example.
C
B
A
The order of this alphabet is now CBA instead of the normal ABC.  This makes the word CAB come before the word AB according to this alphabet.
The next file it takes is a word list. A valid word is a string of characters made with the custom alphabet. 
With the custom alphabet above, CAT is not a valid word because the alphabet does not include T.  
Once the wordlist is proccessed, the program sorts the wordlist 3 times with 3 different sorts (MergeSort, QuickSort and SelectionSort).
It compares the performance by printing out the time taken to sort each wordList with each sort.  
