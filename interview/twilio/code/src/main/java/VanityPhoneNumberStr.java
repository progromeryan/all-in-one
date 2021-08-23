import java.util.*;

public class VanityPhoneNumberStr {
    private static final Map<Character, Character> map;

    static {
        map = new HashMap<>();
        map.put('A', '2');
        map.put('B', '2');
        map.put('C', '2');
        map.put('D', '3');
        map.put('E', '3');
        map.put('F', '3');
        map.put('G', '4');
        map.put('H', '4');
        map.put('I', '4');
        map.put('J', '5');
        map.put('K', '5');
        map.put('L', '5');
        map.put('M', '6');
        map.put('N', '6');
        map.put('O', '6');
        map.put('P', '7');
        map.put('Q', '7');
        map.put('R', '7');
        map.put('S', '7');
        map.put('T', '8');
        map.put('U', '8');
        map.put('V', '8');
        map.put('W', '9');
        map.put('X', '9');
        map.put('Y', '9');
        map.put('Z', '9');
    }

    public static List<String> vanity(List<String> codes, List<String> numbers) {
        List<String> numCodes = getNumCodes(codes);

        Set<String> set = new HashSet<>();

        for (String num : numbers) {
            for (String code : numCodes) {
                if (num.contains(code)) {
                    set.add(num);
                }
            }
        }

        List<String> result = new ArrayList<>(set);

        Collections.sort(result);

        return result;

    }

    private static List<String> getNumCodes(List<String> codes) {
        List<String> numCodes = new ArrayList<>();

        for (String code : codes) {
            StringBuilder numCode = new StringBuilder();
            for (char c : code.toCharArray()) {
                numCode.append(map.get(c));
            }
            numCodes.add(numCode.toString());
        }

        return numCodes;

    }

    public static void main(String[] args) {
        List<String> codes = Arrays.asList("TWLO", "CODE", "HTCH");
        List<String> numbers = Arrays.asList(
                "+17474824380",
                "+14157088956",
                "+919810155555",
                "+15109926333",
                "+14157088956"
        );
        System.out.println(vanity(codes, numbers));
    }
}
