package com.trungthuc.slug;

import com.github.slugify.Slugify;

import java.text.Normalizer;
import java.util.regex.Pattern;
import static org.springframework.util.Assert.*;

public class UrlUtil {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public  static String slug_EN(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String slug = pattern.matcher(normalized).replaceAll("");
      //  return slug.toLowerCase(Locale.ENGLISH);
        return pattern.matcher(slug).replaceAll("");
    }

    public static String slug_VI(String slug) {
        isTrue( !(slug == null || "".equals(slug)) ,"vui long nhap value");
        try {
            String  input = slug.trim().replaceAll(" - "," ").replaceAll("\\s{2,}", "");
            String value = WHITESPACE.matcher(input).replaceAll("-");
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase()
                    .replaceAll(" ", "-")
                    .replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // match with FUNCTION slugify postgreSQL
    // https://www.kdobson.net/2019/ultimate-postgresql-slug-function/

    public static String slugify (String slug) {
        try {
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            String temp = pattern.matcher(slug).replaceAll("").toLowerCase()
                    .replaceAll("đ", "d");
            Slugify slg = new Slugify().withLowerCase(true);
            String result = slg.slugify(temp);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
      //  System.out.printf(slug_VI("Điện Thoại Samsung Galaxy A11 (32GB/3GB) - Hàng Chính Hãng"));
        System.out.printf(slugify("Điện Thoại Samsung Galaxy A11 (32GB/3GB) - Hàng Chính Hãng"));
    }
}
