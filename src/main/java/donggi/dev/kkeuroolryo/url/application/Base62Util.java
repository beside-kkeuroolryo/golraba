package donggi.dev.kkeuroolryo.url.application;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {

    private final int RADIX = 62;
    private final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public String encoding(long param) {
        final StringBuilder sb = new StringBuilder();

        do {
            sb.append(BASE62[(int) param % 62]);
            param /= 62;
        } while (param > 0);

        return sb.toString();
    }

    public long decoding(String param) {
        int result = 0;
        int power = 1;

        for (int i = 0; i < param.length(); i++) {
            int digit = String.valueOf(BASE62).indexOf(param.charAt(i));

            result += digit * power;

                power *= RADIX;
        }
        return result;
    }

}
