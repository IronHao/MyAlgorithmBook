#include "ArrayStack.h"
#include "ArrayStack.cpp"
#include <string>
#include <iostream>

using namespace std;


/*
 *  Dijkstra's two-stack arithmetic expression evaluation.
 *  One stack for operator, another stack for value.
 *  ・Value: push onto the value stack.
 *  ・Operator: push onto the operator stack.
 *  ・Left parenthesis: ignore.
 *  ・Right parenthesis: pop operator and two values; push the result of applying that operator to those values onto the operand stack.
 *  
 *  WARNING: Arithmetic expressions that ignore parentheses are not considered! And we only care about binary operator (operator with two values).
 */
int evaluate(string input)
{
    ArrayStack<char>* ops = new ArrayStack<char>();
    ArrayStack<int>* vals = new ArrayStack<int>();
    try
    {
        
        int num = 0;
        for(int i = 0; i < input.length(); i++)
        {
            char c = input[i];
            switch (c)
            {
            // Ignore the '(' and ' '[space].
            case '(':
            case ' ':
                break;

            // Pop the operator in 'ops' stack and do the operator.
            case ')':
            {
                char op = ops->pop();
                // if (op == char())
                //     throw "Illegal arithmetic expression!";
                int valR = vals->pop(); 
                if (op == '+') valR = vals->pop() + valR;
                else if (op == '-') valR = vals->pop() - valR;
                else if (op == '*') valR = vals->pop() * valR;
                else if (op == '/')
                {
                    if (valR == 0)
                        throw "The divisor cannot be zero!";
                    valR = ops->pop() / valR;
                }
                vals->push(valR);
                break;
            }

            // Arithmetic operator
            case '+':
            case '-':
            case '*':
            case '/':
                ops->push(c);
                break;

            // Value 
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                num = num * 10 + (c - '0');
                // One char of lookahead to deter whether it is a connected number or not. If the value is clear (found the end), push to the 'vals' stack.
                if (i+1 >= input.length() || input[i+1] > '9' || input[i+1] < '0')
                {
                    vals->push(num);
                    num = 0;
                }
                break;
            default:
                throw "Illegal characters in input!";
                break;
            }
        }
        if (vals->count() != 1)
            throw "Illegal arithmetic expression!";
    }
    catch(const char* msg)
    {
        cerr << msg << '\n';
    }
    
    return vals->pop();
}

/*
 *  A simple test:
 *  Input the arithmetic expression(one line) and evaluate it, it will print the answer and wait for next input.
 *  Input 'End' to exit.
 */
int main()
{
    ArrayStack<string>* ops = new ArrayStack<string>();
    ArrayStack<int>* vals = new ArrayStack<int>();

    string input;
    while (true)
    {
        getline(cin, input);
        if (input == "End")
            break;
        cout << "Ans: "<< evaluate(input) << endl;
    }
    return 0;
}