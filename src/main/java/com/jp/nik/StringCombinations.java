package com.jp.nik;

import java.util.function.Function;

/**
 * This class provides utility methods to generate combinations of characters from a given string.
 * It includes methods to process each combination and validate input before processing.
 */
public class StringCombinations {

    /**
     * The StringCombinations method does not allow instantiation.
     */
	private StringCombinations() {
		// Nothing to do.
	}

    /**
     * Generates combinations of characters from the input string and processes each with the provided function.
     *
     * @param characters The input string from which combinations are generated.
     * @param minLength The minimum number of digits for which combinations are generated.
     * @param maxLength The maximum number of digits for which combinations are generated.
     * @param processor  The function to process each generated combination.
     * @throws IllegalArgumentException if {@code processor} is {@code null}.
     * @throws IllegalArgumentException If {@code minLength} is less than {@code 0}.
     */
	public static void generateCombinations(String characters, int minLength, int maxLength,
			Function<String, Boolean> processor) {

		if (characters == null || characters.length() == 0) {
			throw new IllegalArgumentException("Set the string to some value.");
		}
		if (minLength < 0) {
			throw new IllegalArgumentException("The minimum length cannot be negative.");
		}
		if (minLength > maxLength) {
			throw new IllegalArgumentException("Maximum length must be greater than minimum.");
		}
        if (processor == null) {
            throw new IllegalArgumentException("Processor function must not be null");
        }
		int charactersLength = characters.length();
		for (int currentLength = minLength; currentLength <= maxLength; currentLength++) {
			int[] indices = new int[currentLength];
			while (true) {
				StringBuilder combination = new StringBuilder();
				for (int i : indices) {
					combination.append(characters.charAt(i));
				}
				processor.apply(combination.toString());
				int nextIndex = currentLength - 1;
				while (nextIndex >= 0 && indices[nextIndex] == charactersLength - 1) {
					indices[nextIndex] = 0;
					nextIndex--;
				}
				if (nextIndex < 0) {
					break;
				}
				indices[nextIndex]++;
			}
		}
	}
}
