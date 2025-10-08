public class Misaligned {
    static String[][] buildColorMap() {
        String majorColors[] = {"White", "Red", "Black", "Yellow", "Violet"};
        String minorColors[] = {"Blue", "Orange", "Green", "Brown", "Slate"};
        String[][] map = new String[25][3];
        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[index][0] = String.valueOf(index);    
                map[index][1] = majorColors[i];          
                map[index][2] = minorColors[i];          
                index++;
            }
        }
        return map;
    }

    static void printColorMap() {
        String[][] map = buildColorMap();
        for (int i = 0; i < map.length; i++) {
            System.out.printf("%s | %s | %s\n", map[i][0], map[i][1], map[i][2]);
        }
    }


    static void runTests() {
        String[][] map = buildColorMap();

        assert map.length == 25 : "Expected 25 entries";

        assert map[1][2].equals("Orange") : "Expected minor color 'Orange' at index 1";
        assert map[6][2].equals("Orange") : "Expected minor color 'Orange' at index 6";
        assert map[24][2].equals("Slate") : "Expected minor color 'Slate' at index 24";

        System.out.println("Tests completed (expect failures to expose bugs).");
    }

    public static void main(String[] args) {
        printColorMap();
        runTests();
    }
}
