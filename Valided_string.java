import java.util.ArrayList;

class Valided_string {
    // /*
    //  * Grammar is
    //  * S -> aAB
    //     B->c        
    //  * A -> ab
    //  */

    static boolean A(String rule1, StringBuilder input) {
        String rule2 = "ab";
        int pos = input.indexOf(rule2.toString());
        if (pos != -1) {
            input.delete(pos, pos + rule2.length());
            return true;
        }
        return false;
    }

    static boolean S(StringBuilder input) {
        String rule1 = "aAb";
        int i = 0;
        while (i < input.length()) {
            if (rule1.isEmpty() || input.charAt(i) == '$')
                return true;
            char ch = input.charAt(i);
            if (rule1.indexOf(ch) != -1) {
                rule1 = rule1.replace(String.valueOf(ch), "");
                input.deleteCharAt(i);
            } else if (rule1.charAt(0) == 'A') {
                // NON terminals
                if (A(rule1, input))
                    continue;
                else
                    return false;
            } else
                break;
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder("aabb$");
        if (S(input))
            System.out.println("Accepted string!");
        else
            System.out.println("NOT Accepted string!");
    }
   
}
