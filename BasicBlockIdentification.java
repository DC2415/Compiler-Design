import java.util.ArrayList;

public class BasicBlockIdentification {
    public static ArrayList<ArrayList<String>> identifyBasicBlocks(ArrayList<String> threeAddressCode) {
        ArrayList<ArrayList<String>> basicBlocks = new ArrayList<>();
        ArrayList<String> currentBlock = new ArrayList<>();
        
        for (String line : threeAddressCode) {
            if (line.startsWith("LABEL")) {
                if (!currentBlock.isEmpty()) {
                    basicBlocks.add(new ArrayList<>(currentBlock));
                    currentBlock.clear();
                }
                currentBlock.add(line);
            } else {
                currentBlock.add(line);
            }
        }
        
        if (!currentBlock.isEmpty()) {
            basicBlocks.add(new ArrayList<>(currentBlock));
        }
        
        return basicBlocks;
    }
    
    public static void main(String[] args) {
        ArrayList<String> threeAddressCode = new ArrayList<>();
        threeAddressCode.add("a = b + c");
        threeAddressCode.add("LABEL L2:");
        threeAddressCode.add("if a < b goto LABEL L1");
        threeAddressCode.add("c = t1");
        threeAddressCode.add("LABEL L1:");
        threeAddressCode.add("t2 = c * d");
        threeAddressCode.add("goto LABEL L2");
        threeAddressCode.add("f = e - c");
        
        ArrayList<ArrayList<String>> basicBlocks = identifyBasicBlocks(threeAddressCode);
        
        int blockNumber = 1;
        for (ArrayList<String> block : basicBlocks) {
            System.out.println("Basic Block " + blockNumber + ":\n");
            for (String line : block) {
                System.out.println(line);
            }
            System.out.println();
            blockNumber++;
        }
    }
}
