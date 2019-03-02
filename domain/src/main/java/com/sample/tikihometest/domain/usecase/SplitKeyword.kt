package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.di.DomainScope
import com.sample.tikihometest.domain.util.Mockable
import javax.inject.Inject

@DomainScope
@Mockable
class SplitKeyword @Inject constructor() {

    /**
     * Split a sentence (keyword) into 2 lines  so that the max length of lines is minimized
     *
     * Requirement:
     * - If the keyword is more than one word, then display in two lines
     * - Words are arranged  to minimize max lines length
     *
     * Assume that:
     *  - If the max length lines are the same in both arrange, prefer the one with top line length > bottom line
     *    length create a V shape for consistency
     *
     *    Example: "a aaa a" have 2 possible valid arrangement
     *    |  a  |
     *    |aaa a|
     *
     *    |a aaa|
     *    |  a  |
     *    prefer the second one (same with "dac nhan tam" case that shows  in the demo.gif)
     *    ref: https://github.com/tikivn/android-home-test/blob/v2/demo.gif
     *
     * @param input raw keyword input
     *
     * @return split keyword (if needed)
     */
    operator fun invoke(input: String): String {
        val sentence = cleanWhiteSpace(input)

        // validate
        if (sentence.isEmpty())
            throw IllegalArgumentException("""Keyword: "$input" is empty""")
        val words = getWords(sentence)

        // special cases
        if (words.size == 1)
            return words[0]
        if (words.size == 2) {
            return "${words[0]}\n${words[1]}"
        }

        // words contain 3 words or above
        val avgLength = sentence.length / 2
        var lengthTaken = 0
        var firstLine = ""
        var secondLine = ""
        var middleWord = ""
        var isMiddleWordSet = false
        words.forEach { word ->
            if (lengthTaken + word.length < avgLength)
                firstLine += "$word "
            else {
                if (!isMiddleWordSet) {
                    middleWord = word
                    isMiddleWordSet = true
                } else {
                    secondLine += "$word "
                }
            }
            lengthTaken += (word.length + 1)
        }

        // TODO trimEnd or subString more performance?
        firstLine = firstLine.trimEnd()
        secondLine = secondLine.trimEnd()
        // in case the last word take half sentence'saturation length
        // e.green. "123 1234 123456789"
        if (secondLine.isEmpty()) {
            return "$firstLine\n$middleWord"
        }

        // append the middle word to the shorter one to minimize max length
        // if both have same length, append middle word to the first line to create a V shape
        return if (firstLine.length <= secondLine.length) {
            "$firstLine $middleWord\n$secondLine"
        } else {
            "$firstLine\n$middleWord $secondLine"
        }
    }

    /**
     * Clean white space from string
     * e.g. input:  "a  aa aaa  a "
     *      output: "a aa aaa a"
     *
     * @param input raw string input
     *
     * @return a string that contain no extra white space
     */
    internal fun cleanWhiteSpace(input: String): String {
        var appendedSpace = true
        val stringBuilder = StringBuilder()
        input.forEach { char ->
            if (char == ' ') {
                if (!appendedSpace) {
                    stringBuilder.append(" ")
                    appendedSpace = true
                }
            } else {
                stringBuilder.append(char)
                appendedSpace = false
            }
        }
        return stringBuilder.trim().toString()
    }

    /**
     * Split sentences into word
     * assume that input is cleaned from whitespace already
     *
     * @param keyword keyword that contains  no extra white space
     * @return list of words in the keyword
     */
    internal fun getWords(keyword: String): List<String> {
        return keyword
            .splitToSequence(" ")
            .toList()
    }
}