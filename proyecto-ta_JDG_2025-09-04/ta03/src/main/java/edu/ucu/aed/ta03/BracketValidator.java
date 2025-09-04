package edu.ucu.aed.ta03;

import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidator {

    public static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') stack.push(c);
            else if (c == '}') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{{}}{}")); // true
        System.out.println(isBalanced("}{"));     // false
        System.out.println(isBalanced("{{{}"));   // false
    }
}
