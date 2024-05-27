package com.sss.student.utils.string;

public class CamelToSnakeCase {
    public static String camelToSnake(String str) {
        // StringBuilder to build the result
        StringBuilder result = new StringBuilder();

        // Iterating through each character in the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // If the character is uppercase, append an underscore and convert to lowercase
            if (Character.isUpperCase(ch)) {
                // Avoid adding an underscore at the beginning
                if (i > 0) result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                // Append lowercase characters directly
                result.append(ch);
            }
        }

        return result.toString();
    }
}
