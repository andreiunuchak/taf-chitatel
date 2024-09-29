package by.chitatel.api.utils;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class Responses {
    public static String getCSRFToken(Response response) {
        String csrfTokenCssQuery = "meta[name=csrf-token]";
        String csrfTokenAttributyKey = "content";
        Document doc = Jsoup.parse(response.asString());
        Element metaTag = doc.select(csrfTokenCssQuery).first();
        return metaTag != null ? metaTag.attr(csrfTokenAttributyKey) : "";
    }

    public static List<String> getSearchedProducts(Response response) {
        String productCssQuery = "div.products-list-ajax div.name";
        Document doc = Jsoup.parse(response.asString());
        Elements metaTag = doc.select(productCssQuery);
        return metaTag.stream().map(Element::text).collect(Collectors.toList());
    }

    public static Cookies getCookies(Response response) {
        return new Cookies(
                new Cookie.Builder("XSRF-TOKEN", response.getCookies().get("XSRF-TOKEN")).build(),
                new Cookie.Builder("chitatel_session", response.getCookies().get("chitatel_session")).build()
        );
    }
}
