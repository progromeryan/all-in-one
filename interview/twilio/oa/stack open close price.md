![img](../assets/stack%20open%20close%20price.png)
![img](../assets/stack%20open%20close%20price%202.png)
![img](../assets/stack%20open%20close%20price%203.png)

backend

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;
// A Java serialization/deserialization library to convert Java Objects into JSON and back
import com.google.gson.*;

import java.io.InputStreamReader;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class StockOpenClosePrice {
    class Result {
        int page;
        int per_page;
        int total;
        int total_pages;
        List<Data> data;

    }

    class Data {
        String date;
        float open;
        float close;
        float high;
        float low;
    }

    static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
        LocalDate startDay = LocalDate.of(2000, Month.JANUARY, 5);
        LocalDate endDay = LocalDate.of(2014, Month.JANUARY, 1);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("d-MMMM-yyyy", Locale.ENGLISH);
        //
        try {
            LocalDate date01 = LocalDate.parse(firstDate, timeFormat);
            LocalDate date02 = LocalDate.parse(lastDate, timeFormat);
            LocalDate tempDate;
            String[] contWeekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

            if (date01.isBefore(startDay)) {
                date01 = startDay;
            }
            if (date02.isAfter(endDay)) {
                date02 = endDay;
            }

            if (!Arrays.asList(contWeekDays).contains(weekDay)) {
                System.out.println("Out of parameters.");
            } else {
                do {
                    tempDate = date01.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(weekDay.toUpperCase())));
                    URL dataEntry = new URL("https://jsonmock.hackerrank.com/api/stocks/?date=" + timeFormat.format(tempDate));
                    InputStreamReader reader = new InputStreamReader(dataEntry.openStream());
                    Result result = new Gson().fromJson(reader, Result.class);

                    if (result.total == 0) {
                        date01 = tempDate.plusWeeks(1);
                    } else {
                        for (Data datas : result.data) {
                            System.out.println(datas.date + " " + datas.open + " " + datas.close);
                            date01 = tempDate.plusWeeks(1);
                        }
                    }
                } while (date01.isBefore(date02));
            }
        } catch (Exception ex) {
        }
    }

//----  End of my code ----

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String _firstDate;
        try {
            _firstDate = in.nextLine();
        } catch (Exception e) {
            _firstDate = null;
        }

        String _lastDate;
        try {
            _lastDate = in.nextLine();
        } catch (Exception e) {
            _lastDate = null;
        }

        String _weekDay;
        try {
            _weekDay = in.nextLine();
        } catch (Exception e) {
            _weekDay = null;
        }

        openAndClosePrices(_firstDate, _lastDate, _weekDay);

    }
}
```



frontend

![img](../assets/stock%20frontend.png)

```javascript
import "./styles.css";
import { useState } from "react";

export default function App() {
  const [input, setInput] = useState("");
  const [data, setData] = useState({
    open: "",
    close: "",
    hight: "",
    low: ""
  });
  const [noResultFound, setNoResultFound] = useState(false);

  const onSearch = async () => {
    if (!input) {
      return;
    }
    const url = `https://jsonmock.hackerrank.com/api/stocks?date=${input}`;
    const response = await fetch(url);
    const json = await response.json();
    console.log(json);
    if (json && json.data && json.data.length !== 0) {
      const data = json.data[0];
      setData({
        open: data.open,
        close: data.close,
        high: data.high,
        low: data.low
      });
      setNoResultFound(false);
    } else {
      setData({
        open: "",
        close: "",
        high: "",
        low: ""
      });
      setNoResultFound(true);
    }
  };

  return (
    <div className="App">
      <h1>Hello CodeSandbox</h1>
      <input
        data-testid="app-input"
        onChange={(e) => setInput(e.target.value)}
      />
      <button data-testid="submit-buttom" onClick={() => onSearch()}>
        search
      </button>
      <h2>Start editing to see some magic happen!</h2>
      <div>
        <ul data-testid="stock-data">
          <li>Open: {data.open}</li>
          <li>Close: {data.close}</li>
          <li>High: {data.high}</li>
          <li>Low: {data.low}</li>
        </ul>
      </div>
      {noResultFound && <div data-testid="no-result">No Results Found</div>}
    </div>
  );
}

```