# AmazingNumbers
# Buzz numbers

19 / 19 Prerequisites
Computer programming
Paradigms
Introduction to Java
Basic literals
Overview of the basic program
Join a study group for the project Amazing Numbers
Discuss your current project with fellow learners and help each other.
Description
Integers can be even or odd. All numbers that end with 0, 2, 4, 6, or 8 are even; the rest are odd. However, numbers have many other properties, you will learn about some of them in this project.

First, let's talk about Buzz numbers. They are numbers that are either divisible by 7 or end with 7. For example, the number 14 is a buzz number, since it is divisible by 7 without a remainder; the number 17 ends with 7, so it is also a buzz number. However, the number 75 is not a Buzz number, since it is neither divisible by 7 nor does it end with 7. The number 7 is a Buzz number too.

Your task at this stage is to write a program that prints the natural number parity and determines whether this number is a Buzz. The program should print why this number is a Buzz number.

Do you know how to determine whether a number is divisible by 7 or not? We have a great tip:

Remove the last digit;
Multiply the removed digit by 2 and subtract it from the remaining number.
If the result of the subtraction can be divided by 7, then the initial number is divisible by 7. You can apply the whole sequence multiple times until you get a relatively small/easy-to-interpret subtraction result.
For example, take 196. We remove the last digit and get 19. We subtract 12 (the removed digit multiplied by 2) to get 7. Since the last left digit is 7, the number is multiple of 7. So 196 is a Buzz number!

You can use any solution you want, the tests won't check it.

Objectives
Your program should check whether the given natural number is a buzz number.

Ask a user to enter a natural number.
If the number is not natural, print an error message.
Calculate and print the parity of the number.
Check whether is the number is a Buzz number and print the explanation.
Finish the program after printing the message.
Natural numbers are whole numbers starting from 1.

Explanations:

... is neither divisible by 7 nor does it end with 7
... is divisible by 7
... ends with 7
... is divisible by 7 and ends with 7
Error message:

This number is not natural!
Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1:

Enter a natural number:
> 13
This number is Odd.
It is not a Buzz number.
Explanation:
13 is neither divisible by 7 nor does it end with 7.
Example 2:

Enter a natural number:
> 14
This number is Even.
It is a Buzz number.
Explanation:
14 is divisible by 7.
Example 3:

Enter a natural number:
> 17
This number is Odd.
It is a Buzz number.
Explanation:
17 ends with 7.
Example 4:

Enter a natural number:
> 7
This number is Odd.
It is a Buzz number.
Explanation:
7 is divisible by 7 and ends with 7.
Example 5:

Enter a natural number:
-76
This number is not natural!

# Duck numbers
Join a study group for the project Amazing Numbers
Discuss your current project with fellow learners and help each other.
Description
Your next task is to determine whether a number is a Duck number. A Duck number is a positive number that contains zeroes. For example, 3210, 8050896, 70709 are Duck numbers. Note that a number with a leading 0 is not a Duck number. So, numbers like 035 or 0212 are not Duck numbers. Although, 01203 is a Duck, since it has a trailing 0.

In this stage, we need to simplify the way we display the information. We already have several properties that we need to show; we are going to add new features to our program in each stage. From now on, the card should follow this notation:

Properties of {number}
{property}: true/false
{property}: true/false
...
{property}: true/false
In this stage, the properties are even, odd, buzz and duck. For 14, the program output should look like this:

Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false
The property order, indentation, and spaces are not checked by the tests. The tests are also case-insensitive.

Objectives
Your program should print the properties of a natural number. In this stage, your program should:

Ask a user to enter a natural number;
If the number is not natural, the program should print an error message;
If the number is natural, the program should indicate the properties of the number;
Finish the program after printing the message.
Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1:

Enter a natural number:
> -7
This number is not natural!
Example 2:

Enter a natural number:
> 15
Properties of 15
        even: false
         odd: true
        buzz: false
        duck: false
Example 3:

Enter a natural number:
> 14
Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false
Example 4:

Enter a natural number:
> 102
Properties of 102
        even: true
         odd: false
        buzz: false
        duck: true
