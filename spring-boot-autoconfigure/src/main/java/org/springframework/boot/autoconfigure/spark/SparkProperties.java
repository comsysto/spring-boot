package org.springframework.boot.autoconfigure.spark;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author Amer Aljovic
 */
@ConfigurationProperties(prefix = "spring.spark")
public class SparkProperties implements Serializable {

    private String master = "local[*]";

    private String extraListeners;

    private String logConf;

    private App app = new App();

    private Driver driver = new Driver();

    private Streaming streaming = new Streaming();

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getExtraListeners() {
        return extraListeners;
    }

    public void setExtraListeners(String extraListeners) {
        this.extraListeners = extraListeners;
    }

    public String getLogConf() {
        return logConf;
    }

    public void setLogConf(String logConf) {
        this.logConf = logConf;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Streaming getStreaming() {
        return streaming;
    }

    public void setStreaming(Streaming streaming) {
        this.streaming = streaming;
    }

    public static class App {

        private String name = "Spark App";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Driver {

        private Integer cores;

        private String maxResultSize;

        private String memory;

        private String extraClassPath;

        private String extraJavaOptions;

        private String extraLibraryPath;

        private String userClassPathFirst;

        private String host;

        private Integer port;

        public Integer getCores() {
            return cores;
        }

        public void setCores(Integer cores) {
            this.cores = cores;
        }

        public String getMaxResultSize() {
            return maxResultSize;
        }

        public void setMaxResultSize(String maxResultSize) {
            this.maxResultSize = maxResultSize;
        }

        public String getMemory() {
            return memory;
        }

        public void setMemory(String memory) {
            this.memory = memory;
        }

        public String getExtraClassPath() {
            return extraClassPath;
        }

        public void setExtraClassPath(String extraClassPath) {
            this.extraClassPath = extraClassPath;
        }

        public String getExtraJavaOptions() {
            return extraJavaOptions;
        }

        public void setExtraJavaOptions(String extraJavaOptions) {
            this.extraJavaOptions = extraJavaOptions;
        }

        public String getExtraLibraryPath() {
            return extraLibraryPath;
        }

        public void setExtraLibraryPath(String extraLibraryPath) {
            this.extraLibraryPath = extraLibraryPath;
        }

        public String getUserClassPathFirst() {
            return userClassPathFirst;
        }

        public void setUserClassPathFirst(String userClassPathFirst) {
            this.userClassPathFirst = userClassPathFirst;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

    public static class Streaming {

        private Integer batchDuration = 1;

        private Boolean backPressureEnabled;

        private String blockInterval;

        private Integer receiverMaxRate;

        public Integer getBatchDuration() {
            return batchDuration;
        }

        public void setBatchDuration(Integer batchDuration) {
            this.batchDuration = batchDuration;
        }

        public Boolean getBackPressureEnabled() {
            return backPressureEnabled;
        }

        @ConfigurationProperties(prefix = "spring.spark.streaming.backpressure.enabled")
        public void setBackPressureEnabled(Boolean backPressureEnabled) {
            this.backPressureEnabled = backPressureEnabled;
        }

        public String getBlockInterval() {
            return blockInterval;
        }

        public void setBlockInterval(String blockInterval) {
            this.blockInterval = blockInterval;
        }

        public Integer getReceiverMaxRate() {
            return receiverMaxRate;
        }

        public void setReceiverMaxRate(Integer receiverMaxRate) {
            this.receiverMaxRate = receiverMaxRate;
        }
    }
}
