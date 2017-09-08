package com.weibo.coolweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weixj on 2017/9/1.
 */

public class HeFengWeather {

    /**
     * aqi : {"city":{"aqi":"186","co":"1","no2":"38","o3":"125","pm10":"173","pm25":"140","qlty":"中度污染","so2":"3"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.90498734","lon":"116.40528870","update":{"loc":"2017-09-01 14:46","utc":"2017-09-01 06:46"}}
     * daily_forecast : [{"astro":{"mr":"15:31","ms":"00:43","sr":"05:43","ss":"18:46"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2017-09-01","hum":"54","pcpn":"0.0","pop":"12","pres":"1012","tmp":{"max":"27","min":"19"},"uv":"7","vis":"20","wind":{"deg":"223","dir":"西南风","sc":"微风","spd":"7"}},{"astro":{"mr":"16:16","ms":"01:32","sr":"05:44","ss":"18:44"},"cond":{"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"},"date":"2017-09-02","hum":"52","pcpn":"0.0","pop":"2","pres":"1012","tmp":{"max":"26","min":"19"},"uv":"7","vis":"20","wind":{"deg":"251","dir":"西南风","sc":"微风","spd":"4"}},{"astro":{"mr":"16:59","ms":"02:25","sr":"05:45","ss":"18:43"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-09-03","hum":"42","pcpn":"0.3","pop":"44","pres":"1012","tmp":{"max":"27","min":"19"},"uv":"7","vis":"19","wind":{"deg":"237","dir":"西南风","sc":"微风","spd":"7"}}]
     * hourly_forecast : [{"cond":{"code":"302","txt":"雷阵雨"},"date":"2017-09-01 16:00","hum":"62","pop":"2","pres":"1011","tmp":"26","wind":{"deg":"182","dir":"南风","sc":"微风","spd":"9"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-09-01 19:00","hum":"75","pop":"6","pres":"1013","tmp":"24","wind":{"deg":"132","dir":"东南风","sc":"微风","spd":"8"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-09-01 22:00","hum":"83","pop":"6","pres":"1014","tmp":"22","wind":{"deg":"115","dir":"东南风","sc":"微风","spd":"7"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"25","hum":"66","pcpn":"0","pres":"1011","tmp":"25","vis":"7","wind":{"deg":"258","dir":"西南风","sc":"3-4","spd":"12"}}
     * status : ok
     * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},"trav":{"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    @Override
    public String toString() {
        return "HeFengWeaTher{" +
                "status='" + status + '\'' +
                ", aqi=" + aqi +
                ", basic=" + basic +
                ", now=" + now +
                ", suggestion=" + suggestion +
                ", daily_forecast=" + daily_forecast +
                '}';
    }

    private String status;
    private Aqi aqi;
    private Basic basic;
    private Now now;
    private Suggestion suggestion;
    @SerializedName("daily_forecast")
    private List<DailyForecast> daily_forecast;

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public static class Aqi {
        @Override
        public String toString() {
            return "Aqi{" +
                    "city=" + city +
                    '}';
        }

        /**
         * city : {"aqi":"186","co":"1","no2":"38","o3":"125","pm10":"173","pm25":"140","qlty":"中度污染","so2":"3"}
         */

