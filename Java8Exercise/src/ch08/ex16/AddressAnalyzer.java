package ch08.ex16;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressAnalyzer {
	public static void main(String[] args) {
        List<String> data = Arrays.asList(
                "CityA, AA, 12345",
                "CityB, BB, 123456789");
        Pattern pattern = Pattern.compile("(?<city>[\\p{L}]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zip>[0-9]{5}|[0-9]{9})");
        data.stream().map(pattern::matcher).filter(Matcher::matches).forEach(m -> {
            String city = m.group("city");
            String state = m.group("state");
            String zip = m.group("zip");
            System.out.println("city=" + city + ", state=" + state + ", zip=" + zip);
        });
    }
}
