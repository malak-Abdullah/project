package com.example.dell.smartgarden;

public class Constants {

    public static final String MQTT_BROKER_URL = "tcp://m15.cloudmqtt.com:16121";
    public static final String MQTT_USERNAME = "lwrmiitr";
    public static final String MQTT_PASSWORD = "SJhy-SHxd0tH";
    public static final int QOS = 2;
    //Topics
    public static final String PUBLISH_TOPIC_LEFT_FAN = "SmartGarden/Actuators/Fan/Left";
    public static final String PUBLISH_TOPIC_RIGHT_FAN = "SmartGarden/Actuators/Fan/Right";
    public static final String PUBLISH_TOPIC_PUMP = "SmartGarden/Actuators/Pump";
    public static final String PUBLISH_TOPIC_LIGHT = "SmartGarden/Actuators/Light";

    public static final String SUBSCRIBE_TOPIC_LIGHT_SENSOR = "SmartGarden/Sensors/LightIntensity";
    public static final String SUBSCRIBE_TOPIC_WATER_LEVEL = "SmartGarden/Sensors/WaterLevel";
    public static final String SUBSCRIBE_TOPIC_SOILMOISTURE = "SmartGarden/Sensors/SoilMoisture";
    public static final String SUBSCRIBE_TOPIC_TEMPERTURE = "SmartGarden/Sensors/Temperature";
    public static final String SUBSCRIBE_TOPIC_HUMIDITY = "SmartGarden/Sensors/Humidity";


    public static final String CLIENT_ID = "p";


}
