# ZipBrute
The project was created for final exam in Academy. 
It was decided to write a program to find the password from zip archives.
The goal of the project is create program with using multi-threads system. 

## About project
This program usings technology [Brute Force](https://en.wikipedia.org/wiki/Brute-force_search) to search a password for encrypted zip files.
Availiable password length from 1 to 16 chars.
The program has the ability to work in multiple threads.
JavaFX using as GUI. 

### Brute Force
BruteForce implements several parameters (character set, password length).
```java
BruteForce bruteForce = new BruteForce(new char[] {'a', 'b', 'c', 'd'}, 8);
```
Any password can be written to an integer value, and vice versa, if you specify the character set. 
This can be done through transfer from one [number system](https://en.wikipedia.org/wiki/Numeral_system) to another, this method converts the index into a password.
#### Now I show most important things.
* This example convertes index to password (`0` to `aaaa`).
```java
bruteForce.brute(new BigInteger("0"));
```
* This example converters password to index (`aaac` to `2`).
```java
bruteForce.toIndex("aaac");
```
* This example returns next password. 
Each other method call returns next password (first call `aaaa`, second call `aaab`).
```java
bruteForce.nextBrute();
```
* This example returns progress value (ratio current and last index from `0` to `1`). 
```java
bruteForce.getProgress();
```
* This example returns possibility of `nextBrute()`.
Returns `true` if current index less the last, else `false`. 
```java
bruteForce.isAlive();
```

### Charset
The program includes built-in character sets (in Carset.class). 
Which you can choose or combine with each other.

Name | Character set
--- | ---
Numbers | `0` `1` `2` `3` `4` `5` `6` `7` `8` `9`
Lower case | `a` `b` `c` `d` `e` `f` `g` `h` `i` `j` `k` `l` `m` `n` `o` `p` `q` `r` `s` `t` `u` `v` `w` `x` `y` `z`
Upper case | `A` `B` `C` `D` `E` `F` `G` `H` `I` `J` `K` `L` `M` `N` `O` `P` `Q` `R` `S` `T` `U` `V` `W` `X` `Y` `Z`
Specials | ` ` `!` `"` `#` `$` `%` `&` `'` `(` `)` `*` `+` `,` `-` `.` `/` `:` `;` `<` `=` `>` `?` `@` `[` `\` `]` `^` `_` ``` ` ``` `{` ``` \| ``` `}` `~`

## Technical requirements
OS: macOS, Linux, Windows.\
Java: Minimum and recommended version is Java 8.\
IDE: Recommended IntelliJ IDEA.

## Stucture
Main - Main class file.\
Controller - Controller of GUI.\
BruteForce - Brute force class.\
Charset - Charsets pressets.\
NumbersField - Only numbers TextField.

## Instruction
The insucrition shows how to use program.
1. Choose zip file with password (`Choose file` button).\
   On the left side you can see the stucture of archive file.
2. Set number of threads (The recomended count is number threads of your processor, but can be more).
3. Set Charsets (Must select at least one, you can find information about them above).
4. Set desired length of password (You can select `Growing length` then the password will be grow from 1 to your chosen length).
5. For start of serching password press `Brute forse` button.\
   On the left-lower side you can see current password and status (`Password brute...` - in progress, `Password don't found!` - password don't found, `Found for 0 sec.` - password found).
6. If password is found than program show form for saving (Choose path for saving and press `Open`, if you don't need to save files then press `Cansel` and right password will be showing on the same place).
7. If password don't found than you can change setting for brute forse and try again.
