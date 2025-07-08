package com.furkantkgz.renartbackend.config;


import com.furkantkgz.renartbackend.model.ProductEntity;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

@Component
public class GoldPriceConfig {

    public String getGoldPrice() {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.gold-api.com/price/XAU"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            return String.valueOf(json.getBigDecimal("price").divide(BigDecimal.valueOf(31.1035),3, RoundingMode.HALF_DOWN));
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
    public static String setGoldPrice(GoldPriceConfig priceConfig, ProductEntity product) {
        try{
            BigDecimal popularityScore = BigDecimal.valueOf(product.getPopularity_score());
            BigDecimal weight = BigDecimal.valueOf(product.getWeight());
            BigDecimal goldPrice = BigDecimal.valueOf(Float.valueOf(priceConfig.getGoldPrice()));
            BigDecimal price = popularityScore.add(BigDecimal.ONE)
                    .multiply(weight)
                    .multiply(goldPrice)
                    .setScale(2, RoundingMode.HALF_UP);
            return String.valueOf(price);
        }
        catch (Exception e){
            throw new RuntimeException("Error setting gold price", e);
        }
    }
}
