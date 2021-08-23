```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodeDate {
        //Jan 1st 2019 -> 2019-01-01
        static List<String> reformatDate(List<String> dates) {
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Apr", "04");
        map.put("Mar", "03");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        List<String> res = new ArrayList<>();
        for(String date : dates){
            String real = helper(date);
            res.add(real);
        }
        return res;
    }
    static HashMap<String, String> map = new HashMap<>();
    public static String helper(String str) {
        String[] info = str.split("\\s+");
        StringBuilder res = new StringBuilder();
        res.append(info[2]);
        res.append("-");
        
        String month = map.get(info‍‍‍‌‌‍‌‌‍‍‍‍‌‍‌‌‌‍[1]);
        res.append(month);
        res.append("-");
        Pattern p = Pattern.compile("^(\\d+)\\D+$");
        Matcher m = p.matcher(info[0]);
        if(m.matches()) {
            String day = m.group(1);
            if(day.length() == 1) {
                day = "0" + day;
            }
            res.append(day);
        }
        return res.toString();
    }
}

```