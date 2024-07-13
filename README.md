# javaccwc
Go solution to [Coding Challenges](https://codingchallenges.fyi/challenges/intro) first challenge: [build your own wc tool](https://codingchallenges.fyi/challenges/challenge-wc)

## Testing

### Step 1

```bash
java org.haykal.Main -c test.txt
  342190 test.txt
```

### Step 2

```bash
java org.haykal.Main -l test.txt
    7145 test.txt
```

### Step 3

```bash
% wc -w test.txt
   58164 test.txt
java org.haykal.Main -w test.txt
58164   test.txt
```

### Step 4
```bash
java org.haykal.Main -m test.txt
  339292 test.txt
```

### Step 5
```bash
% wc test.txt
    7145   58164  342190 test.txt
java org.haykal.Main test.txt
7145    58164   342190  test.txt
```

### Step 6 (Final Step)
```bash
cat Test.txt | java org.haykal.Main -l
7145
```