
import lists.Node;
import lists.Queue;
import token.Token;

public class Analizer {

    Node<Character> currentChar;
    Accumulator acc;
    Queue<Character> characters;
    TokenGenerator tkGenerator;
    Validator validator;

    public void start(String stringToAnalize) {

        characters = generateCharQueue(stringToAnalize);
        acc = new Accumulator("");
        tkGenerator = new TokenGenerator();
        validator = new Validator();

        while (!characters.isEmpty()) {
            // remove char from characters and add it to accumulator
            currentChar = characters.getFront();
            acc.accumulate(characters.dequeue());

            // verify if token it's completed and if so, create it
            if (validator.isToken(currentChar)) {
                Token newTk = tkGenerator.generateToken(validator, acc);
                acc.empty();
                System.out.println("h");
            }

            // update tkGeneratorPointer
            tkGenerator.updatePointer(currentChar);
        }
    }

    public Queue<Character> generateCharQueue(String stringToAnalize) {

        Queue<Character> characters = new Queue<Character>();

        for (int i = 0; i < stringToAnalize.length(); i++) {
            characters.enqueue(stringToAnalize.charAt(i));
        }

        return characters;

    }

}
