package by.chitatel.api.utils;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Responses {
    public static String getCSRFToken(Response response) {
        Document doc = Jsoup.parse(response.asString());
        Element metaTag = doc.select("meta[name=csrf-token]").first();
        return metaTag != null ? metaTag.attr("content") : "";
    }

    public static Cookies getCookies(Response response) {
        return new Cookies(
                new Cookie.Builder("XSRF-TOKEN", response.getCookies().get("XSRF-TOKEN")).build(),
                new Cookie.Builder("chitatel_session", response.getCookies().get("chitatel_session")).build()
        );
    }
}
