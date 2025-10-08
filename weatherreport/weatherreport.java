package weatherreport;

public class WeatherReport {

    public static String Report(IWeatherSensor sensor) {
        int precipitation = sensor.Precipitation();
        String report = "Sunny Day";

        if (sensor.TemperatureInC() > 25) {
            if (precipitation >= 20 && precipitation < 60)
                report = "Partly Cloudy";
            else if (sensor.WindSpeedKMPH() > 50)
                report = "Alert, Stormy with heavy rain";
        }
        return report;
    }

    static void runTests() {
        // Test 1: High precipitation > 60 should be rain alert (BUG exposed)
        IWeatherSensor highPrecipitation = new IWeatherSensor() {
            public double TemperatureInC() { return 26; }
            public int Precipitation() { return 70; }  
            public int Humidity() { return 80; }
            public int WindSpeedKMPH() { return 30; }  
        };
        String report1 = Report(highPrecipitation);
        System.out.println("Test High Precipitation: " + report1);
        assert report1.contains("rain") : "Expected rain alert (fails with current bug)";

        // Test 2: Medium precipitation triggers partly cloudy
        IWeatherSensor mediumPrecipitation = new IWeatherSensor() {
            public double TemperatureInC() { return 27; }
            public int Precipitation() { return 40; }
            public int Humidity() { return 70; }
            public int WindSpeedKMPH() { return 10; }
        };
        String report2 = Report(mediumPrecipitation);
        System.out.println("Test Medium Precipitation: " + report2);
        assert report2.equals("Partly Cloudy") : "Expected Partly Cloudy";

        // Test 3: Low precipitation triggers sunny
        IWeatherSensor lowPrecipitation = new IWeatherSensor() {
            public double TemperatureInC() { return 20; }  // <=25
            public int Precipitation() { return 10; }
            public int Humidity() { return 50; }
            public int WindSpeedKMPH() { return 5; }
        };
        String report3 = Report(lowPrecipitation);
        System.out.println("Test Low Precipitation: " + report3);
        assert report3.equals("Sunny Day") : "Expected Sunny Day";
    }

    public static void main(String[] args) {
        runTests();
        System.out.println("Tests completed (expect failures to expose bugs).");
    }
}

interface IWeatherSensor {
    double TemperatureInC();
    int Precipitation();
    int Humidity();
    int WindSpeedKMPH();
}
