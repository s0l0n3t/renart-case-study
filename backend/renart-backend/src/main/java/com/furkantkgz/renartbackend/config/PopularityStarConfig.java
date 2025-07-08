package com.furkantkgz.renartbackend.config;

public class PopularityStarConfig {
    public static float roundPopularity(float popularity) {
        return (float) (Math.round(popularity * 10.0) / 10.0);
    }
    public static float popularityToStar(float popularity) {
        if (popularity >= 0.0 && popularity < 0.1) {
            return (float) 0.0;
        }
        if (popularity >= 0.1 && popularity < 0.2) {
            return (float) 0.5;
        }
        if (popularity >= 0.2 && popularity < 0.3) {
            return (float) 1.0;
        }
        if (popularity >= 0.3 && popularity < 0.4) {
            return (float) 1.5;
        }
        if (popularity >= 0.4 && popularity < 0.5) {
            return (float) 2.0;
        }
        if (popularity >= 0.5 && popularity < 0.6) {
            return (float) 2.5;
        }
        if (popularity >= 0.6 && popularity < 0.7) {
            return (float) 3.0;
        }
        if (popularity >= 0.7 && popularity < 0.8) {
            return (float) 3.5;
        }
        if (popularity >= 0.8 && popularity < 0.9) {
            return (float) 4.0;
        }
        if (popularity >= 0.9 && popularity < 1.0) {
            return (float) 4.5;
        }
        if (popularity == 1.0) {
            return (float) 5.0;
        }
        return (float) 0.0;
    }
}
