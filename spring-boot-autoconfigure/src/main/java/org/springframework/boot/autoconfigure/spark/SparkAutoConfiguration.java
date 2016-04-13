/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spark support.
 *
 * @author Amer Aljovic
 */
@Configuration
@ConditionalOnClass(JavaSparkContext.class)
@EnableConfigurationProperties(SparkProperties.class)
public class SparkAutoConfiguration implements Serializable {

    private static final String PROP_SPARK_MASTER = "spark.master";
    private static final String PROP_SPARK_LOG_CONF = "spark.logConf";
    private static final String PROP_SPARK_EXTRA_LISTENERS = "spark.extraListeners";
    private static final String PROP_SPARK_APP_NAME = "spark.app.name";
    private static final String PROP_SPARK_DRIVER_CORES = "spark.driver.cores";
    private static final String PROP_SPARK_DRIVER_MAX_RESULT_SIZE = "spark.driver.maxResultSize";
    private static final String PROP_SPARK_DRIVER_MEMORY = "spark.driver.memory";
    private static final String PROP_SPARK_DRIVER_EXTRA_CLASS_PATH = "spark.driver.extraClassPath";
    private static final String PROP_SPARK_DRIVER_EXTRA_JAVA_OPTIONS = "spark.driver.extraJavaOptions";
    private static final String PROP_SPARK_DRIVER_EXTRA_LIBRARY_PATH = "spark.driver.extraLibraryPath";
    private static final String PROP_SPARK_DRIVER_USER_CLASS_PATH_FIRST = "spark.driver.userClassPathFirst";
    private static final String PROP_SPARK_DRIVER_HOST = "spark.driver.host";
    private static final String PROP_SPARK_DRIVER_PORT = "spark.driver.port";
    public static final String PROP_SPARK_STREAMING_RECEIVER_MAX_RATE = "spark.streaming.receiver.maxRate";
    public static final String PROP_SPARK_STREAMING_BLOCK_INTERVAL = "spark.streaming.blockInterval";
    public static final String PROP_SPARK_STREAMING_BACKPRESSURE_ENABLED = "spark.streaming.backpressure.enabled";

    @Autowired
    SparkProperties sparkProperties = new SparkProperties();

    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.set(PROP_SPARK_MASTER, sparkProperties.getMaster());
        if (sparkProperties.getLogConf() != null) {
            sparkConf.set(PROP_SPARK_LOG_CONF, sparkProperties.getLogConf());
        }
        if (sparkProperties.getExtraListeners() != null) {
            sparkConf.set(PROP_SPARK_EXTRA_LISTENERS, sparkProperties.getExtraListeners());
        }

        sparkConf.set(PROP_SPARK_APP_NAME, sparkProperties.getApp().getName());

        // Driver Properties
        if (sparkProperties.getDriver().getCores() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_CORES, String.valueOf(sparkProperties.getDriver().getCores()));
        }
        if (sparkProperties.getDriver().getMaxResultSize() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_MAX_RESULT_SIZE, String.valueOf(sparkProperties.getDriver().getMaxResultSize()));
        }
        if (sparkProperties.getDriver().getMemory() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_MEMORY, String.valueOf(sparkProperties.getDriver().getMemory()));
        }
        if (sparkProperties.getDriver().getExtraClassPath() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_EXTRA_CLASS_PATH, String.valueOf(sparkProperties.getDriver().getExtraClassPath()));
        }
        if (sparkProperties.getDriver().getExtraJavaOptions() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_EXTRA_JAVA_OPTIONS, String.valueOf(sparkProperties.getDriver().getExtraJavaOptions()));
        }
        if (sparkProperties.getDriver().getExtraLibraryPath() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_EXTRA_LIBRARY_PATH, String.valueOf(sparkProperties.getDriver().getExtraLibraryPath()));
        }
        if (sparkProperties.getDriver().getUserClassPathFirst() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_USER_CLASS_PATH_FIRST, String.valueOf(sparkProperties.getDriver().getUserClassPathFirst()));
        }
        if (sparkProperties.getDriver().getHost() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_HOST, String.valueOf(sparkProperties.getDriver().getHost()));
        }
        if (sparkProperties.getDriver().getPort() != null) {
            sparkConf.set(PROP_SPARK_DRIVER_PORT, String.valueOf(sparkProperties.getDriver().getPort()));
        }

        // Streaming properties
        if (sparkProperties.getStreaming().getBackPressureEnabled() != null) {
            sparkConf.set(PROP_SPARK_STREAMING_BACKPRESSURE_ENABLED,
                    String.valueOf(sparkProperties.getStreaming().getBackPressureEnabled()));
        }
        if (sparkProperties.getStreaming().getBlockInterval() != null) {
            sparkConf.set(PROP_SPARK_STREAMING_BLOCK_INTERVAL,
                    String.valueOf(sparkProperties.getStreaming().getBlockInterval()));
        }
        if (sparkProperties.getStreaming().getReceiverMaxRate() != null) {
            sparkConf.set(PROP_SPARK_STREAMING_RECEIVER_MAX_RATE,
                    String.valueOf(sparkProperties.getStreaming().getReceiverMaxRate()));
        }

        return sparkConf;
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(javaSparkContext());
    }
}
