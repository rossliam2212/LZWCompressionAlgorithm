import java.util.ArrayList;

public class Encoder {
    private final String charstream;
    private String codestream;
    private ArrayList<String> rootDictionary;
    private ArrayList<String> extendedDictionary;

    public Encoder(String charstream) {
        this.charstream = charstream;
        this.rootDictionary = new ArrayList<>();
        this.extendedDictionary = new ArrayList<>();
        this.codestream = "";
    }

    /**
     * This method starts off the encoding process and prints out all the information as it is calculated.
     */
    public void startEncoding() {
        printCharstream(this.charstream);

        this.rootDictionary = getRootDictionary(this.charstream);
        printRootDictionary(this.rootDictionary);

        int codestreamLength = getCodestream(this.charstream, this.rootDictionary);
        printExtendedDictionary(this.extendedDictionary, this.rootDictionary);

        printCodestream(this.codestream);

        calculateEfficiency(codestreamLength, this.rootDictionary, this.charstream.length());

    }

    /**
     * This method calculates the efficiency of the encoding on the charstream.
     * @param codestreamLength The length of the codestream.
     * @param rootDictionary The root dictionary.
     * @param charstreamLength The length of the charstream.
     */
    private void calculateEfficiency(double codestreamLength, ArrayList<String> rootDictionary, double charstreamLength) {
        double efficiency = 1 - ((codestreamLength + rootDictionary.size() + 1) / charstreamLength);
        int efficiencyPercentage = (int)Math.ceil(efficiency * 100);
        System.out.println("\nEfficiency: \n" + efficiency + " ≃ " + efficiencyPercentage + "% reduction.");
    }

    /**
     * This method uses the LZW encoding algorithm to encode the inputted charstream and calculate the codestream.
     * This method also prints out the encoding table.
     * NOTE: THE VERY LAST ENTRY IN THE ENCODING TABLE IS WRONG & HAS TO BE FIXED.
     * @param charstream The inputted charstream.
     * @param rootDictionary The root dictionary.
     * @return The length of the codestream.
     */
    private int getCodestream(String charstream, ArrayList<String> rootDictionary) {
        System.out.println("\nEncoding Table:");
        System.out.println("------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-15s | \n", "n", "P", "C", "Ext. Dictionary");
        System.out.println("------------------------------------------------");

        int codestreamLengthCounter = 0;
        String P = "";
        int beginIndex = 0;
        int endIndex = 1;
        String C = charstream.substring(beginIndex, endIndex);
        String PandC = P + C;

        for(int i = 0; i < charstream.length(); i++) {

            if (dictionaryCheck(PandC, rootDictionary) || dictionaryCheck(PandC, extendedDictionary)) {
                if (i != charstream.length() - 1) {
                    beginIndex++;
                    endIndex++;
                }
                P = PandC;
                C = charstream.substring(beginIndex, endIndex);
                PandC = P + C;
            }
            else {
                if (dictionaryCheck(P, rootDictionary)) {
                    int index = getDictionaryIndex(P, rootDictionary) + 1;
                    this.codestream += index + " ";
                    codestreamLengthCounter++;
                }
                else if (dictionaryCheck(P, extendedDictionary)) {
                    int index = getDictionaryIndex(P, extendedDictionary) + rootDictionary.size() + 1;
                    this.codestream += index + " ";
                    codestreamLengthCounter++;
                }
                else {
                    System.out.println("Error finding P in root dictionary or extended dictionary");
                    break;
                }
                this.extendedDictionary.add(PandC);
                if (i != charstream.length() - 1) {
                    beginIndex++;
                    endIndex++;
                }
                P = C;
                C = charstream.substring(beginIndex, endIndex);
                PandC = P + C;
            }

            // Printing the Encoding Table
            System.out.printf("| %-5s | %-10s | %-5s | %-15s | \n", codestreamLengthCounter+2, P, C, PandC);

        }
        System.out.println("------------------------------------------------");
//        printExtendedDictionary(this.extendedDictionary, this.rootDictionary);
        return codestreamLengthCounter;
    }

    /**
     * This method gets the root dictionary of the inputted charstream.
     * @param charstream The inputted charstream
     * @return The root dictionary of the inputted charstream.
     */
    private ArrayList<String> getRootDictionary(String charstream) {
        char[] arr = charstream.toCharArray();
        ArrayList<String> rootDictionary = new ArrayList<>();

        char firstRoot = arr[0];
        char previous = firstRoot;
        rootDictionary.add(Character.toString(firstRoot));

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > firstRoot && arr[i] > previous) {
                previous = arr[i];
                rootDictionary.add(Character.toString(arr[i]));
            }
        }
        return rootDictionary;
    }

    /**
     * This method takes in a code and a dictionary and checks whether that code is in the dictionary or not.
     * If the code is found in the dictionary, the index of that code is returned.
     * @param code The code to be checked.
     * @param dictionary The dictionary to check if the code is in.
     * @return The index of the code if it is found in the dictionary.
     */
    private int getDictionaryIndex(String code, ArrayList<String> dictionary) {
        int index;
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(code)) {
                index = i;
                return index;
            }
        }
        return -1;
    }

    /**
     * This method takes in a code and a dictionary and checks whether that code is in the dictionary or not.
     * @param code The code to be checked.
     * @param dictionary The dictionary to check if the code is in.
     * @return True if the code is found, false otherwise.
     */
    private boolean dictionaryCheck(String code, ArrayList<String> dictionary) {
        for (String s : dictionary) {
            if (s.equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method prints out the charstream.
     * @param charstream The charstream.
     */
    private void printCharstream(String charstream) {
        int counter = 0;
        System.out.println("Charstream: ");
        for (int i = 0; i < charstream.length(); i++) {
            counter++;
            System.out.print(charstream.charAt(i));
            if (counter % 100 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    /**
     * This method prints out the calculated codestream.
     * @param codestream The codestream.
     */
    private void printCodestream(String codestream) {
        int counter = 0;
        System.out.println("\nCodestream: ");
        for (int i = 0; i < codestream.length(); i++) {
            counter++;
            System.out.print(codestream.charAt(i));
            if (counter % 100 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * This method prints out all the codes in the root dictionary.
     * @param rootDictionary The root dictionary.
     */
    private void printRootDictionary(ArrayList<String> rootDictionary) {
        System.out.println("Root Dictionary: \n-----------"); // Printing out the root directory.
        for (int i = 0; i < rootDictionary.size(); i++) {
            System.out.println("| " + rootDictionary.get(i) + " | (" + (i + 1) + ") |");
        }
        System.out.println("-----------");
    }

    /**
     * This method prints out all the codes in the extended dictionary.
     * @param extendedDictionary The extended dictionary.
     * @param rootDictionary The root dictionary.
     */
    private void printExtendedDictionary(ArrayList<String> extendedDictionary, ArrayList<String> rootDictionary) {
        System.out.println("\nFull Extended Dictionary: \n----------------------"); // Printing out the extended directory.
        for (int i = 0; i < extendedDictionary.size(); i++) {
//            System.out.println("| " + extendedDictionary.get(i) + " | (" + (rootDictionary.size() + i + 1) + ") |");
            System.out.printf("| %-10s | ( %-1s ) | \n", extendedDictionary.get(i), (rootDictionary.size() + i + 1));
        }
        System.out.println("-----------------------");
    }
}