        private City city;

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public static class City {
            @Override
            public String toString() {
                return "City{" +
                        "aqi='" + aqi + '\'' +
                        ", pm25='" + pm25 + '\'' +
                        '}';
            }

            /**
             * aqi : 186
             * co : 1
             * no2 : 38
             * o3 : 125
             * pm10 : 173
             * pm25 : 140
             * qlty : 中度污染
             * so2 : 3
             */

            private String aqi;
            private String pm25;

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

        }
    }

    public static class Basic {
        @Override
        public String toString() {
            return "Basic{" +
                    "cityName='" + cityName + '\'' +
                    ", weatherId='" + weatherId + '\'' +
                    ", update=" + update +
                    '}';
        }

        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.90498734
         * lon : 116.40528870
         * update : {"loc":"2017-09-01 14:46","utc":"2017-09-01 06:46"}
         */

        @SerializedName("city")
        private String cityName;
        @SerializedName("id")
        private String weatherId;
        private Update update;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getWeatherId() {
            return weatherId;
        }

        public void setWeatherId(String weatherId) {
            this.weatherId = weatherId;
        }

        public Update getUpdate() {
            return update;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }

        public static class Update {
            @Override
            public String toString() {
                return "Update{" +
                        "updateTime='" + updateTime + '\'' +
                        '}';
            }

            /**
             * loc : 2017-09-01 14:46
             * utc : 2017-09-01 06:46
             */

            @SerializedName("loc")
            private String updateTime;

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }

    public static class Now {
        @Override
        public String toString() {
            return "Now{" +
                    "more=" + more +
                    ", temperature='" + temperature + '\'' +
                    '}';
        }

        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 25
         * hum : 66
         * pcpn : 0
         * pres : 1011
         * tmp : 25
         * vis : 7
         * wind : {"deg":"258","dir":"西南风","sc":"3-4","spd":"12"}
         */

        @SerializedName("cond")
        private More more;
        @SerializedName("tmp")
        private String temperature;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public More getMore() {
            return more;
        }

        public void setMore(More more) {
            this.more = more;
        }

        public static class More {
            @Override
            public String toString() {
                return "More{" +
                        "info='" + info + '\'' +
                        '}';
            }

            /**
             * code : 101
             * txt : 多云
             */

            @SerializedName("txt")
            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class Wind {
            /**
             * deg : 258
             * dir : 西南风
             * sc : 3-4
             * spd : 12
             */

            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class Suggestion {
        /**
         * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
         * comf : {"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"适宜","txt":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"}
         * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        @SerializedName("comf")
        private Comfort comfort;
        @SerializedName("cw")
        private CarWash carWash;
        private Sport sport;

        @Override
        public String toString() {
            return "Suggestion{" +
                    "comfort=" + comfort +
                    ", carWash=" + carWash +
                    ", sport=" + sport +
                    '}';
        }

        public Sport getSport() {
            return sport;
        }

        public void setSport(Sport sport) {
            this.sport = sport;
        }

        public static class Comfort {
            @Override
            public String toString() {
                return "Comfort{" +
                        "info='" + info + '\'' +
                        '}';
            }

            /**
             * brf : 较舒适
             * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
             */

            @SerializedName("txt")
            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class CarWash {
            @Override
            public String toString() {
                return "CarWash{" +
                        "info='" + info + '\'' +
                        '}';
            }

            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            @SerializedName("txt")
            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class Sport {
            @Override
            public String toString() {
                return "Sport{" +
                        "info='" + info + '\'' +
                        '}';
            }

            /**
             * brf : 适宜
             * txt : 天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。
             */

            @SerializedName("txt")
            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

    }

    public static class DailyForecast {
        @Override
        public String toString() {
            return "DailyForecast{" +
                    "more=" + more +
                    ", tmperature=" + tmperature +
                    ", date='" + date + '\'' +
                    '}';
        }

        /**
         * astro : {"mr":"15:31","ms":"00:43","sr":"05:43","ss":"18:46"}
         * cond : {"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"}
         * date : 2017-09-01
         * hum : 54
         * pcpn : 0.0
         * pop : 12
         * pres : 1012
         * tmp : {"max":"27","min":"19"}
         * uv : 7
         * vis : 20
         * wind : {"deg":"223","dir":"西南风","sc":"微风","spd":"7"}
         */
        @SerializedName("cond")
        private More more;
        @SerializedName("tmp")
        private Temperature tmperature;
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public static class More {
            @Override
            public String toString() {
                return "More{" +
                        "info='" + info + '\'' +
                        '}';
            }

            /**
             * code_d : 101
             * code_n : 104
             * txt_d : 多云
             * txt_n : 阴
             */
            @SerializedName("txt_d")
            private String info;

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

        public static class Temperature {
            @Override
            public String toString() {
                return "Temperature{" +
                        "max='" + max + '\'' +
                        ", min='" + min + '\'' +
                        '}';
            }

            /**
             * max : 27
             * min : 19
             */

            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

    }

}
