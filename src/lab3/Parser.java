package lab3;

import lab3.token.Token;

import java.io.IOException;
import java.io.Reader;

public class Parser {

    private final Reader reader;
    private char currentChar;
    private int nextChar;
    private boolean eof;

    public Parser(Reader r) throws IOException {
        reader = r;
        nextChar = reader.read();
    }

    public Token nextToken() throws IOException {
        do {
            next();
        } while (isSpaceChar(currentChar));

        if (eof)
            return null;



        String ch = String.valueOf(currentChar);

        if ("BE".contains(ch)) {
            return new Token(ch);
        }

        if (">#W".contains(ch)) {
            next();
            if (eof || !Character.isDigit(currentChar))
                throw new RuntimeException("Syntax Error: Digit was expected");

            var sb = new StringBuilder(String.valueOf(currentChar));
            while (Character.isDigit((char) nextChar)) {
                next();
                sb.append(currentChar);
            }

            return new Token(ch, sb.toString());
        }

        if ("XY".contains(ch)) {
            next();
            if (eof || !"lurde".contains(currentChar+""))
                throw new RuntimeException("Syntax Error: [lurde] were expected" + currentChar);

            return new Token(ch, currentChar+"");
        }

        throw new RuntimeException("Syntax Error: Forbidden character");
    }

    public void close() throws IOException {
        reader.close();
    }

    private void next() throws IOException {
        if (nextChar == -1) eof = true;
        currentChar = (char) nextChar;
        nextChar = reader.read();
    }

    private boolean isSpaceChar(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }
}
